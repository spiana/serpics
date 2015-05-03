package com.serpics.commerce.hooks;

import com.serpics.commerce.data.model.AbstractOrder;
import com.serpics.commerce.data.model.AbstractOrderitem;
import com.serpics.stereotype.Hook;

@Hook("discountHook")
public interface DiscountHook {

    public AbstractOrderitem applyItemDiscount(AbstractOrderitem orderItem);

    public AbstractOrder applyOrderDiscount(AbstractOrder order);
}
