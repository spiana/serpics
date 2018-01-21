/*******************************************************************************
 * Copyright 2014-2016  StepFour Srl
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *  
 *      http://www.apache.org/licenses/LICENSE-2.0
 *  
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *  
 *******************************************************************************/
package com.serpics.catalog.data.interceptors;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.serpics.base.commerce.CommerceEngine;
import com.serpics.base.data.model.Store;
import com.serpics.catalog.data.model.Price;
import com.serpics.catalog.data.model.Pricelist;
import com.serpics.catalog.data.repositories.PriceListRepository;
import com.serpics.core.data.SaveInterceptor;
import com.serpics.i18n.data.model.Currency;

public class PriceSaveInterceptor implements SaveInterceptor<Price> {

	@Autowired
	CommerceEngine engine;
	
	@Autowired
	PriceListRepository priceListRepository;
	
	@Override
	public void beforeSave(Price entity) {
		 if (entity.getCurrency() == null) {
	            final Currency currency = (Currency) engine.getCurrentContext().getCurrency();
	            entity.setCurrency(currency);
	        }
	        if (entity.getPricelist() == null) {
	            final List<Pricelist> l = priceListRepository.findDefaultList((Store) engine.getCurrentContext().getStoreRealm());
	            assert !l.isEmpty() : "missing default price list !";
	            entity.setPricelist(l.get(0));
	        }
		
	        if (entity.getPrecedence() == null){
	        	entity.setPrecedence(0.0);
	        }
	}

	@Override
	public void afterSave(Price entity) {
		// TODO Auto-generated method stub
		
	}

}
