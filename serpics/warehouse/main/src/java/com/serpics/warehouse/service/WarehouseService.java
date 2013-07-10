package com.serpics.warehouse.service;

import com.serpics.warehouse.InventoryNotAvailableException;

public interface WarehouseService {

	public boolean checkInventory(String sku , double quantity);
	public void updateInventory(String sku , double quantity) throws InventoryNotAvailableException;
}
