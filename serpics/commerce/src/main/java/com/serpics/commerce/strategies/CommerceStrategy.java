package com.serpics.commerce.strategies;

import com.serpics.commerce.data.model.AbstractOrder;
import com.serpics.commerce.data.model.AbstractOrderitem;

public interface CommerceStrategy {
    public void calculateTax(AbstractOrder order);

    public void calculateShipping(AbstractOrderitem orderitem);

    public void calculateShipping(AbstractOrder order);

    public void calculateProductTotal(AbstractOrder order);

    public void calculateOrderTotal(AbstractOrder order);

}
