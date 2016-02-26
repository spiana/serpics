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
