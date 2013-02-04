package com.serpics.commerce.repositories;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.serpics.commerce.persistence.Cart;
import com.serpics.commerce.persistence.Order;

public class OrderRepositoryImpl implements OrderRepositoryCustom {

	@PersistenceContext(name = "serpics")
	EntityManager entityManager;

	@Override
	public Order createOrderFromcart(Cart cart) {

		return null;
	}

}
