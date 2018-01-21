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

import javax.annotation.Resource;

import com.serpics.base.commerce.CommerceEngine;
import com.serpics.base.data.model.Store;
import com.serpics.catalog.data.model.Catalog2StoreRelation;
import com.serpics.catalog.data.model.Catalog2StoreRelPK;
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
