package com.serpics.catalog.facade;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
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
	public CategoryData findCategoryByCode(final String code){ 
	Category category = categoryService.findOne(new Specification<Category>() {
            @Override
            public Predicate toPredicate(final Root<Category> root, final CriteriaQuery<?> query,
                    final CriteriaBuilder cb) {
            	Expression<String> e = root.get("code");
            		
                return cb.like(	e, code);
            }
		});
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
		Assert.notNull(parent);
		Category entity = buildCategory(category, new Category());
		entity = categoryService.create(entity, parent);
		category = categoryConverter.convert(entity);
		return category;
	} 
	
	protected Category buildCategory(CategoryData category, Category entity){
		entity.setCode(category.getCode());
		entity.setUrl(category.getUrl());
		return entity;
	}
	
	@Transactional
	public void addCategoryParent(Long childId, Long parentId) {
		Category parent = categoryService.findOne(parentId);
		Category child = categoryService.findOne(childId);
		
		Assert.notNull(parent , "parent not found");
		Assert.notNull(child , "child not found");
		
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
