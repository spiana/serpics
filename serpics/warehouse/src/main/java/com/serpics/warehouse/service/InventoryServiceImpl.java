package com.serpics.warehouse.service;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.serpics.catalog.ProductNotFoundException;
import com.serpics.catalog.data.model.Product;
import com.serpics.catalog.services.ProductService;
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
	ProductService productService;
	
	
	@Override
	public InventoryStatus checkInventory(String sku, double quantity) throws ProductNotFoundException {
		Product product  = productService.findByName(sku);	
		if (product == null)
			throw new ProductNotFoundException();
		
		return checkInventory(product, quantity);
	}

	@Override
	public InventoryStatus checkInventory(Product product, double quantity) {
		return inventoryStrategy.checkInventory(product, quantity);
	}
	
	@Override
	public void reserve(Product product, double quantity)
			throws InventoryNotAvailableException {
		
		inventoryStrategy.reserve(product, quantity);
	}

	@Override
	public void reserve(Product product, double quantity,
			Warehouse warehouse) throws InventoryNotAvailableException {
		inventoryStrategy.reserve(product, quantity, warehouse);
		
	}

	@Override
	public void release(Product product, double quantity,
			Warehouse warehouse) {
		inventoryStrategy.release(product, quantity, warehouse);
		
	}

	@Override
	public void release(Product product, double quantity) {
		inventoryStrategy.release(product, quantity);
		
	}

	@Override
	public double getStockLevelAmount(Product product) {
		return inventoryStrategy.getStockLevelAmount(product);
	}

	@Override
	public double getStockLevelAmount(Product product,
			Warehouse warehouse) {
		
		return inventoryStrategy.getStockLevelAmount(product, warehouse);
	}

	@Override
	public InventoryStatus getInventoryStatus(Product product) {
	
		return inventoryStrategy.getInventoryStatus(product);
	}

	@Override
	public InventoryStatus getInventoryStatus(Product product,
			Warehouse warehouse) {
		
		return inventoryStrategy.getInventoryStatus(product, warehouse);
	}

}
