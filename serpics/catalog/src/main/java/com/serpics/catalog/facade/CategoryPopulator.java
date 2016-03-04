package com.serpics.catalog.facade;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.serpics.catalog.data.model.Category;
import com.serpics.catalog.facade.data.CategoryData;
import com.serpics.catalog.services.CategoryService;
import com.serpics.core.facade.AbstractPopulatingConverter;
import com.serpics.core.facade.Populator;


public class CategoryPopulator implements Populator<Category, CategoryData>    {
	
	private AbstractPopulatingConverter<Category, CategoryData> categoryConverter;
	
	@Autowired
	CategoryService categoryService;
	
	
	@Override
	public void populate(Category source, CategoryData target)  {
				
		target.setPublished(source.isPublished());
		
		target.setCatalogId(source.getCatalog().getCode());
		
		target.setChildCategoryNumber(categoryService.getCountChildCategory(source));
		
		target.setChildProductNumber(categoryService.getCountChildProduct(source));
		
		Set<CategoryData> parentCategories = new HashSet<CategoryData>();
		
		for (Category category : categoryService.getParentCategories(source)){
			parentCategories.add(categoryConverter.convert(category));
		}
		
		target.setParentCategories(parentCategories);
	}


	public void setCategoryConverter(AbstractPopulatingConverter<Category, CategoryData> categoryConverter) {
		this.categoryConverter = categoryConverter;
	}

}
