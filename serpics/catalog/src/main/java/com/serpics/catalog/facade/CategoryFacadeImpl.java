/*******************************************************************************
 * Copyright 2014-2016  StepFour Srl
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *  
 *      http://www.apache.org/licenses/LICENSE-2.0
 *  
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *  
 *******************************************************************************/
package com.serpics.catalog.facade;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.serpics.catalog.data.model.Category;
import com.serpics.catalog.data.model.Ctentry;
import com.serpics.catalog.facade.data.CategoryData;
import com.serpics.catalog.facade.data.CtentryData;
import com.serpics.catalog.services.BrandService;
import com.serpics.catalog.services.CategoryService;
import com.serpics.catalog.services.PriceService;
import com.serpics.catalog.services.ProductService;
import com.serpics.commerce.session.CommerceSessionContext;
import com.serpics.core.Engine;
import com.serpics.core.facade.AbstractPopulatingConverter;
import com.serpics.i18n.data.model.MultilingualString;
import com.serpics.i18n.data.model.MultilingualText;
import com.serpics.stereotype.StoreFacade;

@StoreFacade("categoryFacade")
@Transactional(readOnly=true)
public class CategoryFacadeImpl implements CategoryFacade {
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	ProductService productService;
	
	@Autowired
	BrandService brandService;
	
	@Autowired
	PriceService priceService;
	
	@Resource(name="ctentryConverter")
	AbstractPopulatingConverter<Ctentry, CtentryData> ctentryConverter;
	
	@Resource(name="categoryConverter")
	AbstractPopulatingConverter<Category, CategoryData> categoryConverter;
	
	@Autowired
	Engine<CommerceSessionContext> engine;
	
	
	@Override
	public Page<CategoryData> listCategory(Pageable page) {
		Page<Category> categories = categoryService.findAll(page);
		List<CategoryData> l = new ArrayList<CategoryData>();
		for (Category category : categories.getContent()) {
			l.add(categoryConverter.convert(category));
		}
		Page<CategoryData> list = new PageImpl<CategoryData>(l, page, categories.getTotalElements());
		return list;
	}
	
	@Override
	public List<CategoryData> listTopCategory() {
		List<Category> categories = categoryService.findRootCategory();
		List<CategoryData> list = new ArrayList<CategoryData>();
		for (Category category : categories) {
			list.add(categoryConverter.convert(category));
		}
		return list;
	}
	
	@Override
	public List<CategoryData> listChildCategories(Long id) {
		List<CategoryData> list = new ArrayList<CategoryData>();
		Category parent = categoryService.findOne(id);
		Assert.notNull(parent);
		List<Category> categories = categoryService.getChildCategories(parent);
		for (Category category : categories) {
			list.add(categoryConverter.convert(category));
		}
		
		return list;
	}
	
	@Override
	public List<CategoryData> listChildCategories(String code) {
		List<CategoryData> list = new ArrayList<CategoryData>();
		Category parent = categoryService.findByCode(code);
		Assert.notNull(parent);
		List<Category> categories = categoryService.getChildCategories(parent);
		for (Category category : categories) {
			list.add(categoryConverter.convert(category));
		}
		
		return list;
	}
	
	@Override 
	public CategoryData findCategoryByCode(final String code){ 
	Category category = categoryService.findByCode(code);
		CategoryData entity = null;
		if(category != null)
			entity = categoryConverter.convert(category);
		
		return entity;
	}
	
	@Override 
	public CategoryData findCategoryById(final Long id){ 
		Category category = categoryService.findOne(id);
		CategoryData entity = null;
		if(category != null)
			entity = categoryConverter.convert(category);
		
		return entity;
	}
	
	
	@Override
	@Transactional
	public CategoryData create(CategoryData category) {
		Category  entity = buildCategory(category, new Category());
		entity = categoryService.create(entity); 
		category = categoryConverter.convert(entity);
		return category;
	}
	
	@Override
	@Transactional
	public CategoryData create(CategoryData category, Long parentId) {
		Category parent = categoryService.findOne(parentId);
		Assert.notNull(parent , "Category parent not found !");
		Category entity = buildCategory(category, new Category());
		entity = categoryService.create(entity, parent);
		category = categoryConverter.convert(entity);
		return category;
	} 
	
	protected Category buildCategory(CategoryData categoryData, Category category){
		
		category.setCode(categoryData.getCode());
		category.setUrl(categoryData.getUrl());
		category.setPublished(categoryData.isPublished());
		
		String locale = engine.getCurrentContext().getLocale().getLanguage();
		
		category.setDescription(new MultilingualText(locale, categoryData.getDescription()));
		category.setMetaDescription(new MultilingualString(locale, categoryData.getMetaDescription()));
		category.setMetaKeyword(new MultilingualString(locale, categoryData.getMetaKeyword()));
		category.setName(new MultilingualString(locale, categoryData.getName()));

		return category;
	}
	
	@Transactional
	public void addCategoryParent(Long childId, Long parentId) {
		Category parent = categoryService.findOne(parentId);
		Category child = categoryService.findOne(childId);
		
		Assert.notNull(parent , "Category parent not found !");
		Assert.notNull(child , "Category child not found !");
		
		categoryService.addRelationCategory(child, parent);
	}
	
	@Override
	@Transactional
	public CategoryData updateCategory(CategoryData category){
		Category entity = categoryService.findOne(category.getId());
		entity = buildCategory(category, entity);
		entity = categoryService.update(entity);
		category = categoryConverter.convert(entity);
		return category;
	}
	
	@Override
	@Transactional
	public void deleteCategory(Long id){
		categoryService.delete(id);
	}

}
