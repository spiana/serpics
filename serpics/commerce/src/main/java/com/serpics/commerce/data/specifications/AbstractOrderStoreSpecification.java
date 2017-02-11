package com.serpics.commerce.data.specifications;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.serpics.base.data.model.Store;
import com.serpics.commerce.core.CommerceEngine;
import com.serpics.commerce.data.model.AbstractOrder;
import com.serpics.stereotype.DefaultSpec;

@DefaultSpec(AbstractOrder.class)
public class AbstractOrderStoreSpecification implements Specification<AbstractOrder>{

	@Resource
	CommerceEngine commerceEngine;
	
	@Override
	public Predicate toPredicate(Root<AbstractOrder> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
		return cb.equal(root.get("store"), (Store)commerceEngine.getCurrentContext().getStoreRealm());
	}

}
