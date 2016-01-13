package com.serpics.commerce.strategies;

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

        for (final AbstractOrderitem orderItem : order.getItems()) {
            total_netPrice += orderItem.getSkuNetPrice() * orderItem.getQuantity();
            total_cost += orderItem.getSkuCost() != null ?orderItem.getSkuCost() :new Double(0)  * orderItem.getQuantity();
            total_price += orderItem.getSkuPrice() * orderItem.getQuantity();
        }
        order.setTotalProduct(total_netPrice);
        order.setTotalCost(total_cost);
        order.setTotalPrice(total_price);
                  
    }

    @Override
    public void calculateOrderTotal(final AbstractOrder order) {

    	double orderAmount = 0;                              
    	orderAmount = orderAmount + getSafeDouble(order.getTotalProduct()); 
    	orderAmount = orderAmount + getSafeDouble(order.getTotalService()); 
    	orderAmount = orderAmount + getSafeDouble(order.getTotalShipping());
    	orderAmount = orderAmount + getSafeDouble(order.getTotalTax());     

        order.setOrderAmount(orderAmount);

    }
    private double getSafeDouble(Double value){
    	return value!=null?value:0;
    }
}
