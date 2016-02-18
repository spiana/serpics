package com.serpics.catalog.facade;

import org.springframework.beans.factory.annotation.Autowired;

import com.serpics.catalog.data.model.Category;
import com.serpics.catalog.facade.data.CategoryData;
import com.serpics.catalog.services.CategoryService;
import com.serpics.core.facade.Populator;


public class CategoryPopulator implements Populator<Category, CategoryData>    {
	
	@Autowired
	CategoryService categoryService;
	
	
	@Override
	public void populate(Category source, CategoryData target)  {
				
		target.setPublished(source.isPublished());
		
		target.setCatalogId(source.getCatalog().getCode());
		
		target.setChildCategoryNumber(categoryService.getCountChildCategory(source));
		
		target.setChildProductNumber(categoryService.getCountChildProduct(source));
		
	}

}
