package com.serpics.catalog.data.interceptors;

import org.springframework.beans.factory.annotation.Autowired;

import com.serpics.catalog.data.model.Category;
import com.serpics.catalog.services.CatalogService;
import com.serpics.commerce.core.CommerceEngine;
import com.serpics.core.data.CreateInterceptor;

public class CategoryCreateInterceptor implements CreateInterceptor<Category>{
	
	@Autowired
	CommerceEngine engine;
	
	@Autowired
	CatalogService catalogService;
	
	@Override
	public void beforeCreate(Category entity) {
		com.serpics.core.data.model.Catalog  _c = engine.getCurrentContext().getCatalog();
		com.serpics.catalog.data.model.Catalog c = catalogService.findOne(_c.getCatalogId());
		entity.setCatalog(c);
	}

	@Override
	public void afterCreate(Category entity) {
		// TODO Auto-generated method stub
		
	}

}
