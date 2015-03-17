package com.serpics.base.test;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.serpics.base.persistence.Locale;
import com.serpics.stereotype.DefaultSpec;

@DefaultSpec(Locale.class)
public class LocaleSpecification implements Specification<Locale> {

	@Override
	public Predicate toPredicate(final Root<Locale> arg0,
			final CriteriaQuery<?> arg1, final CriteriaBuilder arg2) {
		return arg2.isNotNull(arg0.get("uuid"));
	}

}
