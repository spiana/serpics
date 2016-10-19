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
package com.serpics.catalog.data.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.serpics.catalog.data.model.Brand;

public class BrandSpecification {
	
	
	public static Specification<Brand> findOneByCode(final String code) {
		return new Specification<Brand>() {
			@Override
			public Predicate toPredicate(final Root<Brand> root, final CriteriaQuery<?> query, 
					final CriteriaBuilder cb) {
						Predicate p = cb.equal(root.get("code"), code);
						return p;
			}
		};
	}

}
