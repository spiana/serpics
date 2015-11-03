package com.serpics.commerce.facade;


import com.serpics.commerce.data.model.AbstractOrderitem;
import com.serpics.commerce.facade.data.AbstractOrderItemData;

public class AbstractOrderItemPopulator{

	
	
	public void populate(AbstractOrderitem source, AbstractOrderItemData target) {
		//target.setBillingAddress(source.getBillingAddress());
		target.setId(source.getId());
		target.setUuid(source.getUuid());
		target.setCreated(source.getCreated());
		target.setUpdated(source.getUpdated());
		
		target.setQuantity(source.getQuantity());
		
		target.setProduct(source.getProduct());
		target.setSku(source.getSku());
		target.setSkuCost(source.getSkuCost());
		target.setSkuDescription(source.getSkuDescription());
		target.setSkuNetPrice(source.getSkuNetPrice());
		target.setSkuPrice(source.getSkuPrice());
		
		target.setDiscountAmount(source.getDiscountAmount());
		target.setDiscountPerc(source.getDiscountPerc());
		
		if(source.getShippingAddressId() != null) target.setShippingAddressId(source.getShippingAddressId());
		target.setShippingCost(source.getShippingCost());
		
	}
	
	
	

}
