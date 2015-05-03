package com.serpics.catalog.services;

import java.util.List;

import com.serpics.catalog.data.model.Category;
import com.serpics.core.service.EntityService;

public interface CategoryService extends EntityService<Category, Long> {
    public Category create(Category c, Category parent);

    public List<Category> findRootCategory();

    public List<Category> getChildCategories(Category parent);

}
