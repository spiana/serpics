package com.serpics.commerce.strategies;

import org.springframework.beans.factory.annotation.Autowired;

import com.serpics.catalog.ProductNotFoundException;
import com.serpics.catalog.data.model.Product;
import com.serpics.catalog.services.ProductService;
import com.serpics.stereotype.StoreStrategy;

@StoreStrategy("productStrategy")
public class ProductStrategyImpl extends AbstractStrategy implements ProductStrategy {

	@Autowired
	ProductService productService;

	@Override
	public Product resolveSKU(String sku) throws ProductNotFoundException {
	
		Product product = productService.findByName(sku);
		
		if (product == null)
			throw new ProductNotFoundException(String.format("product not found for SKU [%s] !", sku));
		
		return product;
	}

}
