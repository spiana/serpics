package com.serpics.catalog.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.serpics.catalog.persistence.Catalog;
import com.serpics.catalog.persistence.Category;

public class CategoryRepositoryImpl implements BaseCategoryRepository {

	@PersistenceContext(name = "serpics")
	EntityManager entityManager;

	@Override
	@SuppressWarnings("unchecked")
	public List<Category> findRootCategory(Catalog catalog) {
		String query = "select g from Category g"
				+ " where g.catalog=:catalogId and g.ctentryId not in (select r.childCategory.ctentryId from CategoryRelation as r) ";

		Query q = entityManager.createQuery(query, Category.class);
		q.setParameter("catalogId", catalog);
		return q.getResultList();
	}

}
