package com.serpics.catalog.hooks;

import com.serpics.catalog.persistence.AbstractProduct;
import com.serpics.core.hook.AbstractHook;
import com.serpics.core.hook.Hook;
import com.serpics.core.hook.HookImplementation;
import com.serpics.warehouse.InventoryNotAvailableException;

@HookImplementation("inventory")
public class InventoryHookImpl extends AbstractHook implements InventoryHook {

	@Override
	public boolean checkInventory(AbstractProduct product) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void updateInventory(AbstractProduct product) throws InventoryNotAvailableException {
		// TODO Auto-generated method stub

	}

}
