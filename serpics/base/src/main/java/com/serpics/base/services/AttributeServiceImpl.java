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
package com.serpics.base.services;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.serpics.base.AvailableforType;
import com.serpics.base.data.model.BaseAttribute;
import com.serpics.base.data.repositories.BaseAttributeRepository;
import com.serpics.commerce.service.AbstractCommerceService;

@Service("attributeService")
public class AttributeServiceImpl extends AbstractCommerceService implements AttributeService{

    @Autowired
    BaseAttributeRepository baseAttributeRepository;

       @Override
    public List<BaseAttribute> findbyAvailablefor(final AvailableforType availablefor, final Pageable page) {
        return baseAttributeRepository.findAll(new Specification<BaseAttribute>() {
			@Override
			public Predicate toPredicate(Root<BaseAttribute> root,
					CriteriaQuery<?> cq, CriteriaBuilder cb) {
				   return cb.equal(root.get("availablefor"), availablefor);
            }
        	
        });
       }       

    
}
