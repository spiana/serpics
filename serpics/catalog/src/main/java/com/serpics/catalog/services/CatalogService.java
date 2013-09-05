package com.serpics.catalog.services;

import java.util.List;

import com.serpics.catalog.persistence.AbstractProduct;
import com.serpics.catalog.persistence.Bundle;
import com.serpics.catalog.persistence.Catalog;
import com.serpics.catalog.persistence.Category;
import com.serpics.catalog.persistence.Ctentry;
import com.serpics.catalog.persistence.Product;

public interface CatalogService {

	public Catalog createCatalog(Catalog catalog);
	
	public Catalog getCatalog(String name);
	
	public void deleteCatalog(Catalog  catalog);

	public void deleteProduct(AbstractProduct  product);
	
	public void deleteCatalogEntry(Ctentry ctentry);
	
	public List<Category> findRootCategory();
	
	public List<Category> getChildCategories(Category parent);

	public Category createCategory(Category c, Category parent);

	public Product createproduct(Product p);

	public Bundle createproduct(Bundle b);

}
