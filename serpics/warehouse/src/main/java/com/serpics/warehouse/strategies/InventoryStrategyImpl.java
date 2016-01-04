package com.serpics.warehouse.strategies;

import com.serpics.catalog.data.model.BaseProduct;
import com.serpics.commerce.strategies.AbstractStrategy;
import com.serpics.stereotype.StoreStrategy;
import com.serpics.warehouse.InventoryNotAvailableException;
import com.serpics.warehouse.InventoryStatus;
import com.serpics.warehouse.InventoryStatusEnum;
import com.serpics.warehouse.data.model.Warehouse;

@StoreStrategy("inventoryStrategy")
public class InventoryStrategyImpl extends AbstractStrategy implements InventoryStrategy {

	@Override
	public InventoryStatus checkInventory(BaseProduct product , double quantity) {
		return InventoryStatusEnum.InStock;
	}

	
	@Override
	public double getStockLevelAmount(BaseProduct product) {
		return Double.MAX_VALUE;
	}
	
	@Override
	public InventoryStatus getInventoryStatus(BaseProduct product ) {
		return InventoryStatusEnum.InStock;
	}

	@Override
	public double getStockLevelAmount(BaseProduct product ,Warehouse warehouse) {
		return Double.MAX_VALUE;
	}

	@Override
	public InventoryStatus getInventoryStatus(BaseProduct product,Warehouse warehouse) {
		return InventoryStatusEnum.InStock;
	}


	@Override
	public void reserve(BaseProduct product, double quantity,
			Warehouse warehouse) throws InventoryNotAvailableException {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void release(BaseProduct product, double quantity,
			Warehouse warehouse) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void reserve(BaseProduct product, double quantity)
			throws InventoryNotAvailableException {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void release(BaseProduct product, double quantity) {
		// TODO Auto-generated method stub
		
	}

	
}
