package com.serpics.membership.services;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;

import com.serpics.commerce.service.AbstractCommerceEntityService;
import com.serpics.core.data.Repository;
import com.serpics.core.data.SpecificationUtis;
import com.serpics.core.service.AbstractService;
import com.serpics.core.session.SessionContext;
import com.serpics.membership.data.model.AbstractAddress;
import com.serpics.membership.data.model.PermanentAddress;
import com.serpics.membership.data.model.User;
import com.serpics.membership.repositories.PermanentAddressRepository;

@Service("permanentAddressService")
@Scope("store")
public class PermanentAddressServiceImpl extends AbstractCommerceEntityService<PermanentAddress, Long> implements PermanentAddressService {

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
	
	@Autowired
	PermanentAddressRepository addressRepository;

	@Override
	public Repository<PermanentAddress, Long> getEntityRepository() {
		return addressRepository;
	}

	@Override
	public Specification<PermanentAddress> getBaseSpec() {
		return new Specification<PermanentAddress>() {
			@Override
			public Predicate toPredicate(Root<PermanentAddress> root,
					CriteriaQuery<?> arg1, CriteriaBuilder arg2) {
				// TODO fix primary filter
				return arg2.isNotNull(root.get("uuid"));
			}
		};
	}



}
