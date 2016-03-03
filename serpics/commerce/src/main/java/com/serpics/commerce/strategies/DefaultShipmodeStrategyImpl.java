package com.serpics.commerce.strategies;

import javax.annotation.Resource;

import com.serpics.commerce.data.model.AbstractOrder;
import com.serpics.commerce.data.model.AbstractOrderitem;
import com.serpics.commerce.data.model.Shipping;
import com.serpics.commerce.data.repositories.AbstractOrderRepository;
import com.serpics.stereotype.StoreStrategy;

@StoreStrategy("defaultShipmodeStrategy")
public class DefaultShipmodeStrategyImpl implements ShipmodeStrategy{

	@Resource
	AbstractOrderRepository abstractOrderRepository;
	
	@Override
	public void calculateShippingCost(AbstractOrder order) {
    	double totalWeight = (double) 0;
    	double selectedValue = (double) 0;
    	for (AbstractOrderitem orderItem : order.getItems()){
    		double productWeight = (double) 0;
    		if (orderItem.getProduct().getWeight() != null){
    			productWeight = orderItem.getProduct().getWeight();
    		}    		
    		totalWeight = productWeight * orderItem.getQuantity();
    	}
    	if (order.getShipmode() != null){
	    	Shipping selectedShipping = new Shipping();
	    	selectedShipping.setValue((double) 0);
	    	for (Shipping shipping : order.getShipmode().getShippings()){
	    		if (shipping.getRangestart() <= totalWeight && (selectedShipping.getRangestart() == null || (shipping.getRangestart() > selectedShipping.getRangestart()))){
	    			selectedShipping = shipping;
	    		}
	    	}
	    	selectedValue = selectedShipping.getValue();
    	}
    	order.setTotalShipping(selectedValue);
    	
    	abstractOrderRepository.saveAndFlush(order);
		
	}

	
}
