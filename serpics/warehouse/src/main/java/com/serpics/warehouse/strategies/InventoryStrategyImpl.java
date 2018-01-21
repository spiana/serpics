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

import java.util.List;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.serpics.base.data.model.Store;
import com.serpics.base.strategies.AbstractStrategy;
import com.serpics.catalog.data.model.AbstractProduct;
import com.serpics.stereotype.StoreStrategy;
import com.serpics.warehouse.InventoryNotAvailableException;
import com.serpics.warehouse.InventoryStatus;
import com.serpics.warehouse.InventoryStatusEnum;
import com.serpics.warehouse.data.model.Inventory;
import com.serpics.warehouse.data.model.Warehouse;
import com.serpics.warehouse.data.model.WarehouseStoreConfig;
import com.serpics.warehouse.data.repositories.InventoryRepository;
import com.serpics.warehouse.data.repositories.WarehouseStoreConfigRepository;

@StoreStrategy("inventoryStrategy")
@Transactional(readOnly= true)
public class InventoryStrategyImpl extends AbstractStrategy implements InventoryStrategy  {

	@Resource
	private InventoryRepository inventoryRepository;

	@Resource
	private WarehouseStoreConfigRepository warehouseStoreConfigRepository;
	
	
	
	@Override
	public InventoryStatus checkInventory(AbstractProduct product , double quantity) {
		WarehouseStoreConfig storeConfig = getStoreConfig();
		if (storeConfig.isAlwaysInstock())
			return InventoryStatusEnum.InStock;
		
		Double available = inventoryRepository.getAvailable(product,(Store) getCurrentContext().getStoreRealm());
		if (available == null)
			available = 0.0D;
		
		if (available <= 0)
			return InventoryStatusEnum.OutOfStock;
		else if (available < storeConfig.getStoreThreshold())
			return InventoryStatusEnum.LowStock;
		else
			return InventoryStatusEnum.InStock;
	}

	public WarehouseStoreConfig getStoreConfig(){
//		if (this.storeConfig != null)
//			return storeConfig;
//		
		List<WarehouseStoreConfig> storeConfigList =  warehouseStoreConfigRepository.findAll();
		WarehouseStoreConfig storeConfig = !storeConfigList.isEmpty() ?storeConfigList.get(0): new WarehouseStoreConfig();
//		this.storeConfig= storeConfig;
		return storeConfig;
	}
	
	@Override
	public double getStockLevelAmount(AbstractProduct product) {
		if(getStoreConfig().isAlwaysInstock() )
			return Integer.MAX_VALUE;
			
		return inventoryRepository.getAvailable(product,(Store) getCurrentContext().getStoreRealm());
		
	}
	
	@Override
	public InventoryStatus getInventoryStatus(AbstractProduct product ) {
		WarehouseStoreConfig storeConfig = getStoreConfig();
		if (storeConfig.isAlwaysInstock())
			return InventoryStatusEnum.InStock;
		
		Double available = inventoryRepository.getAvailable(product,(Store) getCurrentContext().getStoreRealm());
		if (available == null)
			available = 0.0D;
		
		if (available <= 0)
			return InventoryStatusEnum.OutOfStock;
		else if (available < storeConfig.getStoreThreshold())
			return InventoryStatusEnum.LowStock;
		else
			return InventoryStatusEnum.InStock;
	}

	@Override
	public double getStockLevelAmount(AbstractProduct product ,Warehouse warehouse) {
		if (BooleanUtils.isTrue(warehouse.getForceInStock()))
			return Integer.MAX_VALUE;
		
		return inventoryRepository.getAvailableByWarehouse(product, warehouse);
	}
	@Override
	public InventoryStatus getInventoryStatus(AbstractProduct product,Warehouse warehouse) {
		if (BooleanUtils.isTrue(warehouse.getForceInStock()))
			return InventoryStatusEnum.InStock;
		
		Double available = inventoryRepository.getAvailableByWarehouse(product, warehouse);
		if (available == null)
			available = 0.0D;
		
		if (available <= 0)
			return InventoryStatusEnum.OutOfStock;
		else if (available < NumberUtils.max(0,warehouse.getInventoryThreshold() ))
			return InventoryStatusEnum.LowStock;
		else
			return InventoryStatusEnum.InStock;
	
	}


	@Override
	@Transactional
	public void reserve(AbstractProduct product, double quantity,
			Warehouse warehouse) throws InventoryNotAvailableException {
		
		if (warehouse.getForceInStock())
			return ;
		
		Inventory inventory = findInventory(product, warehouse);
		Assert.notNull(inventory, 
				String.format("inventory not found for product %s and warehouse %s" ,product.getCode(), warehouse.getName()));
	inventory.setReserved(inventory.getReserved()+quantity);
		
	}


	@Override
	@Transactional
	public void release(final AbstractProduct product, final double quantity,
			final Warehouse warehouse) {
			if (warehouse.getForceInStock())
				return ;
		
			Inventory inventory = findInventory(product, warehouse);
			Assert.notNull(inventory, 
					String.format("inventory not found for product %s and warehouse %s" ,product.getCode(), warehouse.getName()));
		inventory.setReserved(inventory.getReserved()-quantity);
		
	}


	

	private Inventory  findInventory(final AbstractProduct product , final Warehouse warehouse  ){
		return  inventoryRepository.findOne(new Specification<Inventory>() {
			@Override
			public Predicate toPredicate(Root<Inventory> root, CriteriaQuery<?> cq,
					CriteriaBuilder cb) {
				Predicate p = cb.equal(root.get("warehouse"), warehouse);
				Predicate p1 = cb.equal(root.get("product"), product);
				return cb.and(p ,p1);
			}
		});
	}

	@Override
	public double getStockLevelReserve(AbstractProduct product, Warehouse warehouse) {
		if (warehouse.getForceInStock())
			return Integer.MAX_VALUE;
				
		return inventoryRepository.getReservedByWarehouse(product, warehouse);
	}
	
}
