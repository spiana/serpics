package com.serpics.commerce.data.interceptors;

import javax.annotation.Resource;

import org.springframework.util.Assert;

import com.serpics.commerce.core.CommerceEngine;
import com.serpics.commerce.data.model.Paymethodlookup;
import com.serpics.commerce.data.model.PaymethodlookupPK;
import com.serpics.core.data.CreateInterceptor;
import com.serpics.membership.data.model.Store;


public class PaymethodlookupInterceptor implements CreateInterceptor<Paymethodlookup> {

	@Resource
	CommerceEngine commerceEngine;
	
	@Override
	public void beforeCreate(Paymethodlookup entity) {
		Assert.notNull(entity.getPaymethod() , "paymethod must not be null !");
		
		if(entity.getStore() == null){
			Store s = (Store) commerceEngine.getCurrentContext().getStoreRealm();
			entity.setStore(s);
		}
		if (entity.getId() == null){
			PaymethodlookupPK pk = new PaymethodlookupPK();
			pk.setPaymethodId(entity.getPaymethod().getPaymethodId());
			pk.setStoreId(entity.getStore().getId());
			entity.setId( pk);
		}
			
	}

	@Override
	public void afterCreate(Paymethodlookup entity) {
		
		
	}

}
