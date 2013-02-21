package com.serpics.catalog.hooks;

import com.serpics.catalog.ProductNotFoundException;
import com.serpics.catalog.persistence.Product;
import com.serpics.core.hook.AbstractHook;
import com.serpics.core.hook.Hook;
import com.serpics.core.hook.HookImplementation;

@HookImplementation("product")
public class ProductHookImpl extends AbstractHook implements ProductHook {

	@Override
	public Product resolveSKU(String sku) throws ProductNotFoundException {
		Product p = new Product();
		p.setSku(sku);
		p.setBuyable((short) 1);
		p.setPublished((short) 1);

		return p;
	}

}
