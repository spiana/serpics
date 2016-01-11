package com.serpics.warehouse.data.specification;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.serpics.base.data.model.Store;
import com.serpics.commerce.core.CommerceEngine;
import com.serpics.stereotype.DefaultSpec;
import com.serpics.warehouse.data.model.Inventory;

@DefaultSpec(Inventory.class)
public class DefaultInventorySpecification implements Specification<Inventory>{
	@Resource
	CommerceEngine commerceEngine;

	@Override
	public Predicate toPredicate(Root<Inventory> root, CriteriaQuery<?> cq,
			CriteriaBuilder cb) {
		
		return cb.equal(root.get("store"),(Store) commerceEngine.getCurrentContext().getStoreRealm());
	}
}
