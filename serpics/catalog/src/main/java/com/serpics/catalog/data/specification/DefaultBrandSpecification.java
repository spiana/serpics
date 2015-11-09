package com.serpics.catalog.data.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;

import com.serpics.catalog.data.model.Brand;
import com.serpics.catalog.data.model.Catalog;
import com.serpics.catalog.data.model.Product;
import com.serpics.commerce.core.CommerceEngine;
import com.serpics.stereotype.DefaultSpec;

@DefaultSpec(Brand.class)
public class DefaultBrandSpecification implements Specification<Brand>{
	@Autowired
	CommerceEngine engine;
	
	@Override
	public Predicate toPredicate(Root<Brand> root, CriteriaQuery<?> cq,
			CriteriaBuilder cb) {
		return cb.equal(root.get("catalog"),(Catalog)engine.getCurrentContext().getCatalog());
	}
	
	


}
