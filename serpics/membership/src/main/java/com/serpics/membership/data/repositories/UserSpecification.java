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



import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.serpics.base.data.model.Store;
import com.serpics.membership.UserType;
import com.serpics.membership.data.model.User;




public class UserSpecification {
	

    public static Specification<User> isUserInStore(final Store store){
        return new Specification<User>() {
            @Override
            public Predicate toPredicate(final Root<User> root, final CriteriaQuery<?> query,
                    final CriteriaBuilder cb) {

                return cb.equal(root.join("stores").as(Store.class), store);
            }
        };
    }
    

    public static Specification<User> searchByName(final String name,final String type){
        return new Specification<User>() {
            @Override
            public Predicate toPredicate(final Root<User> root, final CriteriaQuery<?> query,
                    final CriteriaBuilder cb) {
            	Expression<String> e = null;
            	Predicate nameLike = null;
            	if(type.equals("name")) {
            		e = root.get("email");
            		nameLike = cb.like(e, "%" +name +"%");
            	}
            	if(type.equals("logonid")) {
            		e = root.get("email");
            		
            		//Root<UsersReg> reg = query.from(UsersReg.class);
            		//e = reg.get("logonid");
            		
            		nameLike = cb.like(e, "%" +name +"%");
            		
            	}
            	
                return nameLike;
            }
        };
    }
    
    public static Specification<User> findByUserType(final UserType userType){
        return new Specification<User>() {
            @Override
            public Predicate toPredicate(final Root<User> root, final CriteriaQuery<?> query,
                    final CriteriaBuilder cb) {
            	return cb.equal(root.get("userType"), userType);
            }
        };
    }

}
