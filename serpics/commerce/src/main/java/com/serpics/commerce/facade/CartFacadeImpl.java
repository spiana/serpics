package com.serpics.commerce.facade;

import java.util.Hashtable;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.serpics.catalog.ProductNotFoundException;
import com.serpics.catalog.data.model.Product;
import com.serpics.catalog.data.specification.ProductSpecification;
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
	
	@Transactional
	public CartItemModification cartAdd(String sku, int quantity) {
		Cartitem c = null;
		try {
			c = cartService.cartAdd(sku, quantity, true);
			cartService.prepareCart();
		} catch (InventoryNotAvailableException e){
			return new CartItemModification(CartModificationStatus.ERROR, new CartItemData(), "not avialable !");
		}catch (ProductNotFoundException e) {
			return new CartItemModification(CartModificationStatus.ERROR, new CartItemData(), "product not found !");
		} 
		
		return new CartItemModification(CartModificationStatus.OK , cartItemConverter.convert(c));
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
				Assert.notNull(ca);
			} catch (InventoryNotAvailableException | ProductNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
		Cart c = cartService.getSessionCart();
		cart =  cartConverter.convert(c);
		return cart;
	}
	
	
	@Override
	@Transactional
	public CartItemModification cartItemDelete(Long id) {
		cartService.cartItemDelete(id);
		return new CartItemModification(CartModificationStatus.OK, null);
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
