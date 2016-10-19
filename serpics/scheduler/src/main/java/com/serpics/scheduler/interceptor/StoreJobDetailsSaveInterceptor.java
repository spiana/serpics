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
package com.serpics.scheduler.interceptor;

import javax.annotation.Resource;

import com.serpics.base.data.model.Store;
import com.serpics.commerce.core.CommerceEngine;
import com.serpics.core.data.SaveInterceptor;
import com.serpics.scheduler.model.StoreJobDetails;

public class StoreJobDetailsSaveInterceptor implements SaveInterceptor<StoreJobDetails> {

	@Resource
	CommerceEngine engine;
	
	@Override
	public void beforeSave(StoreJobDetails entity) {
		if(entity.getStore() == null){
			entity.setStore((Store) engine.getCurrentContext().getStoreRealm());
		}
		
	}

	@Override
	public void afterSave(StoreJobDetails entity) {
		
	}

}
