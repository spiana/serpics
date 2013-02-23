package com.serpics.catalog.hooks;

import com.serpics.catalog.ProductNotFoundException;
import com.serpics.catalog.persistence.Product;
import com.serpics.stereotype.Hook;

@Hook("productHook")
public interface ProductHook {

	public Product resolveSKU(String sku) throws ProductNotFoundException;
}
