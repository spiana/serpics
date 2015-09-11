package com.serpics.warehouse.strategies;

import com.serpics.catalog.data.model.AbstractProduct;
import com.serpics.warehouse.InventoryNotAvailableException;
import com.serpics.warehouse.data.model.InventoryStatus;
import com.serpics.warehouse.data.model.Warehouse;

public interface InventoryStrategy {
	public InventoryStatus checkInventory(AbstractProduct product , double quantity);

	public void updateInventory(AbstractProduct product , double quantity) throws InventoryNotAvailableException;
	
	public void updateInventory(AbstractProduct product , double quantity , Warehouse warehouse) throws InventoryNotAvailableException;
	
	public double getStockLevelAmount(AbstractProduct product);
	public double getStockLevelAmount(AbstractProduct product, Warehouse warehouse);
	
	public InventoryStatus getInventoryStatus(AbstractProduct product);
	public InventoryStatus getInventoryStatus(AbstractProduct product , Warehouse warehouse);

}
