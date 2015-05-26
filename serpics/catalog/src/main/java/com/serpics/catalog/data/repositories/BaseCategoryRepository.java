package com.serpics.catalog.data.repositories;

import java.util.List;

import com.serpics.catalog.data.model.Catalog;
import com.serpics.catalog.data.model.Category;

public interface BaseCategoryRepository {

	public List<Category> findRootCategory(Catalog catalog);
}
