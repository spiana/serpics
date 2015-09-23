package com.serpics.catalog.data.interceptors;

import org.springframework.beans.factory.annotation.Autowired;

import com.serpics.catalog.data.model.FeatureModel;
import com.serpics.catalog.services.CatalogService;
import com.serpics.commerce.core.CommerceEngine;
import com.serpics.core.data.SaveInterceptor;
import com.serpics.core.data.model.Catalog;

public class FeatureModelSaveInterceptor implements SaveInterceptor<FeatureModel>{
	
	@Autowired
	CommerceEngine engine;
	
	@Autowired
	CatalogService catalogService;
	
	@Override
	public void beforeSave(FeatureModel entity) {
		if (entity.getCatalog() == null){
			Catalog  _c = engine.getCurrentContext().getCatalog();
			entity.setCatalog((com.serpics.catalog.data.model.Catalog)_c);
		}
	}

	

	@Override
	public void afterSave(FeatureModel entity) {
		// TODO Auto-generated method stub
		
	}

}
