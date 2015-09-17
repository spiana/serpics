package com.serpics.membership.data.interceptors;

import org.springframework.beans.factory.annotation.Autowired;

import com.serpics.commerce.core.CommerceEngine;
import com.serpics.core.data.SaveInterceptor;
import com.serpics.membership.data.model.Membergroup;
import com.serpics.membership.data.model.Store;

public class MembergroupSaveInterceptor implements SaveInterceptor<Membergroup> {

	@Autowired
	CommerceEngine engine;
	
	@Override
	public void beforeSave(Membergroup entity) {
			entity.setStore((Store) engine.getCurrentContext().getStoreRealm());
		
	}

	@Override
	public void afterSave(Membergroup entity) {
		// TODO Auto-generated method stub
		
	}

}
