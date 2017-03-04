package com.serpics.catalog.data.interceptors;

import org.springframework.beans.factory.annotation.Autowired;

import com.serpics.catalog.data.model.CtentryMedia;
import com.serpics.catalog.services.CatalogService;
import com.serpics.commerce.core.CommerceEngine;
import com.serpics.core.data.SaveInterceptor;
import com.serpics.core.data.model.Catalog;
import com.serpics.stereotype.ModelInterceptor;

@ModelInterceptor(value=CtentryMedia.class)
public class CtentryMediaSaveInterceptor implements SaveInterceptor<CtentryMedia>{
	@Autowired
	CommerceEngine engine;
	
	@Autowired
	CatalogService catalogService;

	@Override
	public void beforeSave(CtentryMedia entity) {
		if(entity.getCatalog() == null){
			Catalog  c = engine.getCurrentContext().getCatalog();
				entity.setCatalog((com.serpics.catalog.data.model.Catalog)c);
			}
		
	}

	@Override
	public void afterSave(CtentryMedia entity) {
		// Nothing
		
	}
	
}
