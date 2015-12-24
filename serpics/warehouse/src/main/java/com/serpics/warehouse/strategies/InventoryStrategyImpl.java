package com.serpics.warehouse.strategies;

import com.serpics.catalog.data.model.AbstractProduct;
import com.serpics.commerce.strategies.AbstractStrategy;
import com.serpics.stereotype.StoreStrategy;
import com.serpics.warehouse.InventoryNotAvailableException;
import com.serpics.warehouse.InventoryStatus;
import com.serpics.warehouse.InventoryStatusEnum;
import com.serpics.warehouse.data.model.Warehouse;

@StoreStrategy("inventoryStrategy")
public class InventoryStrategyImpl extends AbstractStrategy implements InventoryStrategy {

	@Override
	public InventoryStatus checkInventory(AbstractProduct product , double quantity) {
		return InventoryStatusEnum.InStock;
	}

	
	@Override
	public double getStockLevelAmount(AbstractProduct product) {
		return Double.MAX_VALUE;
	}
	
	@Override
	public InventoryStatus getInventoryStatus(AbstractProduct product ) {
		return InventoryStatusEnum.InStock;
	}

	@Override
	public double getStockLevelAmount(AbstractProduct product ,Warehouse warehouse) {
		return Double.MAX_VALUE;
	}

	@Override
	public InventoryStatus getInventoryStatus(AbstractProduct product,Warehouse warehouse) {
		return InventoryStatusEnum.InStock;
	}


	@Override
	public void reserve(AbstractProduct product, double quantity,
			Warehouse warehouse) throws InventoryNotAvailableException {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void release(AbstractProduct product, double quantity,
			Warehouse warehouse) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void reserve(AbstractProduct product, double quantity)
			throws InventoryNotAvailableException {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void release(AbstractProduct product, double quantity) {
		// TODO Auto-generated method stub
		
	}

	
}
