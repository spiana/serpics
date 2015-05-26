package com.serpics.catalog.strategies;

import org.springframework.beans.factory.annotation.Autowired;

import com.serpics.catalog.ProductNotFoundException;
import com.serpics.catalog.data.model.Catalog;
import com.serpics.catalog.data.model.Product;
import com.serpics.catalog.data.repositories.ProductRepository;
import com.serpics.commerce.strategies.AbstractStrategy;
import com.serpics.stereotype.StoreStrategy;

@StoreStrategy("productStrategy")
public class ProductStrategyImpl extends AbstractStrategy implements ProductStrategy {

	@Autowired
	ProductRepository productRepository;

	@Override
	public Product resolveSKU(String sku) throws ProductNotFoundException {
		Catalog catalog = (Catalog) getCurrentContext().getCatalog();

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
