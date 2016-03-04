package com.serpics.warehouse.facade;

import com.serpics.catalog.data.model.Product;
import com.serpics.catalog.facade.data.InventoryData;

public interface InventoryFacade {

	public InventoryData getInventoryForProductId(Long productId);

	public InventoryData getInventoryForProduct(Product product);

}