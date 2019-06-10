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
package com.serpics.warehouse.service;

import java.util.List;

import javax.annotation.Resource;

import com.serpics.base.commerce.session.CommerceSessionContext;
import com.serpics.base.data.model.Store;
import com.serpics.catalog.data.model.AbstractProduct;
import com.serpics.core.service.AbstractService;
import com.serpics.stereotype.StoreService;
import com.serpics.warehouse.data.model.Warehouse;
import com.serpics.warehouse.data.repositories.WarehouseRepository;

@StoreService("warehouseService")
public class WarehouseServiceImpl extends AbstractService<CommerceSessionContext> implements WareHouseService {
	
	@Resource
	WarehouseRepository warehouseRepository;

	@Override
	public Warehouse findPreferredForReserve(AbstractProduct  product , Double needed) {
		List<Warehouse> _w = warehouseRepository.findPreferredForReserve(product, (Store) getCurrentContext().getStoreRealm() , needed);
		if (_w.isEmpty())
		 return null;

		return _w.get(0); 
		
	}

	@Override
	public Warehouse  findPreferredForRelease(AbstractProduct product , Double needed) {
		List<Warehouse> _w = warehouseRepository.findPreferredForRelease(product, (Store) getCurrentContext().getStoreRealm() , needed);
		if (_w.isEmpty())
		 return null;

		return _w.get(0); 
	}

	@Override
	public Warehouse findPreferredForced() {
		List<Warehouse> _w = warehouseRepository.findPreferredForced((Store) getCurrentContext().getStoreRealm());
		if (_w.isEmpty())
			 return null;

		return _w.get(0); 
	}

}
