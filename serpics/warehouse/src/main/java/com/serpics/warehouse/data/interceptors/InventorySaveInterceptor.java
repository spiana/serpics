package com.serpics.warehouse.data.interceptors;

import javax.annotation.Resource;

import com.serpics.commerce.core.CommerceEngine;
import com.serpics.core.data.SaveInterceptor;
import com.serpics.membership.data.model.Store;
import com.serpics.warehouse.data.model.Inventory;

public class InventorySaveInterceptor implements SaveInterceptor<Inventory>{
	@Resource
	CommerceEngine commerceEngine;

	@Override
	public void beforeSave(Inventory entity) {
		if (entity.getStore() ==null){
			entity.setStore((Store) commerceEngine.getCurrentContext().getStoreRealm());
		}
		
	}

	@Override
	public void afterSave(Inventory entity) {
		// TODO Auto-generated method stub
		
	}

}
