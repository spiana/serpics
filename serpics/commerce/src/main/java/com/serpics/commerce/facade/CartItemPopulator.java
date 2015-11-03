package com.serpics.commerce.facade;


import com.serpics.commerce.data.model.Cartitem;
import com.serpics.commerce.facade.data.CartItemData;
import com.serpics.core.facade.Populator;

public  class CartItemPopulator extends AbstractOrderItemPopulator implements Populator<Cartitem ,  CartItemData >{


	
	@Override
	public void populate(Cartitem source, CartItemData target) {
		super.populate(source, target);
		
		
	}
	
	
	

}
