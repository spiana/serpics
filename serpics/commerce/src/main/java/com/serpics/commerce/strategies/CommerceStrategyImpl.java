package com.serpics.commerce.strategies;

import java.math.BigDecimal;

import com.serpics.commerce.data.model.AbstractOrder;
import com.serpics.commerce.data.model.AbstractOrderitem;
import com.serpics.stereotype.StoreStrategy;

@StoreStrategy(value = "commerceStrategy")
public class CommerceStrategyImpl  implements CommerceStrategy {

    @Override
    public void calculateTax(final AbstractOrder order) {
        // TODO Auto-generated method stub

    }

    @Override
    public void calculateShipping(final AbstractOrderitem orderitem) {

        // TODO Auto-generated method stub

    }

    @Override
    public void calculateShiping(final AbstractOrder order) {
        // TODO Auto-generated method stub

    }

    @Override
    public void calculateProductTotal(final AbstractOrder order) {

        double total_netPrice = 0;
        double total_price = 0;

        double total_cost = 0;

        for (final AbstractOrderitem orderItem : order.getOrderitems()) {
            total_netPrice += orderItem.getSkuNetPrice() * orderItem.getQuantity();
            total_cost += orderItem.getSkuCost() != null ?orderItem.getSkuCost() :new Double(0)  * orderItem.getQuantity();
            total_price += orderItem.getSkuPrice() * orderItem.getQuantity();
        }
        order.setTotalProduct(new BigDecimal(total_netPrice));
    }

    @Override
    public void calculateOrderTotal(final AbstractOrder order) {

        BigDecimal orderAmount = new BigDecimal(0);
        orderAmount = orderAmount.add(order.getTotalProduct());
        orderAmount = orderAmount.add(order.getTotalService());
        orderAmount = orderAmount.add(order.getTotalShipping());
        orderAmount = orderAmount.add(order.getTotalTax());

        order.setOrderAmount(orderAmount);

    }

}
