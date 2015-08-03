package com.serpics.catalog.services;

import com.serpics.catalog.data.model.Category;
import com.serpics.catalog.data.model.Product;
import com.serpics.core.service.EntityService;

public interface ProductService extends EntityService<Product, Long> {
	
	public Product create( Product product);
	public Product create(Product product, Category parent);
	
	public Product addParentCategory(Product product, Category category);

}
