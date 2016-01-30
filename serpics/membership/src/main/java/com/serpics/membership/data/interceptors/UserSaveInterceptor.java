package com.serpics.membership.data.interceptors;

import javax.annotation.Resource;

import com.serpics.base.data.model.Store;
import com.serpics.base.data.repositories.StoreRepository;
import com.serpics.commerce.core.CommerceEngine;
import com.serpics.core.data.SaveInterceptor;
import com.serpics.membership.UserType;
import com.serpics.membership.data.model.User;

public class UserSaveInterceptor  implements SaveInterceptor<User> {

	@Resource(name ="commerceEngine")
	CommerceEngine ce ;
	
	@Resource
	StoreRepository storeRepository;
	
	@Override
	public void beforeSave(User entity) {
		if(entity.getUserType() != UserType.SUPERSUSER && entity.getUserType() != UserType.ANONYMOUS){
			//Store _s = storeRepository.findOne(ce.getCurrentContext().getStoreId());
			entity.getStores().add((Store)ce.getCurrentContext().getStoreRealm());
		}
		
	}

	@Override
	public void afterSave(User entity) {
		// TODO Auto-generated method stub
		
	}

}
