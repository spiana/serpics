package com.serpics.catalog.data.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;

import com.serpics.catalog.persistence.Catalog;
import com.serpics.catalog.persistence.Category;
import com.serpics.commerce.core.CommerceEngine;
import com.serpics.stereotype.BaseSpec;

@BaseSpec(Category.class)
public class DefaultCategorySpecification implements Specification<Category>{
	@Autowired
	CommerceEngine engine;
	
	@Override
	public Predicate toPredicate(Root<Category> root, CriteriaQuery<?> cq,
			CriteriaBuilder cb) {
		return cb.equal(root.get("catalog"),(Catalog)engine.getCurrentContext().getCatalog());
	}

}
