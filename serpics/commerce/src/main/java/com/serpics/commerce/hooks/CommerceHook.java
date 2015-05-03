package com.serpics.commerce.hooks;

import com.serpics.commerce.data.model.AbstractOrder;
import com.serpics.commerce.data.model.AbstractOrderitem;
import com.serpics.stereotype.Hook;

@Hook("commerceHook")
public interface CommerceHook {
    public void calculateTax(AbstractOrder order);

    public void calculateShipping(AbstractOrderitem orderitem);

    public void calculateShiping(AbstractOrder order);

    public void calculateProductTotal(AbstractOrder order);

    public void calculateOrderTotal(AbstractOrder order);

}
