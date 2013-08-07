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
		String query = "SELECT * FROM ctentry c" + " join category g on c.ctentry_id=g.category_id"
				+ " left outer join ctentry_relation r on r.ctentry_id_child = c.ctentry_id"
				+ " where r.ctentry_id_parent is null and c.catalog_id=:catalogId";

		Query q = entityManager.createNativeQuery(query, Category.class);
		q.setParameter("catalogId", catalog.getCatalogId());
		return q.getResultList();
	}

}
