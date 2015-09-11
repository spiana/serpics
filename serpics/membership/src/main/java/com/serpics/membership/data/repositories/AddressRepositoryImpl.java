package com.serpics.membership.data.repositories;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.serpics.membership.AddressType;
import com.serpics.membership.data.model.AbstractAddress;
import com.serpics.membership.data.model.Address;
import com.serpics.membership.data.model.BillingAddress;
import com.serpics.membership.data.model.PermanentAddress;

public class ExtendAddressRepositoryImpl implements ExtendAddressRepository {
	
	@PersistenceContext(name = "serpics")
	EntityManager entityManager;

	
	private Address doClone(AbstractAddress a){
		a.setId(null);
		a.setFlag(AddressType.TEMPORARY);
		entityManager.persist(a);

		return entityManager.find(Address.class, a.getId());
	}
	
	@Override
	public Address clone(BillingAddress address) {
		return doClone(address);
	}

	@Override
	public Address clone(PermanentAddress address) {
		return doClone( address);
	}

}
