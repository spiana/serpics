package com.serpics.commerce.strategies;

import javax.annotation.Resource;

import org.springframework.transaction.annotation.Transactional;

import com.serpics.base.data.model.TaxCategory;
import com.serpics.commerce.ShipmodeException;
import com.serpics.commerce.data.model.AbstractOrder;
import com.serpics.commerce.data.model.AbstractOrderitem;
import com.serpics.commerce.services.ShipmodeService;
import com.serpics.core.utils.CurrencyUtils;
import com.serpics.stereotype.StoreStrategy;

@StoreStrategy(value = "commerceStrategy")
public class CommerceStrategyImpl  implements CommerceStrategy {

	@Resource
	ShipmodeService shipmodeService;
	
    @Override
    @Transactional
    public void calculateTax(final AbstractOrder order) {
    	TaxCategory defaultTaxCategory = order.getStore().getTaxcategory();
    	Double totalTax = 0D;
    	if (defaultTaxCategory != null ){
	    	if (order.getItems() != null){
	    		for (AbstractOrderitem item : order.getItems()) {
					TaxCategory tx = item.getTaxcategory();
					Double rate = 0D;
					
					if (tx == null)
						tx = defaultTaxCategory;
					
					if (tx != null){
						rate =tx.getRate() != null ? tx.getRate() :0D;
					}
					totalTax += CurrencyUtils.round(item.getSkuNetPrice()*item.getQuantity()*rate/100);
	    		}
	    		
	    	}
    	}
    	order.setTotalTax(totalTax);
    }

    @Override
    public void calculateShipping(final AbstractOrderitem orderitem) {

        // TODO Auto-generated method stub

    }

    @Override
    public void calculateShipping(final AbstractOrder order) throws ShipmodeException {
       
    	shipmodeService.calculateShippingCost(order);

    }

    @Override
    public void calculateProductTotal(final AbstractOrder order) {

        double total_netPrice = 0;
        double total_price = 0;

        double total_cost = 0;

        for (final AbstractOrderitem orderItem : order.getItems()) {
            total_netPrice += CurrencyUtils.round(orderItem.getSkuNetPrice() * orderItem.getQuantity());
            total_cost += orderItem.getSkuCost() != null ? CurrencyUtils.round(orderItem.getSkuCost() * orderItem.getQuantity()) :CurrencyUtils.round(0.0D);
            total_price += orderItem.getSkuPrice() != null ? CurrencyUtils.round(orderItem.getSkuPrice() * orderItem.getQuantity()):CurrencyUtils.round(0.0D);
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
