package com.serpics.commerce.services;

import java.util.List;

import com.serpics.commerce.persistence.AbstractOrder;
import com.serpics.commerce.persistence.Orderitem;
import com.serpics.commerce.persistence.Orderpayment;
import com.serpics.core.session.CommerceSessionContext;
import com.serpics.warehouse.InventoryNotAvailableException;

public interface CommerceService {

	public List<Orderitem> fetchPendingItems(CommerceSessionContext context);

	public AbstractOrder prepareOrder(CommerceSessionContext context) throws InventoryNotAvailableException;

	public AbstractOrder completeOrder(CommerceSessionContext context) throws InventoryNotAvailableException;

	public AbstractOrder addPayment(CommerceSessionContext context, AbstractOrder order, Orderpayment payment);

	public AbstractOrder closeOrder(CommerceSessionContext context, AbstractOrder order);

	public Orderitem cartUpdate(CommerceSessionContext context, Orderitem orderitem, AbstractOrder order)
			throws InventoryNotAvailableException;

	public Orderitem cartAdd(CommerceSessionContext context, Orderitem orderitem, AbstractOrder order, boolean merge)
			throws InventoryNotAvailableException;

	public Orderitem cartUpdate(CommerceSessionContext context, Orderitem orderitem)
			throws InventoryNotAvailableException;

	public Orderitem cartAdd(CommerceSessionContext context, Orderitem orderitem, boolean merge)
			throws InventoryNotAvailableException;
}
