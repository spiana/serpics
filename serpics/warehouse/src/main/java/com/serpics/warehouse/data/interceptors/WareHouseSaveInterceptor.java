package com.serpics.warehouse.data.interceptors;

import javax.annotation.Resource;

import com.serpics.commerce.core.CommerceEngine;
import com.serpics.core.data.SaveInterceptor;
import com.serpics.membership.data.model.Store;
import com.serpics.warehouse.data.model.Warehouse;

public class WareHouseSaveInterceptor implements SaveInterceptor<Warehouse>{
	@Resource
	CommerceEngine commerceEngine;

	@Override
	public void beforeSave(Warehouse entity) {
		if (entity.getStore() ==null){
			entity.setStore((Store) commerceEngine.getCurrentContext().getStoreRealm());
		}
		
	}

	@Override
	public void afterSave(Warehouse entity) {
		// TODO Auto-generated method stub
		
	}

}
