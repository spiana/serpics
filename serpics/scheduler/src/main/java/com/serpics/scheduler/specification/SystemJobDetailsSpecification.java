package com.serpics.scheduler.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.serpics.scheduler.model.SystemJobDetails;
import com.serpics.stereotype.DefaultSpec;

@DefaultSpec(SystemJobDetails.class)
public class SystemJobDetailsSpecification implements Specification<SystemJobDetails> {

	@Override
	public Predicate toPredicate(Root<SystemJobDetails> arg0, CriteriaQuery<?> arg1, CriteriaBuilder cb) {
		return cb.isNull(arg0.get("store"));
	}

}
