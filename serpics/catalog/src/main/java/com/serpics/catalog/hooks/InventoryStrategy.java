package com.serpics.catalog.hooks;

import com.serpics.catalog.data.model.AbstractProduct;
import com.serpics.warehouse.InventoryNotAvailableException;

public interface InventoryStrategy {
	public boolean checkInventory(AbstractProduct product);

	public void updateInventory(AbstractProduct product) throws InventoryNotAvailableException;

}
