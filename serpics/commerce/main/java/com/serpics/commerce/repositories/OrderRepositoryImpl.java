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
	public Order createOrderFromcart(Cart cart) {
		Query query = entityManager.createNativeQuery("update orders set pending=0 where orders_id = :orderid ");
		query.setParameter("orderid", cart.getOrdersId());
		int i = query.executeUpdate();
		entityManager.flush();

		return entityManager.find(Order.class, cart.getOrdersId());
	}

}
