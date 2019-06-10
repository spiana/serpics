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
package com.serpics.commerce.data.specifications;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.serpics.base.commerce.CommerceEngine;
import com.serpics.base.data.model.Store;
import com.serpics.commerce.data.model.Paymethodlookup;
import com.serpics.stereotype.DefaultSpec;

@DefaultSpec(Paymethodlookup.class)
public class DefaultPaymethodlookupSpecification implements Specification<Paymethodlookup>{
	@Resource
	CommerceEngine commerceEngine;
	

	@Override
	public Predicate toPredicate(Root<Paymethodlookup> root,
			CriteriaQuery<?> cq, CriteriaBuilder cb) {
			Store s = (Store) commerceEngine.getCurrentContext().getStoreRealm();
		
		return cb.equal(root.get("store"), s);
	}

}
