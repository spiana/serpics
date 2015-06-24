package com.serpics.membership.data.interceptors;

import javax.annotation.Resource;

import com.serpics.commerce.core.CommerceEngine;
import com.serpics.core.data.CreateInterceptor;
import com.serpics.membership.data.model.Store;
import com.serpics.membership.data.model.User;
import com.serpics.membership.data.repositories.StoreRepository;

public class UserCreateInterceptor  implements CreateInterceptor<User> {

	@Resource(name ="commerceEngine")
	CommerceEngine ce ;
	
	@Resource
	StoreRepository storeRepository;
	
	@Override
	public void beforeCreate(User entity) {
		Store _s = storeRepository.findOne(ce.getCurrentContext().getStoreId());
		entity.getStores().add(_s);
		
	}

	@Override
	public void afterCreate(User entity) {
		// TODO Auto-generated method stub
		
	}

}
