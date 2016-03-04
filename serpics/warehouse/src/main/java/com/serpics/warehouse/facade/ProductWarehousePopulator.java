package com.serpics.warehouse.facade;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.serpics.catalog.data.model.Product;
import com.serpics.catalog.facade.data.ProductData;
import com.serpics.core.facade.Populator;

public class ProductWarehousePopulator implements Populator<Product, ProductData> {
	
	private static Logger LOG = LoggerFactory.getLogger(ProductWarehousePopulator.class);
	
	@Autowired
	InventoryFacade inventoryFacade;
	
	@Override
	public void populate(Product source, ProductData target) {
		
		target.setInventoryData(inventoryFacade.getInventoryForProduct(source));
		
	}

}
