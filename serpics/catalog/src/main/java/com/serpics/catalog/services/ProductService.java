package com.serpics.catalog.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.serpics.catalog.data.model.Brand;
import com.serpics.catalog.data.model.Category;
import com.serpics.catalog.data.model.CtentryMedia;
import com.serpics.catalog.data.model.Product;
import com.serpics.core.service.EntityService;

public interface ProductService extends EntityService<Product, Long>{

	public Product create(Product product, Category category, Brand brand);
	
	public Product addParentCategory(Product product, Category category);

	public Page<Product> findProductByCategory(Category category,Pageable pagination);
	public Page<Product> findProductByBrand(Brand brand,Pageable pagination);
	
	public Product addMedia(Product product, CtentryMedia media);

	public Product findByName(String name);

	public Product addBrand(Product product, Brand brand);

	Product findByCode(String code);
	
}
