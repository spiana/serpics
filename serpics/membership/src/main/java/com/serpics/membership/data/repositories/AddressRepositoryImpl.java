/*******************************************************************************
 * Copyright 2014-2016  StepFour Srl
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *  
 *      http://www.apache.org/licenses/LICENSE-2.0
 *  
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *  
 *******************************************************************************/
package com.serpics.membership.data.repositories;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import com.serpics.membership.data.model.AbstractAddress;
import com.serpics.membership.data.model.Address;
import com.serpics.membership.data.model.BillingAddress;
import com.serpics.membership.data.model.PermanentAddress;

public class AddressRepositoryImpl implements ExtendAddressRepository {
	
	@PersistenceContext(name = "serpics")
	EntityManager entityManager;

	
	private Address doClone(AbstractAddress a){
		AbstractAddress _a = a.clone();
		entityManager.persist((AbstractAddress) _a);
	    entityManager.detach(_a);
		
		final Query query = entityManager.createNativeQuery("update addresses set flag='TEMPORARY' where address_id = :address_id ");
	    query.setParameter("address_id", _a.getId());
	    query.executeUpdate();
	    
	    entityManager.flush();
	
		return entityManager.find(Address.class, _a.getId());
	}
	
	@Override
	@Transactional
	public Address clone(BillingAddress address) {
		return doClone(address);
	}

	@Override
	@Transactional
	public Address clone(PermanentAddress address) {
		return doClone( address);
	}

}
