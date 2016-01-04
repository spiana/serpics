package com.serpics.warehouse.service;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.serpics.catalog.data.model.BaseProduct;
import com.serpics.catalog.services.BaseProductService;
import com.serpics.commerce.session.CommerceSessionContext;
import com.serpics.core.service.AbstractService;
import com.serpics.warehouse.InventoryNotAvailableException;
import com.serpics.warehouse.InventoryStatus;
import com.serpics.warehouse.data.model.Warehouse;
import com.serpics.warehouse.strategies.InventoryStrategy;

@Service("inventoryService")
@Scope("store")
@Transactional(readOnly=true)
public class InventoryServiceImpl  extends AbstractService<CommerceSessionContext> implements InventoryService {

	@Resource
	InventoryStrategy inventoryStrategy;
	
	@Resource
	BaseProductService productService;
	
	
	@Override
	public InventoryStatus checkInventory(String sku, double quantity) {
		BaseProduct product  = productService.findByName(sku);	
		
		return inventoryStrategy.checkInventory(product, quantity);
	}

	@Override
	public void reserve(BaseProduct product, double quantity)
			throws InventoryNotAvailableException {
		
		inventoryStrategy.reserve(product, quantity);
	}

	@Override
	public void reserve(BaseProduct product, double quantity,
			Warehouse warehouse) throws InventoryNotAvailableException {
		inventoryStrategy.reserve(product, quantity, warehouse);
		
	}

	@Override
	public void release(BaseProduct product, double quantity,
			Warehouse warehouse) {
		inventoryStrategy.release(product, quantity, warehouse);
		
	}

	@Override
	public void release(BaseProduct product, double quantity) {
		inventoryStrategy.release(product, quantity);
		
	}

	@Override
	public double getStockLevelAmount(BaseProduct product) {
		return inventoryStrategy.getStockLevelAmount(product);
	}

	@Override
	public double getStockLevelAmount(BaseProduct product,
			Warehouse warehouse) {
		
		return inventoryStrategy.getStockLevelAmount(product, warehouse);
	}

	@Override
	public InventoryStatus getInventoryStatus(BaseProduct product) {
	
		return inventoryStrategy.getInventoryStatus(product);
	}

	@Override
	public InventoryStatus getInventoryStatus(BaseProduct product,
			Warehouse warehouse) {
		
		return inventoryStrategy.getInventoryStatus(product, warehouse);
	}

}
