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

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.serpics.catalog.facade.data.CategoryData;

public interface CategoryFacade {
	
	public CategoryData findCategoryByCode(String code);
	public CategoryData findCategoryById(Long id);
	
	public Page<CategoryData> listCategory(Pageable page);
	public List<CategoryData> listTopCategory();
	public List<CategoryData> listChildCategories(Long id);
	public CategoryData create(CategoryData category);
	public CategoryData create(CategoryData category, Long parentId);
	public void addCategoryParent(Long childId, Long parentId);
	
	public CategoryData updateCategory(CategoryData category);
	public void deleteCategory(Long id);
	public List<CategoryData> listChildCategories(String code);
	
}
