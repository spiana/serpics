package com.serpics.commerce.facade;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.serpics.base.data.model.Store;
import com.serpics.catalog.ProductNotFoundException;
import com.serpics.catalog.services.ProductService;
import com.serpics.commerce.PaymentException;
import com.serpics.commerce.data.model.Cart;
import com.serpics.commerce.data.model.Cartitem;
import com.serpics.commerce.data.model.Payment;
import com.serpics.commerce.data.model.Paymethod;
import com.serpics.commerce.data.model.Shipmode;
import com.serpics.commerce.facade.data.CartData;
import com.serpics.commerce.facade.data.CartItemData;
import com.serpics.commerce.facade.data.CartModification;
import com.serpics.commerce.facade.data.CartModificationStatus;
import com.serpics.commerce.facade.data.PaymentData;
import com.serpics.commerce.facade.data.PaymethodData;
import com.serpics.commerce.facade.data.ShipmodeData;
import com.serpics.commerce.services.CartService;
import com.serpics.core.facade.AbstractPopulatingConverter;
import com.serpics.membership.UserType;
import com.serpics.membership.data.model.Address;
import com.serpics.membership.data.model.Member;
import com.serpics.membership.data.model.User;
import com.serpics.membership.facade.AddressFacade;
import com.serpics.membership.facade.UserFacade;
import com.serpics.membership.facade.data.AddressData;
import com.serpics.membership.services.UserService;
import com.serpics.stereotype.StoreFacade;
import com.serpics.warehouse.InventoryNotAvailableException;

@StoreFacade("cartFacade")
@Transactional(readOnly = true)
public class CartFacadeImpl implements CartFacade {

	private Logger LOG = LoggerFactory.getLogger(CartFacadeImpl.class);

	@Autowired
	AddressFacade addressFacade;

	@Autowired
	UserFacade usersFacade;

	@Autowired
	ProductService productService;

	@Autowired
	CartService cartService;

	@Resource
	UserService userService;

	@Resource(name = "cartConverter")
	AbstractPopulatingConverter<Cart, CartData> cartConverter;

	@Resource(name = "cartItemConvert")
	AbstractPopulatingConverter<Cartitem, CartItemData> cartItemConverter;

	@Resource(name = "paymethodConverter")
	AbstractPopulatingConverter<Paymethod, PaymethodData> paymethodConverter;

	@Resource(name = "shipmodeConverter")
	AbstractPopulatingConverter<Shipmode, ShipmodeData> shipmodeConverter;

	@Resource(name = "paymentConverter")
	AbstractPopulatingConverter<Payment, PaymentData> paymentConverter;

	@Transactional
	public CartModification cartAdd(String sku) {
		return cartAdd(sku, 1);
	}

