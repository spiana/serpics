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
package com.serpics.warehouse.strategies;

import com.serpics.catalog.data.model.AbstractProduct;
import com.serpics.warehouse.InventoryNotAvailableException;
import com.serpics.warehouse.InventoryStatus;
import com.serpics.warehouse.data.model.Warehouse;
import com.serpics.warehouse.data.model.WarehouseStoreConfig;

public interface InventoryStrategy {
	public InventoryStatus checkInventory(AbstractProduct product , double quantity);
	
	
	public void reserve(AbstractProduct product , double quantity , Warehouse warehouse) throws InventoryNotAvailableException;
	public void release(AbstractProduct product , double quantity , Warehouse warehouse) ;
	
	public double getStockLevelAmount(AbstractProduct product);
	public double getStockLevelAmount(AbstractProduct product, Warehouse warehouse);
	
	public double getStockLevelReserve(AbstractProduct product, Warehouse warehouse);
	
	public InventoryStatus getInventoryStatus(AbstractProduct product);
	public InventoryStatus getInventoryStatus(AbstractProduct product , Warehouse warehouse);
	
	public WarehouseStoreConfig getStoreConfig();

}
