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
package com.serpics.commerce.services;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.serpics.base.data.model.Store;
import com.serpics.base.data.repositories.StoreRepository;
import com.serpics.catalog.data.model.Pricelist;
import com.serpics.catalog.data.repositories.PriceListRepository;
import com.serpics.catalog.services.CatalogService;
import com.serpics.commerce.core.CommerceEngine;
import com.serpics.commerce.session.CommerceSessionContext;
import com.serpics.core.session.SessionContext;
import com.serpics.membership.UserType;
import com.serpics.membership.data.model.User;
import com.serpics.membership.data.repositories.UserRepository;

/**
 * @author spiana
 *
 */
@Service("storeService")
@Transactional(readOnly=true)
public class StoreServiceImpl implements StoreService {

	@Resource
	CatalogService catalogService;
	
	@Resource
	StoreRepository storerepository;
	
	@Resource
	UserRepository userRepository;
	
	@Resource
	PriceListRepository priceListrepository;
	
	@Resource
	CommerceEngine engine;
	
	/* (non-Javadoc)
	 * @see com.serpics.base.services.StoreService#createStore(java.lang.String)
	 */
	@Override
	@Transactional
	public void createStore(String storeName) {
		Assert.notNull(storeName);
		String _newstoreName = storeName +"-store";
		
		Store s = new Store();
		s.setName(storeName);
		
		createStore(s);
	}

	/* (non-Javadoc)
	 * @see com.serpics.commerce.services.StoreService#createStore(com.serpics.base.data.model.Store)
	 */
	@Override
	@Transactional
	public void createStore(Store store) {
		
		if(!store.getName().endsWith("-store"))
			store.setName(store.getName()+ "-store");
	
		String storeName= store.getName().substring(0, store.getName().indexOf("-store"));
				
		Store s = storerepository.findByname(store.getName());
		Assert.isNull(s , "Store already exist in the system !");
		
	
		String catalogName =  storeName + "-catalog";
		String listName = storeName + "-list";
		
		SessionContext _s = engine.getCurrentContext();
		try{
			engine.unbind();
			storerepository.save(store);
			CommerceSessionContext context = engine.connect(store.getName());
			catalogService.initialize(catalogName);
			
			Pricelist priceList = new Pricelist();
			priceList.setName(listName);
			priceList.setDefaultList(true);
			priceList.setStore(store);
			priceListrepository.saveAndFlush(priceList);
			
			engine.disconnect(context);
		}catch(Exception e){
			throw new RuntimeException(e);
		}finally{
			if (_s != null)
				engine.bind(_s.getSessionId());
		}
		
	}

	@Override
	public List<Store> findAllStoreAvailable() {
		User u = (User) engine.getCurrentContext().getUserPrincipal();
		u = userRepository.refresh(u);
		if (u.getUserType().equals(UserType.SUPERSUSER))
			return storerepository.findAll();
		else
			return  new ArrayList<>(u.getStores());
	}
}
