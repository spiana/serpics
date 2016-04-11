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
