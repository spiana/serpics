package com.serpics.catalog.repositories;

import java.util.List;

import com.serpics.catalog.persistence.Catalog;
import com.serpics.catalog.persistence.Category;

public interface BaseCategoryRepository {

	public List<Category> findRootCategory(Catalog catalog);
}
