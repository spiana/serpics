package com.serpics.catalog.data.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.serpics.catalog.data.model.Category;
import com.serpics.catalog.data.model.CategoryProductRelation;
import com.serpics.catalog.data.model.Product;



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
	
	public static Specification<Product> findByName(final String name) {
		return new Specification<Product>() {
			@Override
			public Predicate toPredicate(final Root<Product> root, final CriteriaQuery<?> query, 
					final CriteriaBuilder cb) {
						Predicate p = cb.equal(root.get("code"), name);
						return p;
			}
		};
	}
}
