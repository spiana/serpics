package com.serpics.commerce.strategies;

import com.serpics.commerce.data.model.AbstractOrder;
import com.serpics.commerce.data.model.AbstractOrderitem;
import com.serpics.stereotype.StoreStrategy;

@StoreStrategy(value = "discountStrategy")
public class DiscountStrategyImpl implements DiscountStrategy {

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
            order.setDiscountAmount(order.getDiscountAmount() +
                    order.getTotalProduct() *  (order.getDiscountPerc() / 100));

        return order;
    }

}
