package com.serpics.warehouse.strategies;

import com.serpics.catalog.data.model.BaseProduct;
import com.serpics.warehouse.InventoryNotAvailableException;
import com.serpics.warehouse.InventoryStatus;
import com.serpics.warehouse.data.model.Warehouse;

public interface InventoryStrategy {
	public InventoryStatus checkInventory(BaseProduct product , double quantity);
	
	public void reserve(BaseProduct product , double quantity ) throws InventoryNotAvailableException;
	public void reserve(BaseProduct product , double quantity , Warehouse warehouse) throws InventoryNotAvailableException;
	public void release(BaseProduct product , double quantity , Warehouse warehouse) ;
	public void release(BaseProduct product , double quantity) ;
	
	public double getStockLevelAmount(BaseProduct product);
	public double getStockLevelAmount(BaseProduct product, Warehouse warehouse);
	
	public InventoryStatus getInventoryStatus(BaseProduct product);
	public InventoryStatus getInventoryStatus(BaseProduct product , Warehouse warehouse);

}
