package com.serpics.commerce.services;

import java.math.BigDecimal;
import java.util.Iterator;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.serpics.catalog.ProductNotFoundException;
import com.serpics.catalog.hooks.InventoryHook;
import com.serpics.catalog.hooks.PriceHook;
import com.serpics.catalog.hooks.ProductHook;
import com.serpics.catalog.persistence.AbstractProduct;
import com.serpics.catalog.persistence.Product;
import com.serpics.commerce.hooks.DiscountHook;
import com.serpics.commerce.persistence.AbstractOrder;
import com.serpics.commerce.persistence.Cart;
import com.serpics.commerce.persistence.Orderitem;
import com.serpics.commerce.repositories.CartRepository;
import com.serpics.commerce.repositories.OrderItemRepository;
import com.serpics.core.security.UserPrincipal;
import com.serpics.core.service.AbstractService;
import com.serpics.warehouse.InventoryNotAvailableException;

@Service("cartService")
public class CartServiceImpl extends AbstractService implements CartService {

	@Resource
	CartRepository cartRepository;

	@Resource
	OrderItemRepository orderitemrepository;

	@Override
	@Transactional
	public Cart createSessionCart() {
		Cart cart = cartRepository.findByCookie(getCurrentContext().getUserCookie());
		if (cart == null) {
			cart = new Cart(getCurrentContext().getUserPrincipal().getUserId(), getCurrentContext().getStoreId(),
					getCurrentContext().getUserCookie());
			cart.setCurrency("EUR");
			cartRepository.saveAndFlush(cart);
		}
		return cart;
	}

	@Override
	public Cart findSessionCart() {
		return cartRepository.findByCookie(getCurrentContext().getUserCookie());
	}

	@Override
	@Transactional
	public void cartUpdate(Orderitem orderitem, Cart cart) throws InventoryNotAvailableException,
			ProductNotFoundException {
		DiscountHook discounthook = (DiscountHook) getHook("discountHook");
		PriceHook pricehook = (PriceHook) getHook("priceHook");
		ProductHook producthook = (ProductHook) getHook("productHook");

		Product product = producthook.resolveSKU(orderitem.getSku());

		orderitem.setSkuCost(pricehook.resolveProductCost(product, cart.getCurrency()));
		orderitem.setSkuPrice(pricehook.resolveProductPrice(product, cart.getCurrency()));
		discounthook.applyItemDiscount(orderitem);

		cart.setOrderAmount(cart.getTotalProduct().add(
				new BigDecimal(orderitem.getQuantity() * orderitem.getSkuNetPrice())));

		orderitem.setOrder(cart);
		cart.getOrderitems().add(orderitem);

		discounthook.applyOrderDiscount(cart);

		cartRepository.saveAndFlush(cart);

	}

	@Override
	@Transactional
	public void cartAdd(AbstractProduct product, double quantity, Cart cart, boolean merge)
			throws InventoryNotAvailableException, ProductNotFoundException {
		DiscountHook discounthook = (DiscountHook) getHook("discountHook");
		PriceHook pricehook = (PriceHook) getHook("priceHook");

		Orderitem orderitem = new Orderitem();

		UserPrincipal user = getCurrentContext().getUserPrincipal();
		orderitem.setStatus(AbstractOrder.PENDING);
		orderitem.setUserId(user.getUserId());
		orderitem.setCustomerId(getCurrentContext().getCustomerId());
		orderitem.setStoreId(getCurrentContext().getStoreId());
		orderitem.setCurrency(cart.getCurrency());
		orderitem.setSku(product.getSku());
		orderitem.setQuantity(quantity);

		if (merge)
			orderitem = mergeCart(cart, orderitem);

		orderitem.setSkuCost(pricehook.resolveProductCost(product, cart.getCurrency()));
		orderitem.setSkuPrice(pricehook.resolveProductPrice(product, cart.getCurrency()));

		discounthook.applyItemDiscount(orderitem);
		orderitem.setOrder(cart);
		cart.getOrderitems().add(orderitem);
		cartRepository.saveAndFlush(cart);
	}

	@Override
	@Transactional
	public void cartUpdate(Orderitem orderitem) throws InventoryNotAvailableException, ProductNotFoundException {
		Cart cart = createSessionCart();
		cartUpdate(orderitem, cart);
	}

	@Override
	@Transactional
	public void cartAdd(AbstractProduct product, double quantity, boolean merge) throws InventoryNotAvailableException,
			ProductNotFoundException {
		Cart cart = createSessionCart();
		cartAdd(product, quantity, cart, merge);
	}

	private Orderitem mergeCart(Cart cart, Orderitem orderitem) {

		Iterator<Orderitem> items = cart.getOrderitems().iterator();

		while (items.hasNext()) {
			Orderitem oi = items.next();
			if (oi.getSku().equals(orderitem.getSku())) {
				orderitem.setQuantity(orderitem.getQuantity() + oi.getQuantity());
				oi.setOrder(null);
				items.remove();
			}
		}

		return orderitem;
	}

	@Override
	@Transactional
	public void cartAdd(String sku, double quantity, Cart cart, boolean merge) throws InventoryNotAvailableException,
			ProductNotFoundException {
		ProductHook productHook = (ProductHook) getHook("productHook");
		Product product = productHook.resolveSKU(sku);
		this.cartAdd(product, quantity, merge);
	}

	@Override
	@Transactional
	public void cartAdd(String sku, double quantity, boolean merge) throws InventoryNotAvailableException,
			ProductNotFoundException {
		Cart cart = createSessionCart();
		cartAdd(sku, quantity, cart, merge);

	}

	@Override
	public void prepareCart() throws InventoryNotAvailableException, ProductNotFoundException {
		Cart cart = findSessionCart();
		Assert.notNull(cart);

		prepareCart(cart);
	}

	@Override
	@Transactional
	public void prepareCart(Cart cart) throws InventoryNotAvailableException, ProductNotFoundException {
		DiscountHook discounthook = (DiscountHook) getHook("discountHook");
		PriceHook pricehook = (PriceHook) getHook("priceHook");
		ProductHook producthook = (ProductHook) getHook("productHook");
		InventoryHook inventoryHook = (InventoryHook) getHook("inventoryHook");

		cart.setOrderAmount(new BigDecimal(0));
		cart.setTotalProduct(new BigDecimal(0));
		cart.setTotalShipping(new BigDecimal(0));
		cart.setTotalTax(new BigDecimal(0));
		for (Orderitem orderitem : cart.getOrderitems()) {
			Product product = producthook.resolveSKU(orderitem.getSku());

			inventoryHook.checkInventory(product);

			orderitem.setSkuCost(pricehook.resolveProductCost(product, cart.getCurrency()));
			orderitem.setSkuPrice(pricehook.resolveProductPrice(product, cart.getCurrency()));
			discounthook.applyItemDiscount(orderitem);

			cart.setOrderAmount(cart.getTotalProduct().add(
					new BigDecimal(orderitem.getQuantity() * orderitem.getSkuNetPrice())));
		}
		discounthook.applyOrderDiscount(cart);
		cartRepository.saveAndFlush(cart);
	}

}
