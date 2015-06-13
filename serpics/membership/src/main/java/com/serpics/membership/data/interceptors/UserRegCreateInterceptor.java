package com.serpics.membership.data.interceptors;

import javax.annotation.Resource;

import com.serpics.commerce.core.CommerceEngine;
import com.serpics.core.data.CreateInterceptor;
import com.serpics.membership.data.model.Store;
import com.serpics.membership.data.model.UsersReg;

public class UserRegCreateInterceptor  implements CreateInterceptor<UsersReg> {

	@Resource(name ="commerceEngine")
	CommerceEngine ce ;
	
	@Override
	public void beforeCreate(UsersReg entity) {
		entity.getStores().add((Store) ce.getCurrentContext().getStoreRealm());
		
	}

	@Override
	public void afterCreate(UsersReg entity) {
		// TODO Auto-generated method stub
		
	}

}
