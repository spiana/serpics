package com.serpics.warehouse.strategies;

import java.util.List;

import javax.annotation.Resource;

import com.serpics.base.data.model.Store;
import com.serpics.catalog.data.model.Product;
import com.serpics.commerce.strategies.AbstractStrategy;
import com.serpics.stereotype.StoreStrategy;
import com.serpics.warehouse.InventoryNotAvailableException;
import com.serpics.warehouse.InventoryStatus;
import com.serpics.warehouse.InventoryStatusEnum;
import com.serpics.warehouse.data.model.Warehouse;
import com.serpics.warehouse.data.model.WarehouseStoreConfig;
import com.serpics.warehouse.data.repositories.InventoryRepository;
import com.serpics.warehouse.data.repositories.WarehouseStoreConfigRepository;

@StoreStrategy("inventoryStrategy")
public class InventoryStrategyImpl extends AbstractStrategy implements InventoryStrategy {

	@Resource
	InventoryRepository inventoryRepository;

	@Resource
	WarehouseStoreConfigRepository warehouseStoreConfigRepository;
	
	@Override
	public InventoryStatus checkInventory(Product product , double quantity) {
		List<WarehouseStoreConfig> storeConfigList =  warehouseStoreConfigRepository.findAll();
		WarehouseStoreConfig storeConfig = !storeConfigList.isEmpty() ?storeConfigList.get(0): new WarehouseStoreConfig();
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
	public double getStockLevelAmount(Product product) {
		return Double.MAX_VALUE;
	}
	
	@Override
	public InventoryStatus getInventoryStatus(Product product ) {
		return InventoryStatusEnum.InStock;
	}

	@Override
	public double getStockLevelAmount(Product product ,Warehouse warehouse) {
		return Double.MAX_VALUE;
	}

	@Override
	public InventoryStatus getInventoryStatus(Product product,Warehouse warehouse) {
		return InventoryStatusEnum.InStock;
	}


	@Override
	public void reserve(Product product, double quantity,
			Warehouse warehouse) throws InventoryNotAvailableException {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void release(Product product, double quantity,
			Warehouse warehouse) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void reserve(Product product, double quantity)
			throws InventoryNotAvailableException {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void release(Product product, double quantity) {
		// TODO Auto-generated method stub
		
	}

	
}
