package com.serpics.commerce.strategies;

import com.serpics.catalog.ProductNotFoundException;
import com.serpics.catalog.data.model.AbstractProduct;


public interface ProductStrategy {

	public AbstractProduct resolveSKU(String sku) throws ProductNotFoundException;
}
