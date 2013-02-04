package com.serpics.commerce.hooks;

import com.serpics.commerce.persistence.AbstractOrder;
import com.serpics.commerce.persistence.Orderitem;

public interface DiscountHook {

	public Orderitem applyItemDiscount(Orderitem orderItem);
	public AbstractOrder applyOrderDiscount(AbstractOrder order);
}
