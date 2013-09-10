package com.serpics.membership.services;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.serpics.core.service.AbstractService;
import com.serpics.membership.persistence.PermanentAddress;
import com.serpics.membership.persistence.User;

public abstract class AbstractAddressService extends AbstractService{

	protected  static class AddressSpecification{
		protected static Specification<PermanentAddress> isAddressUser(final User user){
			return new Specification<PermanentAddress>() {
				@Override
				public Predicate toPredicate(Root<PermanentAddress> root,
						CriteriaQuery<?> cq, CriteriaBuilder cb) {
					return cb.equal(root.get("member") ,user);
				}
			};
			
		}
	}
}
