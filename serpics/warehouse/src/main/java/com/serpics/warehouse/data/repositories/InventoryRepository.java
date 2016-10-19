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
package com.serpics.warehouse.data.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.serpics.base.data.model.Store;
import com.serpics.catalog.data.model.AbstractProduct;
import com.serpics.core.data.Repository;
import com.serpics.warehouse.data.model.Inventory;
import com.serpics.warehouse.data.model.Warehouse;

public interface InventoryRepository extends Repository<Inventory, Long>{
	
	@Query("select coalesce(sum(i.available - i.reserved), 0) from Inventory i join i.warehouse w  where w.store=:store and i.product=:product")
	public Double getAvailable(@Param("product")AbstractProduct product , @Param("store") Store store);
	
	
	@Query("select coalesce(sum(i.available - i.reserved),0) from Inventory i where i.warehouse=:warehouse and i.product=:product")
	public Double getAvailableByWarehouse(@Param("product")AbstractProduct product , @Param("warehouse") Warehouse warehouse);
	
	@Query("select coalesce(i.reserved,0) from Inventory i where i.warehouse=:warehouse and i.product=:product")
	public Double getReservedByWarehouse(@Param("product")AbstractProduct product , @Param("warehouse") Warehouse warehouse);

	
}
