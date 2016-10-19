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
