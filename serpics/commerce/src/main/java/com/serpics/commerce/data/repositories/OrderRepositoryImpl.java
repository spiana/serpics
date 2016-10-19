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

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import com.serpics.commerce.data.model.Cart;
import com.serpics.commerce.data.model.Order;

public class OrderRepositoryImpl implements OrderRepositoryCustom {

    @PersistenceContext(name = "serpics")
    EntityManager entityManager;

    @Override
    @Transactional
    public Order createOrderFromcart(final Cart cart) {
    	 entityManager.detach(cart);
        final Query query = entityManager.createNativeQuery("update orders set pending=0 where order_id = :orderid ");
        final Query queryRow = entityManager
                .createNativeQuery("update orderitems set pending=0 where order_id = :orderid ");

        query.setParameter("orderid", cart.getId());
        queryRow.setParameter("orderid", cart.getId());
        query.executeUpdate();
        queryRow.executeUpdate();
        entityManager.flush();
       

        return entityManager.find(Order.class, cart.getId());
    }

}
