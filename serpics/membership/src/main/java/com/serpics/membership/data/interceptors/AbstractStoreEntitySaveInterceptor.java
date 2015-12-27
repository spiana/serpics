package com.serpics.membership.data.interceptors;

import javax.annotation.Resource;

import com.serpics.commerce.core.CommerceEngine;
import com.serpics.core.data.SaveInterceptor;
import com.serpics.membership.data.model.AbstractStoreEntity;
import com.serpics.membership.data.model.Store;

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
