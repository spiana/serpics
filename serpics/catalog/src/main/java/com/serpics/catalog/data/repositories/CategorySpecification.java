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

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.serpics.catalog.data.model.Catalog;
import com.serpics.catalog.data.model.Category;

public class CategorySpecification {

	public static Specification<Category> isCategoryInCatalog(final Catalog catalog){
		return new Specification<Category>() {
		@Override
		public Predicate toPredicate(Root<Category> root, CriteriaQuery<?> query,
				CriteriaBuilder cb) {
			
			return cb.equal(root.get("catalog"), catalog);
		}
		};
	}
}
