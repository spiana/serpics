/*******************************************************************************
 * Copyright 2014-2016  StepFour Srl
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *  
 *      http://www.apache.org/licenses/LICENSE-2.0
 *  
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *  
 *******************************************************************************/
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
