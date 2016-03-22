package com.serpics.catalog.data.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;

import com.serpics.catalog.data.model.AbstractProduct;
import com.serpics.commerce.core.CommerceEngine;
import com.serpics.membership.UserType;
import com.serpics.membership.data.model.User;
import com.serpics.stereotype.DefaultSpec;

@DefaultSpec(AbstractProduct.class)
public class DefaultRegisteredProductSpecification implements Specification<AbstractProduct>{
	@Autowired
	CommerceEngine engine;
	

	@Override
	public Predicate toPredicate(Root<AbstractProduct> root, CriteriaQuery<?> cq,
			CriteriaBuilder cb) {
		User u = (User) engine.getCurrentContext().getUserPrincipal();
		if (u.getUserType() == UserType.REGISTERED || u.getUserType() == UserType.ANONYMOUS )
			return  cb.equal(root.get("buyable"), true);
		else
			return cb.isNotNull(root.get("id"));
	}
	
}
