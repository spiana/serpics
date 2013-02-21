package com.serpics.catalog.hooks;

import com.serpics.catalog.persistence.AbstractProduct;
import com.serpics.core.hook.Hook;
import com.serpics.warehouse.InventoryNotAvailableException;

@Hook("inventory")
public interface InventoryHook {
	public boolean checkInventory(AbstractProduct product);

	public void updateInventory(AbstractProduct product) throws InventoryNotAvailableException;

}
