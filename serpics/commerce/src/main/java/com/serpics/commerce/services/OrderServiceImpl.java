package com.serpics.commerce.services;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.serpics.commerce.data.model.Cart;
import com.serpics.commerce.data.model.Order;
import com.serpics.commerce.data.model.Orderpayment;
import com.serpics.commerce.data.repositories.CartRepository;
import com.serpics.commerce.data.repositories.OrderRepository;
import com.serpics.core.service.AbstractService;

@Service("orderService")
@Scope("store")
public class OrderServiceImpl extends AbstractService implements OrderService {

	@Resource
	CartRepository cartRepository;
	@Resource
	OrderRepository orderRepository;
	

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.serpics.commerce.services.OrderService#createOrder(com.serpics.commerce
	 * .persistence.Cart)
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Order createOrder(Cart cart) {
		return orderRepository.createOrderFromcart(cart);

	}

	@Override
	@Transactional
	public Order addPayment(Order order,Orderpayment payment) {
		Assert.notNull(order , "order must not be null !");
		Assert.notNull(payment , "payment must not be null !");
		Assert.notNull(payment.getPaymethod() , "PaymentMethod must non be null in orderPayment !");
		Assert.notNull(payment.getAmount() , "Amount must non be null in orderPayment !");
		order = orderRepository.findOne(order.getId());
	
		Double toPay = order.getOrderAmount().doubleValue() - order.getPayAmount().doubleValue();
		if (payment.getAmount() > toPay){
			throw new RuntimeException(String.format("paymemt (%s) amount greater than to pay amount (%s) !" , payment.getAmount() , toPay));
		}
		order.setPayAmount(order.getPayAmount() + payment.getAmount());
		payment.setOrder(order);
		order.getOrderpayments().add(payment);
		
		return orderRepository.update(order);
	}
}
