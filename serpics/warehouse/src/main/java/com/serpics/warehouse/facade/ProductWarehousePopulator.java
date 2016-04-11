package com.serpics.warehouse.facade;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.serpics.catalog.data.model.AbstractProduct;
import com.serpics.catalog.facade.data.AbstractProductData;
import com.serpics.core.facade.Populator;

public class ProductWarehousePopulator implements Populator<AbstractProduct, AbstractProductData> {
	
	private static Logger LOG = LoggerFactory.getLogger(ProductWarehousePopulator.class);
	
	@Autowired
	InventoryFacade inventoryFacade;
	
	@Override
	public void populate(AbstractProduct source, AbstractProductData target) {
		
		target.setInventoryData(inventoryFacade.getInventoryForProduct(source));
		
	}

}
