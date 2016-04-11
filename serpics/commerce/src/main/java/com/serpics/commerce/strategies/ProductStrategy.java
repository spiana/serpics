package com.serpics.commerce.strategies;

import java.util.Map;

import com.serpics.catalog.ProductNotFoundException;
import com.serpics.catalog.data.model.AbstractProduct;
import com.serpics.catalog.data.model.ProductVariant;


public interface ProductStrategy {

	public AbstractProduct resolveSKU(String sku) throws ProductNotFoundException;
	public ProductVariant resolveVariant(String sku , Map<String ,String > attributeMap );
}
