package com.serpics.catalog.persistence.dao;

import java.util.List;

import com.serpics.catalog.persistence.Catalog;
import com.serpics.catalog.persistence.Category;
import com.serpics.catalog.persistence.AbstractProduct;
import com.serpics.core.persistence.dao.BaseFactory;

public interface CatalogFactory extends BaseFactory {
	public Catalog getCatalog(long catalogId);
	public List<Category> fetchRootCategory(Long catalogId);
	public Category createCatalogCategory( Category c , String URL , Category parent, double sequence,Catalog catalog);
	public Category createCatalogCategory( Category c , String URL , Category parent, Catalog catalog);
	public AbstractProduct createCatalogItem( AbstractProduct p , Catalog catalog , AbstractProduct parent);
	public AbstractProduct createCatalogProduct( AbstractProduct p , Catalog catalog);
	public AbstractProduct createCatalogPackage( AbstractProduct p , Catalog catalog);
	
	public List<Category> fetchCategory(Long catalogId , Long parentId);
	public List<AbstractProduct> fetchProduct(Long catlogId , Long categoryId , boolean onlyPublished);

}
