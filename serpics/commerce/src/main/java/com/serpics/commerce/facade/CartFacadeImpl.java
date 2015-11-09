package com.serpics.commerce.facade;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.serpics.catalog.ProductNotFoundException;
import com.serpics.catalog.data.model.Product;
import com.serpics.catalog.services.ProductService;
import com.serpics.commerce.data.model.Cart;
import com.serpics.commerce.data.model.Cartitem;
import com.serpics.commerce.facade.data.CartData;
import com.serpics.commerce.facade.data.CartItemData;
import com.serpics.commerce.facade.data.CartItemModification;
import com.serpics.commerce.facade.data.CartModificationStatus;
import com.serpics.commerce.services.CartService;
import com.serpics.core.facade.AbstractPopulatingConverter;
import com.serpics.membership.data.model.BillingAddress;
import com.serpics.membership.data.model.User;
import com.serpics.membership.facade.UserFacade;
import com.serpics.membership.facade.data.AddressData;
import com.serpics.membership.services.UserService;
import com.serpics.stereotype.StoreFacade;
import com.serpics.warehouse.InventoryNotAvailableException;

@StoreFacade("cartFacade")
@Transactional(readOnly=true)
public class CartFacadeImpl implements CartFacade {
	
	private Logger LOG = LoggerFactory.getLogger(CartFacadeImpl.class);
			
	@Autowired
	UserFacade usersFacade;
	
	@Autowired
	ProductService productService;
	
	@Autowired
	CartService cartService;
	
	@Resource
	UserService userService;
	
	@Resource(name="cartConverter")
	AbstractPopulatingConverter<Cart, CartData> cartConverter;
	
	@Resource(name="cartItemConvert")
	AbstractPopulatingConverter<Cartitem , CartItemData> cartItemConverter;
	
	public CartItemModification cartAdd(String sku){ 
		 return cartAdd(sku, 1);
	}
	
	@Override
	@Transactional
	public CartItemModification cartAdd(String sku, int quantity) {
		Cart c = null;
		try {
			cartService.cartAdd(sku, quantity, true);
			c = cartService.prepareCart();
			return new CartItemModification(CartModificationStatus.OK , cartConverter.convert(c));
		} catch (InventoryNotAvailableException e){
			return new CartItemModification(CartModificationStatus.LOW_STOCK, new CartData(), "not available !");
		}catch (ProductNotFoundException e) {
			return new CartItemModification(CartModificationStatus.ERROR, new CartData(), "product not found !");
		} 
		
		
	}
	
	
	@Override
	@Transactional
	public CartData getCurrentCart() {
		Cart c = cartService.getSessionCart();
		CartData cart = cartConverter.convert(c);
		return cart;
	}
	
	@Override
	public CartItemModification update(CartItemData cartItem){	
		Cart cart = null;
		try {
			cart = cartService.cartUpdate(convertCartItemData(cartItem));
			return new CartItemModification(CartModificationStatus.OK , cartConverter.convert(cart));
		} catch (InventoryNotAvailableException e){
			return new CartItemModification(CartModificationStatus.LOW_STOCK, new CartData(), "not available !");
		}catch (ProductNotFoundException e) {
			return new CartItemModification(CartModificationStatus.ERROR, new CartData(), "product not found !");
		} 

	}
	
	protected Cartitem convertCartItemData(CartItemData cartItemData){
		Cartitem cartItem = new Cartitem();
		cartItem.setId(cartItemData.getId());
		cartItem.setUuid(cartItemData.getUuid());
		cartItem.setProduct(new Product());
		cartItem.getProduct().setCode(cartItemData.getProduct().getCode());
		
		cartItem.setQuantity(cartItemData.getQuantity());
		
		return cartItem;
	}
	
	@Override
	@Transactional
	public CartItemModification cartItemDelete(Long id) {
		CartItemModification resultModification = null;
		
		try{
			cartService.cartItemDelete(id);
			resultModification = new CartItemModification(CartModificationStatus.OK, null);
		}catch(InventoryNotAvailableException e){
			resultModification = new CartItemModification(CartModificationStatus.LOW_STOCK, null);
		}
		
		return resultModification;
	}
	
	@Override
	@Transactional
	public CartData addBillingAddress(AddressData billingAddress){
		CartData cartdata = null;
		User user = userService.getCurrentCustomer();
		usersFacade.addBillingAddress(billingAddress);
	
		user = userService.getCurrentCustomer();
		BillingAddress billing = user.getBillingAddress();
		cartService.setBillingAddress(billing);
		
		// cartService.setDestinationAddress(shippingAddress);
		try {
			
			Cart cart = cartService.prepareCart();
			cartdata = cartConverter.convert(cart);
			
		} catch (InventoryNotAvailableException | ProductNotFoundException e) {
			LOG.error("Error to add Biling Address into cart", e);
		}
		
		return cartdata;
	}
	
	@Override
	@Transactional
	public CartData addShippingAddress(AddressData shippingAddress){
		CartData cartdata = null;
		usersFacade.addDestinationAddress(shippingAddress);
		 
	try {
			
			Cart cart = cartService.prepareCart();
			cartdata = cartConverter.convert(cart);
			
		} catch (InventoryNotAvailableException | ProductNotFoundException e) {
			LOG.error("Error to add Biling Address into cart", e);
		}
		
		return cartdata;
	}
	
	@Override
	@Transactional
	public CartData addAddress(AddressData shippingAddress,
			AddressData billingAddress) {
		addBillingAddress(billingAddress);
		return addShippingAddress(shippingAddress);
	}
	
	
	
}
