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
package com.serpics.catalog.data.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.serpics.catalog.data.model.Category;
import com.serpics.catalog.data.model.CategoryProductRelation;
import com.serpics.catalog.data.model.CtentryRelationPK;
import com.serpics.catalog.data.model.AbstractProduct;
import com.serpics.core.data.Repository;

public interface Category2ProductRepository extends Repository<CategoryProductRelation, CtentryRelationPK>  {
	
	@Query("select count(c.childProduct) from CategoryProductRelation c where c.parentCategory = :category and c.childProduct.buyable = true and c.parentCategory.published = true")
	public int getCountChildProduct(@Param("category") Category category);
	
	public List<CategoryProductRelation> findByChildProduct(AbstractProduct product);
}
