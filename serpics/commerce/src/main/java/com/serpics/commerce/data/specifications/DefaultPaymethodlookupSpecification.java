package com.serpics.commerce.data.specifications;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.serpics.base.data.model.Store;
import com.serpics.commerce.core.CommerceEngine;
import com.serpics.commerce.data.model.Paymethodlookup;
import com.serpics.stereotype.DefaultSpec;

@DefaultSpec(Paymethodlookup.class)
public class DefaultPaymethodlookupSpecification implements Specification<Paymethodlookup>{
	@Resource
	CommerceEngine commerceEngine;
	

	@Override
	public Predicate toPredicate(Root<Paymethodlookup> root,
			CriteriaQuery<?> cq, CriteriaBuilder cb) {
			Store s = (Store) commerceEngine.getCurrentContext().getStoreRealm();
		
		return cb.equal(root.get("store"), s);
	}

}
