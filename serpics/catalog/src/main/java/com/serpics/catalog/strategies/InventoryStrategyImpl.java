package com.serpics.catalog.strategies;

import com.serpics.catalog.data.model.AbstractProduct;
import com.serpics.commerce.strategies.AbstractStrategy;
import com.serpics.stereotype.StoreStrategy;
import com.serpics.warehouse.InventoryNotAvailableException;

@StoreStrategy("inventoryStrategy")
public class InventoryStrategyImpl extends AbstractStrategy implements InventoryStrategy {

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
