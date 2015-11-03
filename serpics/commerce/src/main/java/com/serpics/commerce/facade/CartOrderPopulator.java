package com.serpics.commerce.facade;

import com.serpics.commerce.data.model.Cart;
import com.serpics.commerce.facade.data.CartData;
import com.serpics.core.facade.Populator;

public class CartOrderPopulator extends AbstractOrderPopulator implements Populator<Cart, CartData> {

	@Override
	public void populate(Cart source, CartData target) {
		super.populate(source, target);
		
	}

}
