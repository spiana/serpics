package com.serpics.commerce.hooks;

import com.serpics.commerce.persistence.AbstractOrder;
import com.serpics.commerce.persistence.AbstractOrderitem;
import com.serpics.stereotype.Hook;

@Hook("discountHook")
public interface DiscountHook {

    public AbstractOrderitem applyItemDiscount(AbstractOrderitem orderItem);

    public AbstractOrder applyOrderDiscount(AbstractOrder order);
}
