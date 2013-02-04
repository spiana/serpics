package com.serpics.catalog.hooks;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.serpics.catalog.ProductNotFoundException;
import com.serpics.catalog.persistence.Product;
import com.serpics.core.hook.AbstractHook;

@Component("productHook")
@Scope("prototype")
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
