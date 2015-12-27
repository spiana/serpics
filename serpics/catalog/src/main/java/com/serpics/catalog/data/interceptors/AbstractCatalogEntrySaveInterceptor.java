package com.serpics.catalog.data.interceptors;

import org.springframework.beans.factory.annotation.Autowired;

import com.serpics.catalog.data.model.AbstractCatalogEntry;
import com.serpics.catalog.services.CatalogService;
import com.serpics.commerce.core.CommerceEngine;
import com.serpics.core.data.SaveInterceptor;
import com.serpics.core.data.model.Catalog;

public class AbstractCatalogEntrySaveInterceptor implements SaveInterceptor<AbstractCatalogEntry>{
	
	@Autowired
	CommerceEngine engine;
	
	@Autowired
	CatalogService catalogService;
	
	@Override
	public void beforeSave(AbstractCatalogEntry entity) {
		if(entity.getCatalog() == null){
		Catalog  c = engine.getCurrentContext().getCatalog();
			//com.serpics.catalog.data.model.Catalog c = catalogService.findOne(_c.getId());
			entity.setCatalog((com.serpics.catalog.data.model.Catalog)c);
		}
	}

	@Override
	public void afterSave(AbstractCatalogEntry entity) {
		// TODO Auto-generated method stub
		
	}

}
