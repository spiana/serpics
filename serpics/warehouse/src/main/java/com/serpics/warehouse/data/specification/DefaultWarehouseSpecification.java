package com.serpics.warehouse.data.specification;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.serpics.commerce.core.CommerceEngine;
import com.serpics.membership.data.model.Store;
import com.serpics.stereotype.DefaultSpec;
import com.serpics.warehouse.data.model.Warehouse;

@DefaultSpec(Warehouse.class)
public class DefaultWarehouseSpecification implements Specification<Warehouse>{
	@Resource
	CommerceEngine commerceEngine;

	@Override
	public Predicate toPredicate(Root<Warehouse> root, CriteriaQuery<?> cq,
			CriteriaBuilder cb) {
		
		return cb.equal(root.get("store"),(Store) commerceEngine.getCurrentContext().getStoreRealm());
	}
}
