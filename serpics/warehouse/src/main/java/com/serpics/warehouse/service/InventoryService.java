package com.serpics.warehouse.service;

import com.serpics.catalog.data.model.Product;
import com.serpics.warehouse.InventoryNotAvailableException;
import com.serpics.warehouse.InventoryStatus;
import com.serpics.warehouse.data.model.Warehouse;

public interface InventoryService {

	public InventoryStatus checkInventory(String sku , double quantity);
	
	public void reserve(Product product , double quantity ) throws InventoryNotAvailableException;
	public void reserve(Product product , double quantity , Warehouse warehouse) throws InventoryNotAvailableException;
	public void release(Product product , double quantity , Warehouse warehouse) ;
	public void release(Product product , double quantity) ;
	
	public double getStockLevelAmount(Product product);
	public double getStockLevelAmount(Product product, Warehouse warehouse);
	
	public InventoryStatus getInventoryStatus(Product product);
	public InventoryStatus getInventoryStatus(Product product , Warehouse warehouse);
}
