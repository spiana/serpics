package com.serpics.membership.interceptor;

import org.springframework.beans.factory.annotation.Autowired;

import com.serpics.commerce.core.CommerceEngine;
import com.serpics.core.data.CreateInterceptor;
import com.serpics.membership.persistence.Membergroup;
import com.serpics.membership.persistence.Store;

public class membergroupCreateInterceptor implements CreateInterceptor<Membergroup> {

	@Autowired
	CommerceEngine engine;
	
	@Override
	public void beforeCreate(Membergroup entity) {
			entity.setStore((Store) engine.getCurrentContext().getStoreRealm());
		
	}

	@Override
	public void afterCreate(Membergroup entity) {
		// TODO Auto-generated method stub
		
	}

}
