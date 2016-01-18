package com.serpics.warehouse.strategies;

import com.serpics.catalog.data.model.Product;
import com.serpics.warehouse.InventoryNotAvailableException;
import com.serpics.warehouse.InventoryStatus;
import com.serpics.warehouse.data.model.Warehouse;
import com.serpics.warehouse.data.model.WarehouseStoreConfig;

public interface InventoryStrategy {
	public InventoryStatus checkInventory(Product product , double quantity);
	
	
	public void reserve(Product product , double quantity , Warehouse warehouse) throws InventoryNotAvailableException;
	public void release(Product product , double quantity , Warehouse warehouse) ;
	
	public double getStockLevelAmount(Product product);
	public double getStockLevelAmount(Product product, Warehouse warehouse);
	
	public double getStockLevelReserve(Product product, Warehouse warehouse);
	
	public InventoryStatus getInventoryStatus(Product product);
	public InventoryStatus getInventoryStatus(Product product , Warehouse warehouse);
	
	public WarehouseStoreConfig getStoreConfig();

}
