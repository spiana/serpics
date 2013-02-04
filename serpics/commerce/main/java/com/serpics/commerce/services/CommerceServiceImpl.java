package com.serpics.commerce.services;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.serpics.catalog.hooks.InventoryHook;
import com.serpics.commerce.hooks.DiscountHook;
import com.serpics.commerce.persistence.AbstractOrder;
import com.serpics.commerce.persistence.Orderitem;
import com.serpics.commerce.persistence.Orderpayment;
import com.serpics.commerce.persistence.Suborder;
import com.serpics.commerce.persistence.dao.OrderFactory;
import com.serpics.core.security.UserPrincipal;
import com.serpics.core.service.AbstractService;
import com.serpics.core.session.CommerceSessionContext;
import com.serpics.membership.persistence.User;
import com.serpics.membership.persistence.dao.MemberFactory;
import com.serpics.warehouse.InventoryNotAvailableException;

public class CommerceServiceImpl extends AbstractService implements CommerceService {

	@Autowired(required = true)
	MemberFactory memberFactory;

	public void setMembershipService(MemberFactory membershipService) {
		this.memberFactory = membershipService;
	}

	@Autowired
	OrderFactory orderFactory;

	public OrderFactory getOrderFactory() {
		return orderFactory;
	}

	public void setOrderFactory(OrderFactory orderFactory) {
		this.orderFactory = orderFactory;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = InventoryNotAvailableException.class)
	public Orderitem cartUpdate(CommerceSessionContext context, Orderitem orderitem, AbstractOrder order)
			throws InventoryNotAvailableException {
		return cartAdd(context, orderitem, order, false);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = InventoryNotAvailableException.class)
	public Orderitem cartUpdate(CommerceSessionContext context, Orderitem orderitem)
			throws InventoryNotAvailableException {
		return cartAdd(context, orderitem, null, false);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = InventoryNotAvailableException.class)
	public Orderitem cartAdd(CommerceSessionContext context, Orderitem orderitem, boolean merge)
			throws InventoryNotAvailableException {
		return cartAdd(context, orderitem, null, merge);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = InventoryNotAvailableException.class)
	public Orderitem cartAdd(CommerceSessionContext context, Orderitem orderitem, AbstractOrder order, boolean merge)
			throws InventoryNotAvailableException {
		DiscountHook h = (DiscountHook) getHook("discountHook", context);
		UserPrincipal user = context.getUserPrincipal();
		orderitem.setStatus(AbstractOrder.PENDING);
		orderitem.setUserId(user.getUserId());
		orderitem.setCustomerId(context.getCustomerId());
		orderitem.setStoreId(context.getStoreId());

		if (order != null) {
			order = (AbstractOrder) orderFactory.merge(order);
		}
		if (merge) {
			Iterator<Orderitem> items;
			if (order == null) {
				items = orderFactory.fetchCurrentOrderItem(user.getUserId(), context.getCustomerId(),
						context.getStoreId()).iterator();
			} else {
				items = order.getOrderitems().iterator();
			}
			while (items.hasNext()) {
				Orderitem oi = items.next();
				if (oi.getSku().equals(orderitem.getSku())) {
					orderitem.setQuantity(orderitem.getQuantity() + oi.getQuantity());
					oi = (Orderitem) orderFactory.merge(oi);
					oi.setOrder(null); // Detach from order
					orderFactory.delete(oi);
					items.remove();
				}
			}

		}

		if (order != null)
			orderitem.setOrder(order);

		if (!checkRowInventory(orderitem, context))
			throw new InventoryNotAvailableException(orderitem.getSku(), orderitem.getQuantity());

		h.applyItemDiscount(orderitem);

		return (Orderitem) orderFactory.merge(orderitem);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = InventoryNotAvailableException.class)
	public AbstractOrder prepareOrder(CommerceSessionContext context) throws InventoryNotAvailableException {
		UserPrincipal u = context.getUserPrincipal();
		AbstractOrder order = orderFactory.fetchCurrentOrder(u.getUserId(), context.getCustomerId(),
				context.getStoreId());

		if (order == null) {
			order = new AbstractOrder(u.getUserId(), context.getStoreId());
			order.setCurrency("EUR");
			order.setCustomerId(context.getCustomerId());
		}
		return prepareOrder(order, context);
	}

	protected Set<Orderitem> prepareSubOrder(Set<Orderitem> items) {
		Suborder sorder = new Suborder();

		return null;
	}

