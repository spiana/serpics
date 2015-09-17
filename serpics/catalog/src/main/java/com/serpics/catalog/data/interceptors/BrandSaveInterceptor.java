package com.serpics.catalog.data.interceptors;

import org.springframework.beans.factory.annotation.Autowired;

import com.serpics.catalog.data.model.Brand;
import com.serpics.catalog.services.CatalogService;
import com.serpics.commerce.core.CommerceEngine;
import com.serpics.core.data.SaveInterceptor;
import com.serpics.core.data.model.Catalog;

public class BrandSaveInterceptor implements SaveInterceptor<Brand>{
	
	@Autowired
	CommerceEngine engine;
	
	@Autowired
	CatalogService catalogService;
	
	@Override
	public void beforeSave(Brand entity) {
		Catalog  _c = engine.getCurrentContext().getCatalog();
		com.serpics.catalog.data.model.Catalog c = catalogService.findOne(_c.getId());
		entity.setCatalog(c);
	}

	@Override
	public void afterSave(Brand entity) {
		// TODO Auto-generated method stub
		
	}

}
