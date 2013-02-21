package com.serpics.catalog.hooks;

import com.serpics.catalog.ProductNotFoundException;
import com.serpics.catalog.persistence.Product;
import com.serpics.core.hook.Hook;

@Hook("product")
public interface ProductHook {

	public Product resolveSKU(String sku) throws ProductNotFoundException;
}
