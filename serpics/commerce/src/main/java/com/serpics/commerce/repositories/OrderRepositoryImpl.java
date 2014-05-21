package com.serpics.commerce.repositories;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import com.serpics.commerce.persistence.Cart;
import com.serpics.commerce.persistence.Order;

public class OrderRepositoryImpl implements OrderRepositoryCustom {

    @PersistenceContext(name = "serpics")
    EntityManager entityManager;

    @Override
    @Transactional
    public Order createOrderFromcart(final Cart cart) {
        final Query query = entityManager.createNativeQuery("update orders set pending=0 where order_id = :orderid ");
        final Query queryRow = entityManager
                .createNativeQuery("update orderitems set pending=0 where order_id = :orderid ");

        query.setParameter("orderid", cart.getOrderId());
        queryRow.setParameter("orderid", cart.getOrderId());
        query.executeUpdate();
        queryRow.executeUpdate();
        entityManager.flush();
        entityManager.detach(cart);

        return entityManager.find(Order.class, cart.getOrderId());
    }

}
