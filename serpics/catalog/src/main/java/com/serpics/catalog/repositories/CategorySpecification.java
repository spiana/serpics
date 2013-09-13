package com.serpics.catalog.repositories;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.serpics.catalog.persistence.Catalog;
import com.serpics.catalog.persistence.Category;

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
