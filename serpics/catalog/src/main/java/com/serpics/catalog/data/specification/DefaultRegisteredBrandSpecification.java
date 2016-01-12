package com.serpics.catalog.data.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;

import com.serpics.catalog.data.model.Brand;
import com.serpics.commerce.core.CommerceEngine;
import com.serpics.membership.UserType;
import com.serpics.membership.data.model.User;
import com.serpics.stereotype.DefaultSpec;

@DefaultSpec(Brand.class)
public class DefaultRegisteredBrandSpecification implements Specification<Brand>{
	@Autowired
	CommerceEngine engine;
	
	@Override
	public Predicate toPredicate(Root<Brand> root, CriteriaQuery<?> cq,
			CriteriaBuilder cb) {
		User u = (User) engine.getCurrentContext().getUserPrincipal();
		if (u.getUserType() == UserType.REGISTERED || u.getUserType() == UserType.ANONYMOUS )
			return  cb.equal(root.get("published"), true);
		else
			return cb.isNotNull(root.get("id"));
	}

}