package com.serpics.base.data.interceptor;

import javax.annotation.Resource;

import com.serpics.base.data.model.Media;
import com.serpics.commerce.core.CommerceEngine;
import com.serpics.core.data.SaveInterceptor;

public class MediaSaveInterceptor implements SaveInterceptor<Media> {

	@Resource
	CommerceEngine engine;
	
	@Override
	public void beforeSave(Media entity) {
		entity.setStoreId(engine.getCurrentContext().getStoreId());
		
	}

	@Override
	public void afterSave(Media entity) {
	
		
	}

}
