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
package com.serpics.commerce.data.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.serpics.base.data.model.Store;
import com.serpics.commerce.data.model.Cart;
import com.serpics.core.data.Repository;
import com.serpics.membership.data.model.Member;

public interface CartRepository extends Repository<Cart, Long> {

	public Cart findByCookie(String cookie);
	
		
	@Query("select c from Cart c where c.user= :user and c.customer = :customer and c.store = :store and c.cookie != :sessionId")
	public Page<Cart> findAllCartByUserId(@Param("user") Member user, @Param("customer") Member customer, @Param("store") Store store,@Param("sessionId") String sessionId, Pageable pageable );

}
