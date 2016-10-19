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

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.serpics.catalog.data.model.Catalog;
import com.serpics.catalog.data.model.Category;

public class CategoryRepositoryImpl implements BaseCategoryRepository {

	@PersistenceContext(name = "serpics")
	EntityManager entityManager;

	@Override
	@SuppressWarnings("unchecked")
	public List<Category> findRootCategory(Catalog catalog) {
		String query = "select g from Category g"
				+ " where g.catalog=:catalogId and g.id not in (select r.childCategory.id from CategoryRelation as r) ";

		Query q = entityManager.createQuery(query, Category.class);
		q.setParameter("catalogId", catalog);
		return q.getResultList();
	}

}
