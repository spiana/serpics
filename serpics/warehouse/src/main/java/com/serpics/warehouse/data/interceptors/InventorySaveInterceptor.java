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
package com.serpics.warehouse.data.interceptors;

import javax.annotation.Resource;

import com.serpics.base.data.model.Store;
import com.serpics.commerce.core.CommerceEngine;
import com.serpics.core.data.SaveInterceptor;
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
