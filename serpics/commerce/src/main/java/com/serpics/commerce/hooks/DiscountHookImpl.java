package com.serpics.commerce.hooks;

import java.math.BigDecimal;

import com.serpics.commerce.persistence.AbstractOrder;
import com.serpics.commerce.persistence.AbstractOrderitem;
import com.serpics.core.hook.AbstractHook;
import com.serpics.stereotype.StoreHook;

@StoreHook(value = "discountHook")
public class DiscountHookImpl extends AbstractHook implements DiscountHook {

    @Override
    public AbstractOrderitem applyItemDiscount(final AbstractOrderitem orderitem) {

        orderitem.setSkuNetPrice(orderitem.getSkuPrice().doubleValue() - orderitem.getDiscountAmount().doubleValue());

        if (orderitem.getDiscountPerc() > 0)
            orderitem.setSkuNetPrice(orderitem.getSkuNetPrice().doubleValue()
                    - orderitem.getSkuNetPrice().doubleValue() * (orderitem.getDiscountPerc() / 100));
        if (orderitem.getDiscountPerc1() > 0)
            orderitem.setSkuNetPrice(orderitem.getSkuNetPrice().doubleValue()
                    - orderitem.getSkuNetPrice().doubleValue() * (orderitem.getDiscountPerc1() / 100));
        if (orderitem.getDiscountPerc2() > 0)
            orderitem.setSkuNetPrice(orderitem.getSkuNetPrice().doubleValue()
                    - orderitem.getSkuNetPrice().doubleValue() * (orderitem.getDiscountPerc2() / 100));

        return orderitem;
    }

    @Override
    public AbstractOrder applyOrderDiscount(final AbstractOrder order) {
        if (order.getDiscountPerc() > 0)
            order.setDiscountAmount(order.getDiscountAmount().add(
                    order.getTotalProduct().multiply(new BigDecimal(order.getDiscountPerc() / 100))));

        return order;
    }

}
