package com.serpics.catalog.data.interceptors;

import javax.annotation.Resource;

import com.serpics.base.data.model.Store;
import com.serpics.catalog.data.model.Catalog2StoreRelation;
import com.serpics.catalog.data.model.Catalog2StoreRelPK;
import com.serpics.commerce.core.CommerceEngine;
import com.serpics.core.data.SaveInterceptor;

public class Catalog2StoreSaveInterceptor  implements SaveInterceptor<Catalog2StoreRelation>{

	@Resource
	CommerceEngine commerceEngine;
	
	@Override
	public void beforeSave(Catalog2StoreRelation entity) {
		if (entity.getStore() == null)
			entity.setStore((Store)commerceEngine.getCurrentContext().getStoreRealm());
		if (entity.getId() == null) {
            final Catalog2StoreRelPK pk = new Catalog2StoreRelPK();
            pk.setCatalogId(entity.getCatalog().getId());
            pk.setStoreId(entity.getStore().getId());
            entity.setId(pk);
        }
	}

	@Override
	public void afterSave(Catalog2StoreRelation entity) {
		
		
	}

}
