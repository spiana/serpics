package com.serpics.commerce.services;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.serpics.commerce.persistence.Cart;
import com.serpics.commerce.persistence.Order;
import com.serpics.commerce.persistence.Orderitem;
import com.serpics.commerce.repositories.CartRepository;
import com.serpics.commerce.repositories.OrderRepository;
import com.serpics.core.service.AbstractService;

@Service("orderService")
@Scope("store")
public class OrderServiceImpl extends AbstractService implements OrderService {

	@Resource
	CartRepository cartRepository;
	@Resource
	OrderRepository orderRepository;

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Order createOrder(Cart cart) {

		Order order = new Order();
		order.setBillingAddress(cart.getBillingAddress());
		order.setCookie(cart.getCookie());
		order.setCurrency(cart.getCurrency());
		order.setCustomerId(cart.getCustomerId());
		order.setDiscountAmount(cart.getDiscountAmount());
		order.setDiscountPerc(cart.getDiscountPerc());
		order.setOrderitems(cart.getOrderitems());
		order.setOrdersAttributes(cart.getOrdersAttributes());
		order.setShipmode(cart.getShipmode());
		order.setShippingAddress(cart.getShippingAddress());
		order.setStoreId(cart.getStoreId());
		order.setSuborders(cart.getSuborders());
		order.setUserId(cart.getUserId());
		for (Orderitem item : order.getOrderitems()) {
			item.setOrder(order);
		}
		cartRepository.delete(cart);

		return orderRepository.saveAndFlush(order);

	}
}
