package com.serpics.catalog.services;

import java.util.List;

import com.serpics.catalog.persistence.Catalog;
import com.serpics.catalog.persistence.Category;
import com.serpics.catalog.persistence.Product;

public interface CatalogService {

	public Catalog createCatalog(Catalog catalog);

	public List<Category> findRootCategory();

	public Category createCategory(Category c, Category parent);

	public Product createproduct(Product p);

}
