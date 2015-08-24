package com.serpics.commerce.facade;

import com.serpics.commerce.facade.data.CartData;
import com.serpics.membership.facade.data.AddressData;

public interface CartFacade {
	public CartData cartAdd(String proudctUuid);
	public CartData cartAdd(String proudctUuid, int qty);
	
	public CartData update(CartData cart);
	public CartData getCurrentCart();
	
	
	public CartData cartItemDelete(String uuid);
	
	public CartData addAddress(AddressData shippingAddress, AddressData buildingAddress);
}
