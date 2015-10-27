package com.serpics.catalog.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.serpics.catalog.data.model.Category;
import com.serpics.catalog.data.model.Media;
import com.serpics.catalog.data.model.Product;
import com.serpics.core.service.EntityService;

public interface ProductService extends EntityService<Product, Long> {
	
	public Product create( Product product);
	public Product create(Product product, Category parent);
	
	public Product addParentCategory(Product product, Category category);

	public Page<Product> findProductByCategory(Category category,Pageable pagination);
	
	
	public Product addMedia(Product product, Media media);
}
