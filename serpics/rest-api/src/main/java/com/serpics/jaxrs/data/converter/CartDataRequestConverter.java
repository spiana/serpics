package com.serpics.jaxrs.data.converter;


import com.serpics.commerce.facade.data.CartData;
import com.serpics.core.facade.Populator;
import com.serpics.jaxrs.data.CartDataRequest;

public class CartDataRequestConverter implements Populator<CartDataRequest, CartData>{

	@Override
	public void populate(CartDataRequest source, CartData target) {
		target.setTotalProduct(source.getTotalProduct());
		
	}
}
