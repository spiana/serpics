package com.serpics.commerce.data.interceptors;

import org.springframework.beans.factory.annotation.Autowired;

import com.serpics.commerce.core.CommerceEngine;
import com.serpics.commerce.data.model.AbstractOrder;
import com.serpics.core.data.SaveInterceptor;
import com.serpics.membership.data.model.Store;
import com.serpics.membership.data.model.UsersReg;


public class AbstractOrderSaveInterceptor implements SaveInterceptor<AbstractOrder> {

	@Autowired
	CommerceEngine engine;

	@Override
	public void beforeSave(AbstractOrder entity) {
		if (entity.getCookie() == null)
			entity.setCookie("no-cookie");
		if(entity.getOrderAmount() == null)
			entity.setOrderAmount(0D);
		
		if (entity.getStore() == null)
			entity.setStore((Store)engine.getCurrentContext().getStoreRealm());
			
		if (entity.getUser() == null)
			entity.setUser((UsersReg) engine.getCurrentContext().getUserPrincipal() );
		
	}

	@Override
	public void afterSave(AbstractOrder entity) {
		// TODO Auto-generated method stub
		
	}
	
	
}
