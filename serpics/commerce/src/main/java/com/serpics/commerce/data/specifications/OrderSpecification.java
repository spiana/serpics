package com.serpics.commerce.data.specifications;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.serpics.commerce.data.model.Order;
import com.serpics.membership.data.model.User;

public class OrderSpecification {

	public static Specification<Order> findByUser(final User user) {
		return new Specification<Order>() {
			@Override
			public Predicate toPredicate(final Root<Order> root, final CriteriaQuery<?> query, 
					final CriteriaBuilder cb) {
						Predicate p = cb.equal(root.get("user"), user);
						return p;
			}
		};
	}
	
}