	@Override
	@Transactional
	public CartModification cartAdd(String sku, int quantity) {
		Cart c = null;
		try {
			cartService.cartAdd(sku, quantity, true);
			c = cartService.prepareCart();
			return new CartModification(CartModificationStatus.OK, cartConverter.convert(c));
		} catch (InventoryNotAvailableException e) {
			return new CartModification(CartModificationStatus.LOW_STOCK, getCurrentCart(), "not available !");
		} catch (ProductNotFoundException e) {
			return new CartModification(CartModificationStatus.ERROR, getCurrentCart(), e.getMessage());
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
	@Transactional
	public CartModification update(CartItemData cartItem) {
		Cart cart = null;
		try {
			cart = cartService.cartUpdate(convertCartItemData(cartItem));
			return new CartModification(CartModificationStatus.OK, cartConverter.convert(cart));
		} catch (InventoryNotAvailableException e) {
			return new CartModification(CartModificationStatus.LOW_STOCK, getCurrentCart(), "not available !");
		} catch (ProductNotFoundException e) {
			return new CartModification(CartModificationStatus.ERROR, getCurrentCart(), "product not found !");
		}

	}

	protected Cartitem convertCartItemData(CartItemData cartItemData) {
		Cartitem cartItem = new Cartitem();
		cartItem.setId(cartItemData.getId());
		cartItem.setUuid(cartItemData.getUuid());

		cartItem.setQuantity(cartItemData.getQuantity());

		return cartItem;
	}

	@Override
	@Transactional
	public CartModification cartItemDelete(Long id) {
		CartModification resultModification = null;

		try {
			cartService.cartItemDelete(id);
			resultModification = new CartModification(CartModificationStatus.OK, getCurrentCart());
		} catch (InventoryNotAvailableException e) {
			resultModification = new CartModification(CartModificationStatus.LOW_STOCK, getCurrentCart());
		}

		return resultModification;
	}

	@Override
	@Transactional
	public CartData addBillingAddress(AddressData billingAddress) {
		CartData cartdata = null;
		User user = userService.getCurrentCustomer();
		Address address = addressFacade.addressDataToAddress(billingAddress, user);
		cartService.setBillingAddress(address);

		if (user.getBillingAddress() == null && UserType.REGISTERED.equals(user.getUserType())) {
			usersFacade.addBillingAddress(billingAddress);
		}

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
	public CartData addShippingAddress(AddressData shippingAddress) {
		CartData cartdata = null;
		User user = userService.getCurrentCustomer();
		Address address = addressFacade.addressDataToAddress(shippingAddress, user);
		cartService.setDestinationAddress(address);

		if (user.getPermanentAddresses().isEmpty() && UserType.REGISTERED.equals(user.getUserType())) {
			usersFacade.addDestinationAddress(shippingAddress);
		}

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
	public CartData addAddress(AddressData shippingAddress, AddressData billingAddress) {
		addBillingAddress(billingAddress);
		return addShippingAddress(shippingAddress);
	}

	@Override
	@Transactional
	public CartData addShipmode(String shipmodeName) {
		CartData cartdata = null;
		cartService.addShipmode(shipmodeName);
		try {

			Cart cart = cartService.prepareCart();
			cartdata = cartConverter.convert(cart);

		} catch (InventoryNotAvailableException | ProductNotFoundException e) {
			LOG.error("Error to add shipmode into cart", e);
		}

		return cartdata;
	}

	@Override
	public List<PaymethodData> getPaymethodList() {
		List<PaymethodData> paymethodList = new ArrayList<PaymethodData>();
		for (Paymethod paymethod : cartService.getPaymethod()) {
			paymethodList.add(paymethodConverter.convert(paymethod));
		}
		return paymethodList;
	}

	@Override
	@Transactional
	public CartData addPaymethod(String paymethodName) {
		CartData cartdata = null;
		cartService.addPaymethod(paymethodName);
		try {

			Cart cart = cartService.prepareCart();
			cartdata = cartConverter.convert(cart);

		} catch (InventoryNotAvailableException | ProductNotFoundException e) {
			LOG.error("Error to add paymethod into cart", e);
		}

		return cartdata;
	}

	@Override
	@Transactional
	public CartData deleteCart() {
		cartService.cartDelete();
		return getCurrentCart();
	}

	@Override
	public List<ShipmodeData> getShipmodeList() {
		List<ShipmodeData> shipmodeList = new ArrayList<ShipmodeData>();
		for (Shipmode shipmode : cartService.getShipmode()) {
			shipmodeList.add(shipmodeConverter.convert(shipmode));
		}
		return shipmodeList;
	}

	@Override
	@Transactional
	public void mergeCartAtLogin(Member member, Member customer, Store store, String ssid) {
		// TODO Method used to merge cart at login with current session cart and
		// saved cart
		try {
			cartService.mergeCartAtLogin(member, customer, store, ssid);
		} catch (InventoryNotAvailableException e) {
			LOG.error("Error to merge cart at login", e);
		} catch (ProductNotFoundException e) {
			LOG.error("Error to found product for merge cart at login", e);
		}

	}

	@Override
	@Transactional
	public PaymentData createPayment() {

		PaymentData paymentData = null;
		Payment payment = null;

		try {
			payment = cartService.createPayment();
			if (payment != null) {
				paymentData = paymentConverter.convert(payment);
			}

		} catch (PaymentException e) {
			LOG.error("Error to create payment into cart", e);
		}

		return paymentData;
	}

	@Override
	@Transactional
	public void addPaymentInfo(PaymentData paymentData) {

		try {
			cartService.addPaymentInfo(paymentData.getPaymentIdentifier(), paymentData.getPayerID());
		} catch (PaymentException e) {
			LOG.error("Error to add payment info into current cart", e);
		}
	}

}
