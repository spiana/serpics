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
package com.serpics.base.data.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;

import com.serpics.base.commerce.CommerceEngine;
import com.serpics.base.data.model.BaseAttribute;
import com.serpics.stereotype.DefaultSpec;

@DefaultSpec(BaseAttribute.class)
public class DefaultBaseAttributeSpecification  implements Specification<BaseAttribute>{

	@Autowired
	CommerceEngine engine;
	
	@Override
	public Predicate toPredicate(Root<BaseAttribute> root,
			CriteriaQuery<?> cq, CriteriaBuilder cb) {
	    return cb.equal(root.get("storeId"), engine.getCurrentContext().getStoreId());
	}

}
