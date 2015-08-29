package com.serpics.commerce.facade;

import com.serpics.commerce.facade.data.CartData;
import com.serpics.commerce.facade.data.CartItemModification;
import com.serpics.membership.facade.data.AddressData;

public interface CartFacade {
	public CartItemModification cartAdd(String sku);
	public CartItemModification cartAdd(String sku, int qty);
	
	public CartData update(CartData cart);
	public CartData getCurrentCart();
	
	
	public CartItemModification cartItemDelete(Long uuid);
	
	public CartData addAddress(AddressData shippingAddress, AddressData buildingAddress);
}
