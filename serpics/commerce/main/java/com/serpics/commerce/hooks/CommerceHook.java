package com.serpics.commerce.hooks;

import com.serpics.commerce.persistence.AbstractOrder;
import com.serpics.commerce.persistence.Orderitem;

public interface CommerceHook {
	public void calculateTax(AbstractOrder order);

	public void calculateShipping(Orderitem orderitem);

	public void calculateShiping(AbstractOrder order);

	public void calculateProductTotal(AbstractOrder order);

	public void calculateOrderTotale(AbstractOrder order);

}
