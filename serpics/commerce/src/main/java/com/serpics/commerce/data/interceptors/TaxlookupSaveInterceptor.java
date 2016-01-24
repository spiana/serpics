package com.serpics.commerce.data.interceptors;

import javax.annotation.Resource;

import org.springframework.util.Assert;

import com.serpics.base.data.model.Store;
import com.serpics.commerce.core.CommerceEngine;
import com.serpics.commerce.data.model.Taxlookup;
import com.serpics.commerce.data.model.TaxlookupPK;
import com.serpics.core.data.SaveInterceptor;


public class TaxlookupSaveInterceptor implements SaveInterceptor<Taxlookup> {

	@Resource
	CommerceEngine commerceEngine;
	
	@Override
	public void beforeSave(Taxlookup entity) {
		Assert.notNull(entity.getTax() , "tax must not be null !");
		
		if(entity.getStore() == null){
			Store s = (Store) commerceEngine.getCurrentContext().getStoreRealm();
			entity.setStore(s);
		}
		if (entity.getId() == null){
			TaxlookupPK pk = new TaxlookupPK();
			pk.setTaxesId(entity.getTax().getId());
			pk.setStoreId(entity.getStore().getId());
			entity.setId(pk);
		}
			
	}

	@Override
	public void afterSave(Taxlookup entity) {
		
		
	}

}
