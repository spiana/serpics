package com.serpics.catalog.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.serpics.catalog.data.model.BaseProduct;
import com.serpics.catalog.data.model.Brand;
import com.serpics.catalog.data.model.Category;
import com.serpics.catalog.data.model.CtentryMedia;
import com.serpics.core.service.EntityService;

public interface AbstractProductService<T extends BaseProduct> extends EntityService<T, Long> {
	
	
	public T create(T product, Category category, Brand brand);
	
	public T addParentCategory(T product, Category category);

	public Page<T> findProductByCategory(Category category,Pageable pagination);
	public Page<T> findProductByBrand(Brand brand,Pageable pagination);
	
	public T addMedia(T product, CtentryMedia media);

	public T findByName(String name);

	public T addBrand(T product, Brand brand);
}
