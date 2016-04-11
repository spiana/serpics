package com.serpics.warehouse.service;

import javax.annotation.Resource;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.serpics.catalog.ProductNotFoundException;
import com.serpics.catalog.data.model.AbstractProduct;
import com.serpics.catalog.services.ProductService;
import com.serpics.commerce.session.CommerceSessionContext;
import com.serpics.core.service.AbstractService;
import com.serpics.stereotype.StoreService;
import com.serpics.warehouse.InventoryNotAvailableException;
import com.serpics.warehouse.InventoryStatus;
import com.serpics.warehouse.data.model.Warehouse;
import com.serpics.warehouse.strategies.InventoryStrategy;

@StoreService("inventoryService")
@Transactional(readOnly=true)
public class InventoryServiceImpl  extends AbstractService<CommerceSessionContext> implements InventoryService {

	@Resource
	InventoryStrategy inventoryStrategy;
	
	@Resource
	ProductService productService;
	
	@Resource
	WareHouseService warehouseService;
	
	
	@Override
	public InventoryStatus checkInventory(String sku, double quantity) throws ProductNotFoundException {
		AbstractProduct product  = productService.findByName(sku);	
		if (product == null)
			throw new ProductNotFoundException();
		
		return checkInventory(product, quantity);
	}

	@Override
	public InventoryStatus checkInventory(AbstractProduct product, double quantity) {
		return inventoryStrategy.checkInventory(product, quantity);
	}
	
	
	@Override
	public void reserve(AbstractProduct product, double quantity,
			Warehouse warehouse) throws InventoryNotAvailableException {
		inventoryStrategy.reserve(product, quantity, warehouse);
		
	}

	@Override
	public void release(AbstractProduct product, double quantity,
			Warehouse warehouse) {
		inventoryStrategy.release(product, quantity, warehouse);
		
	}


	@Override
	public double getStockLevelAmount(AbstractProduct product) {
		return inventoryStrategy.getStockLevelAmount(product);
	}

	@Override
	public double getStockLevelAmount(AbstractProduct product,
			Warehouse warehouse) {
		
		return inventoryStrategy.getStockLevelAmount(product, warehouse);
	}

	@Override
	public InventoryStatus getInventoryStatus(AbstractProduct product) {
	
		return inventoryStrategy.getInventoryStatus(product);
	}

	@Override
	public InventoryStatus getInventoryStatus(AbstractProduct product,
			Warehouse warehouse) {
		
		return inventoryStrategy.getInventoryStatus(product, warehouse);
	}

	@Override
	@Transactional
	public void reserve(AbstractProduct product, double quantity)
			throws InventoryNotAvailableException {
		
		if (inventoryStrategy.getStoreConfig().isAlwaysInstock())
			return;
		
		Warehouse w = warehouseService.findPreferredForReserve(product , quantity);
		if (w != null){
			inventoryStrategy.reserve(product, quantity, w);
		}else{
			while (quantity > 0){
				Warehouse _w = warehouseService.findPreferredForReserve(product , 1.0);
				if (_w == null){
					_w = warehouseService.findPreferredForced();
					if (_w == null)
						throw new InventoryNotAvailableException(product.getCode(), quantity);
				}
					
				double available = inventoryStrategy.getStockLevelAmount(product, _w);
				inventoryStrategy.reserve(product, available, _w);
				quantity -= available;
			}
			
			
		}
		
		
	}

	@Override
	@Transactional
	public void release(AbstractProduct product, double quantity) {
		if (inventoryStrategy.getStoreConfig().isAlwaysInstock())
			return;
		
		Warehouse w = warehouseService.findPreferredForRelease(product , quantity);
		if (w != null)
			inventoryStrategy.release(product, quantity, w);
		else {
			while (quantity > 0){
				Warehouse _w = warehouseService.findPreferredForRelease(product , 1.0);
				if (_w == null){
					_w = warehouseService.findPreferredForced();
					Assert.notNull(_w, String.format("Warehouse not found to release product [%s]  and quantity [%s] !" , product.getCode() , quantity));
				}
			
				
				double reserved = inventoryStrategy.getStockLevelReserve(product, _w);
				inventoryStrategy.release(product, reserved, _w);
				quantity -= reserved;
			}
		}
	}

}
