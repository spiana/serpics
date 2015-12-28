package com.serpics.commerce.facade;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.serpics.commerce.data.model.Cart;
import com.serpics.commerce.data.model.Order;
import com.serpics.commerce.data.model.Orderpayment;
import com.serpics.commerce.facade.data.OrderData;
import com.serpics.commerce.facade.data.OrderPaymentData;
import com.serpics.commerce.services.CartService;
import com.serpics.commerce.services.OrderService;
import com.serpics.core.facade.AbstractPopulatingConverter;
import com.serpics.membership.data.model.User;
import com.serpics.membership.services.UserService;
import com.serpics.stereotype.StoreFacade;

@StoreFacade("orderFacade")
public class OrderFacadeImpl implements OrderFacade {

	private Logger LOG = LoggerFactory.getLogger(OrderFacadeImpl.class);
	
	@Autowired
	UserService userService;
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	CartService cartService;
	
	@Autowired
	AbstractPopulatingConverter<Order,OrderData> orderConverter;
	
	@Override
	@Transactional
	public OrderData placeOrder(){
		LOG.debug("Invoke placeOrder");
		Cart cart = cartService.getSessionCart();
		
		LOG.debug("Convert cart with number "+cart.getId()+" in order");
		Order order = orderService.createOrder(cart);
		
		LOG.debug("Placed Order with number: "+order.getId()+" [Cart: "+cart.getId()+"]");
		return orderConverter.convert(order);
	}
	
	@Override
	@Transactional
	public OrderData addPayment(Long orderId,OrderPaymentData paymentData){
		
		LOG.debug("Retrieve order with "+orderId);
		Order order = orderService.getOrder(orderId);
		
		Orderpayment payment = new Orderpayment(paymentData.getPaymethod(),paymentData.getAmount());
		
		LOG.debug("add to order, payment");
		order = orderService.addPayment(order, payment);
		return orderConverter.convert(order);
	}

	@Override
	@Transactional
	public List<OrderData> getOrders() {
		User currentUser = userService.getCurrentCustomer();
		List<OrderData> orderDataList = new ArrayList<OrderData>();
		List<Order> orderList = orderService.getOrdersByUser(currentUser);
		for (Order order : orderList){
			orderDataList.add(orderConverter.convert(order));
		}
		return orderDataList;
	}

}
