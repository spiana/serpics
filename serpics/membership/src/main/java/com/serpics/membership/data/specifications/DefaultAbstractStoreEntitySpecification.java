package com.serpics.membership.data.specifications;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.serpics.base.data.model.AbstractStoreEntity;
import com.serpics.base.data.model.Store;
import com.serpics.commerce.core.CommerceEngine;
import com.serpics.stereotype.DefaultSpec;

@DefaultSpec(AbstractStoreEntity.class)
public class DefaultAbstractStoreEntitySpecification  implements Specification<AbstractStoreEntity>{

	@Resource
	CommerceEngine commerceEngine;
	
	@Override
	public Predicate toPredicate(Root<AbstractStoreEntity> root,
			CriteriaQuery<?> cq, CriteriaBuilder cb) {
		
		return cb.equal(root.get("store"),(Store) commerceEngine.getCurrentContext().getStoreRealm());
	}

}
