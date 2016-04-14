package com.serpics.commerce.facade;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import com.serpics.catalog.ProductNotFoundException;
import com.serpics.commerce.EmptyCartException;
import com.serpics.commerce.data.model.Cart;
import com.serpics.commerce.data.model.Order;
import com.serpics.commerce.data.model.Orderpayment;
import com.serpics.commerce.facade.data.CartData;
import com.serpics.commerce.facade.data.CartItemData;
import com.serpics.commerce.facade.data.OrderData;
import com.serpics.commerce.facade.data.OrderPaymentData;
import com.serpics.commerce.services.CartService;
import com.serpics.commerce.services.OrderService;
import com.serpics.core.facade.AbstractPopulatingConverter;
import com.serpics.membership.data.model.User;
import com.serpics.membership.services.UserService;
import com.serpics.stereotype.StoreFacade;
import com.serpics.warehouse.InventoryNotAvailableException;

@StoreFacade("orderFacade")
@Transactional(readOnly = true)
public class OrderFacadeImpl implements OrderFacade {

	private Logger LOG = LoggerFactory.getLogger(OrderFacadeImpl.class);

	@Autowired
	UserService userService;

	@Autowired
	OrderService orderService;

	@Autowired
	CartService cartService;

	@Autowired
	CartFacade cartFacade;

	@Autowired
	AbstractPopulatingConverter<Order, OrderData> orderConverter;

	@Override
	@Transactional
	public OrderData createOrder(CartData cartData) {
		OrderData orderData = null;
		try {
			cartService.removeCartFromSession();
			Cart cart = cartService.getSessionCart();
			cart = updateCartFromCartData(cartData, cart);
			cart = cartService.prepareCart(cart);
			Order order = orderService.createOrder(cart);
			cartService.removeCartFromSession();
			orderData = orderConverter.convert(order);

		} catch (InventoryNotAvailableException e) {
			LOG.error("InventoryNotAvailable", e);
		} catch (ProductNotFoundException e) {
			LOG.error("ProductNotFound", e);
		} catch (EmptyCartException e) {
			LOG.error("Empty Cart Exception", e);
		}
		return orderData;
	}

	@Override
	@Transactional
	public OrderData placeOrder() {
		LOG.debug("Invoke placeOrder");
		Cart cart = cartService.getSessionCart();

		LOG.debug("Convert cart with number " + cart.getId() + " in order");
		Order order = null;
		try {
			order = orderService.createOrder(cart);
			LOG.debug("Placed Order with number: " + order.getId() + " [Cart: " + cart.getId() + "]");
			cartService.removeCartFromSession();
		} catch (EmptyCartException e) {
			LOG.error("Empty Cart Exception", e);
		}
		return orderConverter.convert(order);
	}

	@Override
	@Transactional
	public OrderData addPayment(Long orderId, OrderPaymentData paymentData) {

		LOG.debug("Retrieve order with " + orderId);
		Order order = orderService.getOrder(orderId);

		Orderpayment payment = new Orderpayment(paymentData.getPaymethod(), paymentData.getAmount());

		LOG.debug("add to order, payment");
		order = orderService.addPayment(order, payment);
		return orderConverter.convert(order);
	}

	@Override
	public List<OrderData> getOrders() {
		User currentUser = userService.getCurrentCustomer();
		List<OrderData> orderDataList = new ArrayList<OrderData>();
		List<Order> orderList = orderService.getOrdersByUser(currentUser);
		for (Order order : orderList) {
			orderDataList.add(orderConverter.convert(order));
		}
		return orderDataList;
	}

	private Cart updateCartFromCartData(CartData cartData, Cart cart)
			throws InventoryNotAvailableException, ProductNotFoundException {
		cartFacade.addBillingAddress(cartData.getBillingAddress());
		cartFacade.addShippingAddress(cartData.getShippingAddress());

		if (cartData.getShipmode() != null) {
			if (cartData.getShipmode().getName() != null) {
				cartService.addShipmode(cartData.getShipmode().getName());
			}
		}

		if (cartData.getPaymethod() != null) {
			if (cartData.getPaymethod().getName() != null) {
				cartService.addPaymethod(cartData.getPaymethod().getName());
			}
		}

		Set<CartItemData> cartItemsData = cartData.getOrderItems();
		for (CartItemData item : cartItemsData) {
			cartService.cartAdd(item.getSku(), item.getQuantity(), cart, true);
		}

		return cart;
	}

	@Override
	public Page<OrderData> getPagedOrders(Pageable page) {

		List<OrderData> listOrders = getOrders();
		Page<OrderData> list = new PageImpl<OrderData>(listOrders, page, listOrders.size());
		return list;

	}

}
