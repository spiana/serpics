package com.serpics.commerce.strategies;

import com.serpics.commerce.data.model.AbstractOrder;
import com.serpics.commerce.data.model.AbstractOrderitem;

public interface DiscountStrategy {

    public AbstractOrderitem applyItemDiscount(AbstractOrderitem orderItem);

    public AbstractOrder applyOrderDiscount(AbstractOrder order);
}
