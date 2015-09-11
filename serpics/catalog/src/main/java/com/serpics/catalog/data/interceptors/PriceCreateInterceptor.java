package com.serpics.catalog.data.interceptors;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.serpics.base.data.model.Currency;
import com.serpics.catalog.data.model.Catalog;
import com.serpics.catalog.data.model.Price;
import com.serpics.catalog.data.model.Pricelist;
import com.serpics.catalog.data.repositories.PriceListRepository;
import com.serpics.commerce.core.CommerceEngine;
import com.serpics.core.data.CreateInterceptor;

public class PriceCreateInterceptor implements CreateInterceptor<Price> {

	@Autowired
	CommerceEngine engine;
	
	@Autowired
	PriceListRepository priceListRepository;
	
	@Override
	public void beforeCreate(Price entity) {
		 if (entity.getCurrency() == null) {
	            final Currency currency = (Currency) engine.getCurrentContext().getCurrency();
	            entity.setCurrency(currency);
	        }
	        if (entity.getPricelist() == null) {
	            final List<Pricelist> l = priceListRepository.findDefaultList((Catalog) engine.getCurrentContext().getCatalog());
	            assert !l.isEmpty() : "missing default price list !";
	            entity.setPricelist(l.get(0));
	        }
		
	        if (entity.getPrecedence() == null){
	        	entity.setPrecedence(0.0);
	        }
	}

	@Override
	public void afterCreate(Price entity) {
		// TODO Auto-generated method stub
		
	}

}
