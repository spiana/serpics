package com.serpics.catalog.hooks;

import com.serpics.catalog.ProductNotFoundException;
import com.serpics.catalog.persistence.Product;

public interface ProductHook {

	public Product resolveSKU(String sku) throws ProductNotFoundException;
}
