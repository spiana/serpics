package com.serpics.catalog.data.interceptors;

import com.serpics.catalog.data.model.Feature;
import com.serpics.core.data.SaveInterceptor;

public class FeatureSaveInterceptor implements SaveInterceptor<Feature> {

	@Override
	public void beforeSave(Feature entity) {
		if (entity.getFeatureGroup() != null)
			entity.setFeatureModel(entity.getFeatureGroup().getModel());
		
	}

	@Override
	public void afterSave(Feature entity) {
		// TODO Auto-generated method stub
		
	}

}
