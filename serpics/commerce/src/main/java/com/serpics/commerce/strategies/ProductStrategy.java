package com.serpics.commerce.strategies;

import com.serpics.catalog.ProductNotFoundException;
import com.serpics.catalog.data.model.Product;


public interface ProductStrategy {

	public Product resolveSKU(String sku) throws ProductNotFoundException;
}
