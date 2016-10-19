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

import org.springframework.beans.factory.annotation.Autowired;

import com.serpics.base.data.model.Store;
import com.serpics.commerce.core.CommerceEngine;
import com.serpics.commerce.data.model.AbstractOrder;
import com.serpics.core.data.SaveInterceptor;
import com.serpics.membership.data.model.User;


public class AbstractOrderSaveInterceptor implements SaveInterceptor<AbstractOrder> {

	@Autowired
	CommerceEngine engine;

	@Override
	public void beforeSave(AbstractOrder entity) {
		if (entity.getCookie() == null)
			entity.setCookie("no-cookie");
		if(entity.getOrderAmount() == null)
			entity.setOrderAmount(0D);
		
		if (entity.getStore() == null)
			entity.setStore((Store)engine.getCurrentContext().getStoreRealm());
			
		if (entity.getUser() == null)
			entity.setUser((User) engine.getCurrentContext().getUserPrincipal() );
		
	}

	@Override
	public void afterSave(AbstractOrder entity) {
		// TODO Auto-generated method stub
		
	}
	
	
}
