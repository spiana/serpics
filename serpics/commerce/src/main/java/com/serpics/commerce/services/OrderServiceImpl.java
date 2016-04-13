package com.serpics.commerce.services;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.serpics.catalog.ProductNotFoundException;
import com.serpics.commerce.EmptyCartException;
import com.serpics.commerce.OrderStatus;
import com.serpics.commerce.PaymentException;
import com.serpics.commerce.PaymentIntent;
import com.serpics.commerce.PaymentState;
import com.serpics.commerce.data.model.Cart;
import com.serpics.commerce.data.model.Order;
import com.serpics.commerce.data.model.Orderpayment;
import com.serpics.commerce.data.model.Payment;
import com.serpics.commerce.data.repositories.CartRepository;
import com.serpics.commerce.data.repositories.OrderRepository;
import com.serpics.commerce.data.specifications.OrderSpecification;
import com.serpics.commerce.event.PlaceOrderEvent;
import com.serpics.core.event.SerpicsPublisherEvent;
import com.serpics.core.service.AbstractService;
import com.serpics.membership.data.model.User;
import com.serpics.warehouse.InventoryNotAvailableException;

@SuppressWarnings("rawtypes")
@Service("orderService")
@Scope("store")
public class OrderServiceImpl extends AbstractService implements OrderService {

	@Resource
	CartRepository cartRepository;
	@Resource
	OrderRepository orderRepository;
	
	@Resource
	CartService cartService;
	
	@Resource
	PaymentService paymentService;

	@Resource
	SerpicsPublisherEvent eventPublisher;
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.serpics.commerce.services.OrderService#createOrder(com.serpics.commerce
	 * .persistence.Cart)
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Order createOrder(Cart cart) throws EmptyCartException {
		Order order = null;
		if (!cart.getCartitems().isEmpty()) {
			try {

				cartService.prepareCart(cart, true);
				order = orderRepository.createOrderFromcart(cart);
				order.setStatus(OrderStatus.CREATED);
				Payment payment = paymentService.findCurrentPendingPayment(order);
				if (payment != null && payment.getState().equals(PaymentState.CREATED)
						&& !payment.getIntent().equals(PaymentIntent.ORDER)) {
					paymentService.authorizePayment(payment);

					if (payment.getState().equals(PaymentState.APPROVED)
							|| payment.getState().equals(PaymentState.COMPLETED))
						order.setStatus(OrderStatus.READY);
					else if (payment.getState().equals(PaymentState.CANCELLED)
							|| payment.getState().equals(PaymentState.FAILED))
						order.setStatus(OrderStatus.ANNULLED);
				} else {
					order.setStatus(OrderStatus.WAITING_PAYMENT);
				}

			} catch (InventoryNotAvailableException | ProductNotFoundException | PaymentException e) {
				throw new RuntimeException(e);
			}
		} else {
			throw new EmptyCartException("Empty Cart Exception");
		}

		orderRepository.save(order);
		PlaceOrderEvent event = new PlaceOrderEvent(order);
		eventPublisher.publishSerpicsEvent(event);

		return order;

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
		
		return orderRepository.saveAndFlush(order);
	}

	@Override
	public Order getOrder(Long id) {
		Assert.notNull(id,"id order must not be null");
		
		Order order =orderRepository.findOne(id);
		
		return order;
	}

	@Override
	public List<Order> getOrdersByUser(User user) {
		return orderRepository.findAll(OrderSpecification.findByUser(user));
	}
}
