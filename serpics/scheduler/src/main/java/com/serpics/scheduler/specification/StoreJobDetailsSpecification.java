package com.serpics.scheduler.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;

import com.serpics.base.data.model.Store;
import com.serpics.commerce.core.CommerceEngine;
import com.serpics.scheduler.model.StoreJobDetails;
import com.serpics.stereotype.DefaultSpec;

@DefaultSpec(StoreJobDetails.class)
public class StoreJobDetailsSpecification implements Specification<StoreJobDetails> {

	@Autowired
	CommerceEngine engine;
	
	@Override
	public Predicate toPredicate(Root<StoreJobDetails> root, CriteriaQuery<?> arg1, CriteriaBuilder cb) {
		return cb.equal(root.get("store"), (Store)engine.getCurrentContext().getStoreRealm());
	}

}
