package com.serpics.membership.data.repositories;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;

import com.serpics.commerce.core.CommerceEngine;
import com.serpics.core.Engine;
import com.serpics.membership.data.model.Membergroup;
import com.serpics.membership.data.model.Store;

public class MembergroupSpecification implements Specification<Membergroup> {
	@Autowired
	CommerceEngine engine;
	
	@Override
	public Predicate toPredicate(Root<Membergroup> root, CriteriaQuery<?> cq,
			CriteriaBuilder cb) {
		  return cb.equal(root.get("store"), (Store) engine.getCurrentContext().getStoreRealm());
	}

}
