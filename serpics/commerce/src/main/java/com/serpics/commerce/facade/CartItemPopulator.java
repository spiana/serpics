package com.serpics.commerce.facade;


import com.serpics.commerce.data.model.Cartitem;
import com.serpics.commerce.facade.data.CartItemData;
import com.serpics.core.facade.Populator;

public  class CartItemPopulator  implements Populator<Cartitem ,  CartItemData >{


	
	@Override
	public void populate(Cartitem source, CartItemData target) {
		//target.setBillingAddress(source.getBillingAddress());
		target.setId(source.getId());
		target.setUuid(source.getUuid());
		target.setCreated(source.getCreated());
		target.setUpdated(source.getUpdated());
		
		target.setSku(source.getSku());
		
		
	}
	
	
	

}
