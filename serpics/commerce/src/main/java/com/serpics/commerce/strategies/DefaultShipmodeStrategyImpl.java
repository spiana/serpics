package com.serpics.commerce.strategies;

import javax.annotation.Resource;

import com.serpics.commerce.data.model.AbstractOrder;
import com.serpics.commerce.data.model.AbstractOrderitem;
import com.serpics.commerce.data.model.Shipmodelookup;
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
		if (order.getShippingAddress() != null ){
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
		    	Shipmodelookup shipmodelookup = null;
		    	for (Shipmodelookup lookup : order.getShipmode().getShipmodelookups()){
		    		if (lookup.getZipcode() != null){
		    			if (lookup.getZipcode().equals(order.getShippingAddress().getZipcode())){
			    			shipmodelookup = lookup;
			    			break;
		    			}
		    		} else if (lookup.getDistrict() != null){
		    			if (shipmodelookup != null){
		    				if (shipmodelookup.getDistrict() == null){
				    			if (lookup.getDistrict().equals(order.getShippingAddress().getDistrict())){
					    			shipmodelookup = lookup;
				    			}
		    				}
		    			} else {
			    			if (lookup.getDistrict().equals(order.getShippingAddress().getDistrict())){
				    			shipmodelookup = lookup;
			    			}
		    			}
		    		} else if (lookup.getRegion() != null){
		    			if (shipmodelookup != null){
		    				if (shipmodelookup.getRegion() == null){
				    			if (lookup.getRegion().equals(order.getShippingAddress().getRegion())){
					    			shipmodelookup = lookup;
				    			}
		    				}
		    			} else {
			    			if (lookup.getRegion().equals(order.getShippingAddress().getRegion())){
				    			shipmodelookup = lookup;
			    			}
		    			}
		    		} else if (lookup.getCountry() != null){
		    			if (shipmodelookup != null){
		    				if (shipmodelookup.getCountry() == null){
				    			if (lookup.getCountry().equals(order.getShippingAddress().getCountry())){
					    			shipmodelookup = lookup;
				    			}
		    				}
		    			} else {
			    			if (lookup.getCountry().equals(order.getShippingAddress().getCountry())){
				    			shipmodelookup = lookup;
			    			}
		    			}
		    		} else if (lookup.getStore() != null){
		    			if (shipmodelookup != null){
		    				if (shipmodelookup.getStore() == null){
				    			if (lookup.getStore().equals(order.getStore())){
					    			shipmodelookup = lookup;
				    			}
		    				}
		    			} else {
			    			if (lookup.getStore().equals(order.getStore())){
				    			shipmodelookup = lookup;
			    			}
		    			}
		    		}
		    	}
		    	if (shipmodelookup != null){
			    	for (Shipping shipping : shipmodelookup.getShippings()){
			    		if (shipping.getRangestart() <= totalWeight && (selectedShipping.getRangestart() == null || (shipping.getRangestart() > selectedShipping.getRangestart()))){
			    			selectedShipping = shipping;
			    		}
			    	}
		    	}
		    	selectedValue = selectedShipping.getValue();
	    	}
		}
    	order.setTotalShipping(selectedValue);
    	
    	abstractOrderRepository.saveAndFlush(order);
		
	}

	
}
