package com.serpics.base.data.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;

import com.serpics.base.data.model.BaseAttribute;
import com.serpics.commerce.core.CommerceEngine;
import com.serpics.stereotype.DefaultSpec;

@DefaultSpec(BaseAttribute.class)
public class DefaultBaseAttributeSpecification  implements Specification<BaseAttribute>{

	@Autowired
	CommerceEngine engine;
	
	@Override
	public Predicate toPredicate(Root<BaseAttribute> root,
			CriteriaQuery<?> cq, CriteriaBuilder cb) {
	    return cb.equal(root.get("storeId"), engine.getCurrentContext().getStoreId());
	}

}
