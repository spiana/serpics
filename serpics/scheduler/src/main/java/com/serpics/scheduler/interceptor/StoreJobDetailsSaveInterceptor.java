package com.serpics.scheduler.interceptor;

import javax.annotation.Resource;

import com.serpics.base.data.model.Store;
import com.serpics.commerce.core.CommerceEngine;
import com.serpics.core.data.SaveInterceptor;
import com.serpics.scheduler.model.StoreJobDetails;

public class StoreJobDetailsSaveInterceptor implements SaveInterceptor<StoreJobDetails> {

	@Resource
	CommerceEngine engine;
	
	@Override
	public void beforeSave(StoreJobDetails entity) {
		if(entity.getStore() == null){
			entity.setStore((Store) engine.getCurrentContext().getStoreRealm());
		}
		
	}

	@Override
	public void afterSave(StoreJobDetails entity) {
		
	}

}
