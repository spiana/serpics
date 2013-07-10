package com.serpics.catalog.hooks;

import com.serpics.catalog.persistence.AbstractProduct;
import com.serpics.stereotype.Hook;
import com.serpics.warehouse.InventoryNotAvailableException;

@Hook("inventoryHook")
public interface InventoryHook {
	public boolean checkInventory(AbstractProduct product);

	public void updateInventory(AbstractProduct product) throws InventoryNotAvailableException;

}
