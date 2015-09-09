package com.serpics.membership.data.specifications;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;

import com.serpics.commerce.session.CommerceSessionContext;
import com.serpics.core.Engine;
import com.serpics.membership.data.model.Store;
import com.serpics.membership.data.model.UsersReg;
import com.serpics.stereotype.DefaultSpec;

@DefaultSpec(UsersReg.class)
public class DefaultUserRegSpecification implements Specification<UsersReg> {
	@Autowired
	Engine<CommerceSessionContext> engine;

	@Override
	public Predicate toPredicate(Root<UsersReg> root, CriteriaQuery<?> cq,
			CriteriaBuilder cb) {
		return cb.equal(root.join("stores").as(Store.class), (Store) engine.getCurrentContext().getStoreRealm());

	}

}
