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
import org.springframework.stereotype.Service;

import com.serpics.core.data.Repository;
import com.serpics.core.service.AbstractEntityService;
import com.serpics.core.service.AbstractService;
import com.serpics.core.service.EntityService;
import com.serpics.membership.persistence.Address;
import com.serpics.membership.persistence.User;
import com.serpics.membership.repositories.AddressRepository;

@Service("addressService")
@Scope("store")
public class AddressServiceImpl extends AbstractEntityService<Address, Long> implements AddressService {


	protected  static class AddressSpecification{
		protected static Specification<Address> isAddressUser(final User user){
			return new Specification<Address>() {
				@Override
				public Predicate toPredicate(Root<Address> root,
						CriteriaQuery<?> cq, CriteriaBuilder cb) {
					return cb.equal(root.get("member") ,user);
				}
			};
			
		}
	}
	
	@Autowired
	AddressRepository addressRepository;

	public Address create(Address entity , User user) {
		entity.setMember(user);
		return addressRepository.saveAndFlush(entity);
	}
	
	@Override
	public Address create(Address entity) {
		User user = (User) getCurrentContext().getCustomer();
		return create(entity, user);
	}

	@Override
	public void delete(Address entity) {
		 addressRepository.delete(entity);
	}

	@Override
	public Page<Address> findAll(Pageable page) {
		User user = (User) getCurrentContext().getCustomer();
		return addressRepository.findAll(AddressSpecification.isAddressUser(user), page);
	}

	@Override
	public List<Address> findAll() {
		User user = (User) getCurrentContext().getCustomer();
		return addressRepository.findAll(AddressSpecification.isAddressUser(user));
	}

	@Override
	public Address update(Address entity) {
		return addressRepository.save(entity);
	}

	@Override
	public List<Address> findByexample(Address example) {
		return addressRepository.findAll(addressRepository.makeSpecification(example));
	}

	@Override
	public Address findOne(Long id) {
		return addressRepository.findOne(id);
	}

	@Override
	public Repository<Address, Long> getEntityRepository() {
		
		return addressRepository;
	}

	@Override
	public Specification<Address> getBaseSpec() {
		
		return null;
	}

}
