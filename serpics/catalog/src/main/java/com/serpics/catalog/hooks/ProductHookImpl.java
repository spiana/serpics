package com.serpics.catalog.hooks;

import org.springframework.beans.factory.annotation.Autowired;

import com.serpics.catalog.ProductNotFoundException;
import com.serpics.catalog.persistence.Catalog;
import com.serpics.catalog.persistence.Product;
import com.serpics.catalog.repositories.ProductRepository;
import com.serpics.core.hook.AbstractHook;
import com.serpics.stereotype.HookImplementation;

@HookImplementation("productHook")
public class ProductHookImpl extends AbstractHook implements ProductHook {

	@Autowired
	ProductRepository productRepository;

	@Override
	public Product resolveSKU(String sku) throws ProductNotFoundException {
		Catalog catalog = (Catalog) getSessionContext().getCatalog();

		Product p = new Product();
		p.setBuyable(1);
		p.setCode(sku);
		p.setCatalog(catalog);

		Product product = productRepository.findOne(productRepository.makeSpecification(p));
		if (product == null)
			throw new ProductNotFoundException(String.format("product not found for SKU [%s] !", sku));
		
		return product;
	}

}
