package com.serpics.catalog.services;

import java.util.List;
import java.util.Set;

import com.serpics.catalog.data.model.Category;
import com.serpics.catalog.data.model.AbstractProduct;
import com.serpics.core.service.EntityService;

public interface CategoryService extends EntityService<Category, Long> {
    public Category create(Category c, Category parent);
    
    public void addRelationCategory(Category child, Category parent);
    
    public Category findByCode(final String code);
    
    public List<Category> findRootCategory();

    public List<Category> getChildCategories(Category parent);
    
    public List<Category> getCategoriesByProduct(AbstractProduct product);
    
    public int getCountChildCategory(Category category);
    
    public int getCountChildProduct(Category category);

	public Set<Category> getParentCategories(Category category);

}
