package com.serpics.membership.services;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;

import com.serpics.core.data.SpecificationUtis;
import com.serpics.core.service.AbstractService;
import com.serpics.membership.persistence.AbstractAddress;
import com.serpics.membership.persistence.PermanentAddress;
import com.serpics.membership.persistence.User;
import com.serpics.membership.repositories.PermanentAddressRepository;

public class PermanentAddressServiceImpl extends AbstractService implements PermanentAddressService {

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
	public PermanentAddress create(PermanentAddress entity) {
		User user = (User) getCurrentContext().getCustomer();
		entity.setMember(user);
		return addressRepository.saveAndFlush(entity);
	}

	@Override
	public void delete(PermanentAddress entity) {
		 addressRepository.delete(entity);
	}

	@Override
	public Page<PermanentAddress> findAll(Pageable page) {
		User user = (User) getCurrentContext().getCustomer();
		return addressRepository.findAll(AddressSpecification.isAddressUser(user), page);
	}

	@Override
	public List<PermanentAddress> findAll() {
		User user = (User) getCurrentContext().getCustomer();
		return addressRepository.findAll(AddressSpecification.isAddressUser(user));
	}

	@Override
	public PermanentAddress update(PermanentAddress entity) {
		return addressRepository.save(entity);
	}

	@Override
	public List<PermanentAddress> findByexample(PermanentAddress example) {
		return addressRepository.findAll(addressRepository.makeSpecification(example));
	}

	@Override
	public PermanentAddress findOne(Long id) {
		return addressRepository.findOne(id);
	}

	@Override
	public List<PermanentAddress> findAll(Specification<PermanentAddress> spec,
			Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PermanentAddress> findAll(Specification<PermanentAddress> spec,
			Pageable page) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PermanentAddress findOne(Specification<PermanentAddress> spec,
			Sort sort, int index) {
		// TODO Auto-generated method stub
		return null;
	}



}
