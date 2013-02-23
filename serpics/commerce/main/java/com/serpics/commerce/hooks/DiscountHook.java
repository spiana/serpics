package com.serpics.commerce.hooks;

import com.serpics.commerce.persistence.AbstractOrder;
import com.serpics.commerce.persistence.Orderitem;
import com.serpics.stereotype.Hook;

@Hook("discountHook")
public interface DiscountHook {

	public Orderitem applyItemDiscount(Orderitem orderItem);

	public AbstractOrder applyOrderDiscount(AbstractOrder order);
}
