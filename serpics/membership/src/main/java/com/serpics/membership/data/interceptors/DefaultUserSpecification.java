package com.serpics.membership.data.interceptors;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;

import com.serpics.commerce.session.CommerceSessionContext;
import com.serpics.core.Engine;
import com.serpics.membership.data.model.Store;
import com.serpics.membership.data.model.User;
import com.serpics.stereotype.DefaultSpec;

@DefaultSpec(User.class)
public class DefaultUserSpecification implements Specification<User> {
	@Autowired
	Engine<CommerceSessionContext> engine;

	@Override
	public Predicate toPredicate(Root<User> root, CriteriaQuery<?> cq,
			CriteriaBuilder cb) {
		return cb.equal(root.join("stores").as(Store.class), (Store) engine.getCurrentContext().getStoreRealm());

	}

}