	protected AbstractOrder prepareOrder(AbstractOrder order, CommerceSessionContext context)
			throws InventoryNotAvailableException {
		UserPrincipal u = context.getUserPrincipal();

		// if Billing address null set with customer principal Address
		if (order.getBillingAddress() == null) {
			User customer = (User) memberFactory.find(User.class, context.getCustomerId());
			order.setBillingAddress(customer.getPrimaryAddress());
		}
		// if shipping address null use the billing Address
		if (order.getShippingAddress() == null)
			order.setShippingAddress(order.getBillingAddress());

		// add all pending item rows to new Order
		List<Orderitem> items = orderFactory.fetchCurrentOrderItem(u.getUserId(), context.getCustomerId(),
				context.getStoreId());

		for (Orderitem orderitem : items) {
			orderitem.setOrder(order);
			if (orderitem.getShippingAddressId() == null)
				orderitem.setShippingAddressId(order.getShippingAddress().getAddressId());
		}
		order.getOrderitems().addAll(items);

		checkInventory(order, context);

		BigDecimal totalCost = new BigDecimal(0);
		BigDecimal totalPrice = new BigDecimal(0);
		BigDecimal totalNetPrice = new BigDecimal(0);

		for (Orderitem orderitem : order.getOrderitems()) {

			BigDecimal qta = new BigDecimal(orderitem.getQuantity());

			BigDecimal netPrice = orderitem.getSkuNetPrice().multiply(qta);
			BigDecimal price = orderitem.getSkuPrice().multiply(qta);
			BigDecimal cost = orderitem.getSkuCost().multiply(qta);

			totalCost = totalCost.add(cost);
			totalPrice = totalPrice.add(price);
			totalNetPrice = totalNetPrice.add(netPrice);
		}

		order.setTotalProduct(totalNetPrice);
		order.setOrderAmount(totalNetPrice);

		order = applyOrderDiscount(order, context);

		order = calculateShipping(order, context);
		order.setOrderAmount(order.getOrderAmount().add(order.getTotalShipping()));
		order = calculateTaxes(order, context);
		order.setOrderAmount(order.getOrderAmount().add(order.getTotalTax()));

		return (AbstractOrder) orderFactory.merge(order);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = InventoryNotAvailableException.class)
	public AbstractOrder completeOrder(CommerceSessionContext context) throws InventoryNotAvailableException {
		AbstractOrder o = this.prepareOrder(context);

		updateInventory(o, context);

		for (Orderpayment payment : o.getOrderpayments()) {
			o.setPayAmount(o.getPayAmount().add(payment.getAmount()));
		}

		o.setStatus(AbstractOrder.WAITING);
		changeOrderItemStatus(o);

		o = (AbstractOrder) orderFactory.merge(o);

		return o;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public AbstractOrder addPayment(CommerceSessionContext context, AbstractOrder order, Orderpayment payment) {

		if (order.getUserId() != context.getUserPrincipal().getUserId())
			; // TODO throws invalid order access exception

		payment.setOrder(order);
		orderFactory.persist(payment);
		order.setPayAmount(order.getPayAmount().add(payment.getAmount()));

		return order;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public AbstractOrder closeOrder(CommerceSessionContext context, AbstractOrder order) {
		order = (AbstractOrder) orderFactory.merge(order);

		if (order.getUserId() != context.getUserPrincipal().getUserId())
			; // TODO throws invalid order access exception

		order.setStatus(AbstractOrder.COMPLETE);
		changeOrderItemStatus(order);

		return order;
	}

	private void changeOrderItemStatus(AbstractOrder o) {

		for (Orderitem oi : o.getOrderitems()) {
			oi.setStatus(o.getStatus());
			orderFactory.merge(oi);
		}
	}

	protected void checkInventory(AbstractOrder o, CommerceSessionContext context)
			throws InventoryNotAvailableException {
		InventoryHook h = (InventoryHook) this.getHook("inventoryHook", context);
		for (Orderitem oi : o.getOrderitems()) {
			if (!h.checkInventory(oi))
				throw new InventoryNotAvailableException(oi.getSku(), oi.getQuantity());
		}
	}

	protected boolean checkRowInventory(Orderitem item, CommerceSessionContext context) {
		InventoryHook h = (InventoryHook) this.getHook("inventoryHook", context);
		return h != null ? h.checkInventory(item) : true;
	}

	protected void updateInventory(AbstractOrder order, CommerceSessionContext context)
			throws InventoryNotAvailableException {
		// TODO: to implement
	}

	protected AbstractOrder calculateShipping(AbstractOrder order, CommerceSessionContext context) {
		// TODO: to implement
		return order;
	}

	protected AbstractOrder calculateTaxes(AbstractOrder order, CommerceSessionContext context) {
		// TODO: to implement
		return order;
	}

	protected AbstractOrder applyOrderDiscount(AbstractOrder o, CommerceSessionContext context) {
		DiscountHook h = (DiscountHook) this.getHook("discountHook", context);

		return h != null ? h.applyOrderDiscount(o) : o;
	}

	@Override
	public List<Orderitem> fetchPendingItems(CommerceSessionContext context) {
		return orderFactory.fetchCurrentOrderItem(context.getUserPrincipal().getUserId(), context.getCustomerId(),
				context.getStoreId());
	}

}
