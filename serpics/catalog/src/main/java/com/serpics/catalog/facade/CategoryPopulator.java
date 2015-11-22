package com.serpics.catalog.facade;

import org.springframework.beans.factory.annotation.Autowired;

import com.serpics.catalog.data.model.Category;
import com.serpics.catalog.facade.data.CategoryData;
import com.serpics.catalog.services.CategoryService;
import com.serpics.commerce.session.CommerceSessionContext;
import com.serpics.core.Engine;
import com.serpics.core.facade.Populator;


public class CategoryPopulator implements Populator<Category, CategoryData>    {
 
	Engine<CommerceSessionContext> engine;
	
	@Autowired
	CategoryService categoryService;
	
	@Override
	public void populate(Category source, CategoryData target)  {
		target.setUuid(source.getUuid());
		target.setId(source.getId());
		target.setCreated(source.getCreated());
		target.setUpdated(source.getUpdated());
		
		target.setMetaKey(source.getMetaKeyword());
		target.setMetaDescription(source.getMetaDescription());
		
		target.setCode(source.getCode());
		
		target.setPublished(source.getPublished());
		
		target.setUrl(source.getUrl());
		target.setCatalogId(source.getCatalog().getCode());
		String locale = engine.getCurrentContext().getLocale().getLanguage();
		
		target.setChildCategoryNumber(categoryService.getCountChildCategory(source));
		
		target.setChildProductNumber(categoryService.getCountChildProduct(source));
		
		if(source.getDescription() != null)
			target.setDescription(source.getDescription().getText(locale));
	}

	public Engine<CommerceSessionContext> getEngine() {
		return engine;
	}

	public void setEngine(Engine<CommerceSessionContext> engine) {
		this.engine = engine;
	}

}
