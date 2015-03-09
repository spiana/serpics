package com.serpics.catalog.repositories.interceptors;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.serpics.base.persistence.Currency;
import com.serpics.catalog.persistence.Catalog;
import com.serpics.catalog.persistence.Price;
import com.serpics.catalog.persistence.Pricelist;
import com.serpics.catalog.repositories.PriceListRepository;
import com.serpics.commerce.core.CommerceEngine;
import com.serpics.core.data.CreateInterceptor;
import com.serpics.membership.persistence.Store;

public class PriceCreateInterceptor implements CreateInterceptor<Price> {

	@Autowired
	CommerceEngine engine;
	
	@Autowired
	PriceListRepository priceListRepository;
	
	@Override
	public void beforeCreate(Price entity) {
		 if (entity.getCurrency() == null) {
	            final Currency currency = ((Store) engine.getCurrentContext().getStoreRealm()).getCurrency();
	            entity.setCurrency(currency);
	        }
	        if (entity.getPricelist() == null) {
	            final List<Pricelist> l = priceListRepository.findDefaultList((Catalog) engine.getCurrentContext().getCatalog());
	            assert !l.isEmpty() : "missing default price list !";
	            entity.setPricelist(l.get(0));
	        }
		
	}

	@Override
	public void afterCreate(Price entity) {
		// TODO Auto-generated method stub
		
	}

}
