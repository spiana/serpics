package com.serpics.commerce.facade;

import java.util.Hashtable;

import javax.annotation.Resource;

import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.serpics.catalog.ProductNotFoundException;
import com.serpics.catalog.data.model.Product;
import com.serpics.catalog.data.repositories.ProductSpecification;
import com.serpics.catalog.services.ProductService;
import com.serpics.commerce.data.model.Cart;
import com.serpics.commerce.facade.data.CartData;
import com.serpics.commerce.facade.data.CartItemData;
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
	@Autowired
	UserFacade usersFacade;
	
	@Autowired
	ProductService productService;
	
	@Autowired
	CartService cartService;
	
	@Autowired
	UserService userService;
	
	@Resource(name="cartConverter")
	AbstractPopulatingConverter<Cart, CartData> cartConverter;
	
	public CartData cartAdd(String productUuid){ 
		 return cartAdd(productUuid, 1);
	}
	public CartData cartAdd(String productUuid, int quantity) {
		Product product = productService.findByUUID(productUuid);
		Cart c = null;
		try {
			c = cartService.cartAdd(product, quantity, true);
		} catch (InventoryNotAvailableException | ProductNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		CartData cartData = cartConverter.convert(c);
		return cartData;
	}
	
	
	@Override
	public CartData getCurrentCart() {
		Cart c = cartService.getSessionCart();
		CartData cart = cartConverter.convert(c);
		return cart;
	}
	
	@Override
	public CartData update(CartData cart) {
		Hashtable<Product, Double> list = new Hashtable<Product, Double>();
		for (CartItemData cartItemData : cart.getOrderItems()) {
			//String uuid = cartItemData.getProduct().getUuid();
			String sku = cartItemData.getSku();
			//Product product =  productService.findByUUID(uuid);
			Product product = productService.findOne(ProductSpecification.findByName(sku));
			double q = cartItemData.getQuantity();
			list.put(product, q);
		}
			try {
				Cart ca = cartService.cartUpdateProduct(list);
				Assert.assertNotNull(ca);
			} catch (InventoryNotAvailableException | ProductNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
		Cart c = cartService.getSessionCart();
		cart =  cartConverter.convert(c);
		return cart;
	}
	
	
	public CartData cartItemDelete(String uuid) {
		CartData cart = null;
		Cart c = cartService.getSessionCart();
		
		return cart;
	}
	@Override
	public CartData addAddress(AddressData shippingAddress,
			AddressData billingAddress) {
		
		User user = userService.getCurrentCustomer();
		usersFacade.addBillingAddress(billingAddress);
		//usersFacade.addDestinationAddress(shippingAddress);
		
		user = userService.getCurrentCustomer();
		BillingAddress billing = user.getBillingAddress();
		//PermanentAddress shipping = user.getPermanentAddresses().iterator().next();
		Cart cart = cartService.getSessionCart();
		cart.setBillingAddress(billing);
		//cart.setShippingAddress(shipping);
		try {
			cartService.prepareCart(cart);
		} catch (InventoryNotAvailableException | ProductNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	
}
