package com.serpics.commerce.facade;

import java.util.List;

import com.serpics.base.data.model.Store;
import com.serpics.commerce.facade.data.CartData;
import com.serpics.commerce.facade.data.CartItemData;
import com.serpics.commerce.facade.data.CartModification;
import com.serpics.commerce.facade.data.PaymentData;
import com.serpics.commerce.facade.data.PaymethodData;
import com.serpics.commerce.facade.data.ShipmodeData;
import com.serpics.membership.data.model.Member;
import com.serpics.membership.facade.data.AddressData;

public interface CartFacade {
	public CartModification cartAdd(String sku);
	public CartModification cartAdd(String sku, int qty);

	public CartData getCurrentCart();

	public CartModification cartItemDelete(Long id);
	
	public CartModification update(CartItemData cartItem);
	
	public CartData addAddress(AddressData shippingAddress, AddressData buildingAddress);
	
	public CartData addBillingAddress(AddressData billingAddress);
	public CartData addShippingAddress(AddressData shippingAddress);

	public List<ShipmodeData> getShipmodeList();
	public CartData addShipmode(String shipmodeName);
	
	public CartData deleteCart();
	public CartData addPaymethod(String paymethodName);
	public List<PaymethodData> getPaymethodList();
	public void mergeCartAtLogin(Member member,Member customer, Store store,String ssid);
	public PaymentData createPayment();
	public void addPaymentInfo(PaymentData paymentData);
}
