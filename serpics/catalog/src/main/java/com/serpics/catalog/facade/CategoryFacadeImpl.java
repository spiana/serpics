package com.serpics.catalog.facade;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;

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
	public CategoryData findCategoryByCode(final String code){ 
	Category category = categoryService.findOne(new Specification<Category>() {
            @Override
            public Predicate toPredicate(final Root<Category> root, final CriteriaQuery<?> query,
                    final CriteriaBuilder cb) {
            	Expression<String> e = root.get("code");
            		
                return cb.like(	e, code);
            }
		});
		CategoryData entity = categoryConverter.convert(category);
		return entity;
	}
	
	
	@Override
	public CategoryData addCategory(CategoryData category) {
		Category  entity = buildCategory(category);
		entity = categoryService.create(entity); 
		category = categoryConverter.convert(entity);
		return category;
	}
	
	@Override
	public CategoryData addCategory(CategoryData category, String parentUuid) {
		Category parent = categoryService.findByUUID(parentUuid);
		Category entity = buildCategory(category);
		entity = categoryService.create(entity, parent);
		category = categoryConverter.convert(entity);
		return category;
	}
	
	private Category buildCategory(CategoryData category){
		Category entity = new Category();
		entity.setCode(category.getCode());
		entity.setUrl(category.getUrl());
		return entity;
	}
	
	
	public void addCategoryParent(String parentUui, String childUuid) {
		Category parent = categoryService.findByUUID(parentUui);
		Category child = categoryService.findByUUID(childUuid);
		
		Assert.assertNotNull("parent not found", parent);
		Assert.assertNotNull("child not found", child);
		
		categoryService.addRelationCategory(child, parent);
	}
	

}
