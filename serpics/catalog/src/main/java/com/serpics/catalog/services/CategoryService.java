package com.serpics.catalog.services;

import java.util.List;

import com.serpics.catalog.data.model.Category;
import com.serpics.catalog.data.model.Product;
import com.serpics.core.service.EntityService;

public interface CategoryService extends EntityService<Category, Long> {
    public Category create(Category c, Category parent);
    
    public void addRelationCategory(Category child, Category parent);
    
    public Category findByCode(final String code);
    
    public List<Category> findRootCategory();

    public List<Category> getChildCategories(Category parent);
    
    public List<Category> getCategoriesByProduct(Product product);
    
    public int getCountChildCategory(Category category);
    
    public int getCountChildProduct(Category category);

}
