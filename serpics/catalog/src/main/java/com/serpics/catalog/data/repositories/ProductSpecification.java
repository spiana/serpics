package com.serpics.catalog.data.repositories;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.serpics.catalog.data.model.Category;
import com.serpics.catalog.data.model.CategoryProductRelation;



public class ProductSpecification {
	
	public static Specification<CategoryProductRelation> findByCategory(final Category category) {
		return new Specification<CategoryProductRelation>() {
			@Override
			public Predicate toPredicate(final Root<CategoryProductRelation> root, final CriteriaQuery<?> query, 
					final CriteriaBuilder cb) {
						Predicate p = cb.equal(root.get("parentCategory"), category);
						return p;
			}
		};
	}
}