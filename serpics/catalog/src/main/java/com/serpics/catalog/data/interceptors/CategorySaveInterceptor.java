package com.serpics.catalog.data.interceptors;

import org.springframework.beans.factory.annotation.Autowired;

import com.serpics.catalog.data.model.Category;
import com.serpics.catalog.services.CatalogService;
import com.serpics.commerce.core.CommerceEngine;
import com.serpics.core.data.SaveInterceptor;

public class CategorySaveInterceptor implements SaveInterceptor<Category>{
	
	@Autowired
	CommerceEngine engine;
	
	@Autowired
	CatalogService catalogService;
	
	@Override
	public void beforeSave(Category entity) {
		com.serpics.core.data.model.Catalog  _c = engine.getCurrentContext().getCatalog();
		com.serpics.catalog.data.model.Catalog c = catalogService.findOne(_c.getId());
		entity.setCatalog(c);
	}

	@Override
	public void afterSave(Category entity) {
		// TODO Auto-generated method stub
		
	}

}
