package com.serpics.catalog.data.interceptors;

import org.springframework.beans.factory.annotation.Autowired;

import com.serpics.catalog.data.model.AbstractProduct;
import com.serpics.catalog.services.CatalogService;
import com.serpics.commerce.core.CommerceEngine;
import com.serpics.core.data.CreateInterceptor;
import com.serpics.core.data.model.Catalog;

public class ProductCreateInterceptor implements CreateInterceptor<AbstractProduct>{
	
	@Autowired
	CommerceEngine engine;
	
	@Autowired
	CatalogService catalogService;
	
	@Override
	public void beforeCreate(AbstractProduct entity) {
		Catalog  _c = engine.getCurrentContext().getCatalog();
		com.serpics.catalog.data.model.Catalog c = catalogService.findOne(_c.getId());
		entity.setCatalog(c);
	}

	@Override
	public void afterCreate(AbstractProduct entity) {
		// TODO Auto-generated method stub
		
	}

}
