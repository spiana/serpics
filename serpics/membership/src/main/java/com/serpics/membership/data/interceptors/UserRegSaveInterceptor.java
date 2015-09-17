package com.serpics.membership.data.interceptors;

import javax.annotation.Resource;

import com.serpics.commerce.core.CommerceEngine;
import com.serpics.core.data.SaveInterceptor;
import com.serpics.membership.UserType;
import com.serpics.membership.data.model.Store;
import com.serpics.membership.data.model.UsersReg;
import com.serpics.membership.data.repositories.StoreRepository;

public class UserRegSaveInterceptor  implements SaveInterceptor<UsersReg> {

	@Resource(name ="commerceEngine")
	CommerceEngine ce ;
	
	@Resource
	StoreRepository storeRepository;
	
	@Override
	public void beforeSave(UsersReg entity) {
		if(entity.getUserType() != UserType.SUPERSUSER){
			Store _s = storeRepository.findOne(ce.getCurrentContext().getStoreId());
			entity.getStores().add(_s);
		}
		
	}

	@Override
	public void afterSave(UsersReg entity) {
		// TODO Auto-generated method stub
		
	}

}
