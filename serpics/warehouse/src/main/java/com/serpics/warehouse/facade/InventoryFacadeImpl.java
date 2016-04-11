package com.serpics.warehouse.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.serpics.catalog.data.model.AbstractProduct;
import com.serpics.catalog.facade.data.InventoryData;
import com.serpics.catalog.services.ProductService;
import com.serpics.stereotype.StoreFacade;
import com.serpics.warehouse.service.InventoryService;

@StoreFacade("inventoryFacade")
@Transactional(readOnly=true)
public class InventoryFacadeImpl implements InventoryFacade {
	
	@Autowired
	InventoryService inventoryService;
	
	@Autowired
	ProductService productService;
	
	@Override
	public InventoryData getInventoryForProductId(Long productId){
		AbstractProduct product = productService.findOne(productId);
		Assert.notNull(product);
		InventoryData inventoryData = getInventoryForProduct(product);
		return inventoryData;
	}
	
	@Override
	public InventoryData getInventoryForProduct(AbstractProduct product){
		InventoryData inventoryData = new InventoryData();
		inventoryData.setInventoryStatus(inventoryService.getInventoryStatus(product).toString());
		inventoryData.setStockLevelAmount(inventoryService.getStockLevelAmount(product));
		return inventoryData;
	}

}
