package com.serpics.membership.data.interceptors;

import javax.annotation.Resource;

import com.serpics.base.data.model.AbstractStoreEntity;
import com.serpics.base.data.model.Store;
import com.serpics.commerce.core.CommerceEngine;
import com.serpics.core.data.SaveInterceptor;

public class AbstractStoreEntitySaveInterceptor implements SaveInterceptor<AbstractStoreEntity>{

	@Resource(name="commerceEngine")
	CommerceEngine engine;
	
	@Override
	public void beforeSave(AbstractStoreEntity entity) {
			if(entity.getStore() == null){
				entity.setStore((Store) engine.getCurrentContext().getStoreRealm());
			}
		
	}

	@Override
	public void afterSave(AbstractStoreEntity entity) {
	
		
	}

}
