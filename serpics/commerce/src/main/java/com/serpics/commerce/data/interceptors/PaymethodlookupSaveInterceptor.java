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
package com.serpics.commerce.data.interceptors;

import javax.annotation.Resource;

import org.springframework.util.Assert;

import com.serpics.base.commerce.CommerceEngine;
import com.serpics.base.data.model.Store;
import com.serpics.commerce.data.model.Paymethodlookup;
import com.serpics.commerce.data.model.PaymethodlookupPK;
import com.serpics.core.data.SaveInterceptor;


public class PaymethodlookupSaveInterceptor implements SaveInterceptor<Paymethodlookup> {

	@Resource
	CommerceEngine commerceEngine;
	
	@Override
	public void beforeSave(Paymethodlookup entity) {
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
	public void afterSave(Paymethodlookup entity) {
		
		
	}

}
