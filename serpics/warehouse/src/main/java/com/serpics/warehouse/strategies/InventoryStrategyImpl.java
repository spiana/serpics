package com.serpics.warehouse.strategies;

import com.serpics.catalog.data.model.Product;
import com.serpics.commerce.strategies.AbstractStrategy;
import com.serpics.stereotype.StoreStrategy;
import com.serpics.warehouse.InventoryNotAvailableException;
import com.serpics.warehouse.InventoryStatus;
import com.serpics.warehouse.InventoryStatusEnum;
import com.serpics.warehouse.data.model.Warehouse;

@StoreStrategy("inventoryStrategy")
public class InventoryStrategyImpl extends AbstractStrategy implements InventoryStrategy {

	@Override
	public InventoryStatus checkInventory(Product product , double quantity) {
		return InventoryStatusEnum.InStock;
	}

	
	@Override
	public double getStockLevelAmount(Product product) {
		return Double.MAX_VALUE;
	}
	
	@Override
	public InventoryStatus getInventoryStatus(Product product ) {
		return InventoryStatusEnum.InStock;
	}

	@Override
	public double getStockLevelAmount(Product product ,Warehouse warehouse) {
		return Double.MAX_VALUE;
	}

	@Override
	public InventoryStatus getInventoryStatus(Product product,Warehouse warehouse) {
		return InventoryStatusEnum.InStock;
	}


	@Override
	public void reserve(Product product, double quantity,
			Warehouse warehouse) throws InventoryNotAvailableException {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void release(Product product, double quantity,
			Warehouse warehouse) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void reserve(Product product, double quantity)
			throws InventoryNotAvailableException {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void release(Product product, double quantity) {
		// TODO Auto-generated method stub
		
	}

	
}
