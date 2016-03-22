package com.serpics.warehouse.service;

import com.serpics.catalog.ProductNotFoundException;
import com.serpics.catalog.data.model.AbstractProduct;
import com.serpics.warehouse.InventoryNotAvailableException;
import com.serpics.warehouse.InventoryStatus;
import com.serpics.warehouse.data.model.Warehouse;

public interface InventoryService {

	public InventoryStatus checkInventory(AbstractProduct product , double quantity);
	public InventoryStatus checkInventory(String  sku , double quantity) throws ProductNotFoundException;
	
	public void reserve(AbstractProduct product , double quantity , Warehouse warehouse) throws InventoryNotAvailableException;
	public void release(AbstractProduct product , double quantity , Warehouse warehouse) ;
	public void reserve(AbstractProduct product , double quantity) throws InventoryNotAvailableException;
	public void release(AbstractProduct product , double quantity) ;
	
	
	public double getStockLevelAmount(AbstractProduct product);
	public double getStockLevelAmount(AbstractProduct product, Warehouse warehouse);
	
	public InventoryStatus getInventoryStatus(AbstractProduct product);
	public InventoryStatus getInventoryStatus(AbstractProduct product , Warehouse warehouse);
}
