package com.serpics.commerce.services;

import java.math.BigDecimal;
import java.util.Iterator;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.serpics.catalog.ProductNotFoundException;
import com.serpics.catalog.hooks.InventoryHook;
import com.serpics.catalog.hooks.PriceHook;
import com.serpics.catalog.hooks.ProductHook;
import com.serpics.catalog.persistence.AbstractProduct;
import com.serpics.catalog.persistence.Product;
import com.serpics.commerce.hooks.CommerceHook;
import com.serpics.commerce.hooks.DiscountHook;
import com.serpics.commerce.persistence.AbstractOrder;
import com.serpics.commerce.persistence.Cart;
import com.serpics.commerce.persistence.Orderitem;
import com.serpics.commerce.repositories.CartRepository;
import com.serpics.commerce.repositories.OrderItemRepository;
import com.serpics.core.security.UserPrincipal;
import com.serpics.core.service.AbstractService;
import com.serpics.membership.persistence.Store;
import com.serpics.warehouse.InventoryNotAvailableException;

@Service("cartService")
@Scope("store")
public class CartServiceImpl extends AbstractService implements CartService {

	@Resource
	CartRepository cartRepository;

	@Resource
	OrderItemRepository orderitemrepository;

	@Resource
	DiscountHook discountHook;
	@Resource
	PriceHook priceHook;

	@Resource
	ProductHook productHook;
	@Resource
	CommerceHook commerceHook;

	@Resource
	InventoryHook inventoryHook;

	@Override
	@Transactional
	public Cart createSessionCart() {
		Cart cart = cartRepository.findByCookie(getCurrentContext().getUserCookie());
		if (cart == null) {

			cart = new Cart(getCurrentContext().getUserPrincipal().getUserId(), getCurrentContext().getStoreId(),
					getCurrentContext().getUserCookie());

			cart.setCurrency(((Store) getCurrentContext().getStoreRealm()).getCurrency());
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
	public Cart cartUpdate(Orderitem orderitem, Cart cart) throws InventoryNotAvailableException,
			ProductNotFoundException {
		Product product = productHook.resolveSKU(orderitem.getSku());

		orderitem.setSkuCost(priceHook.resolveProductCost(product, cart.getCurrency()));
		orderitem.setSkuPrice(priceHook.resolveProductPrice(product, cart.getCurrency()));
		discountHook.applyItemDiscount(orderitem);

		orderitem.setOrder(cart);
		cart.getOrderitems().add(orderitem);

		commerceHook.calculateShipping(orderitem);

		return cartRepository.saveAndFlush(cart);

	}

	@Override
	@Transactional
	public Cart cartAdd(AbstractProduct product, double quantity, Cart cart, boolean merge)
			throws InventoryNotAvailableException, ProductNotFoundException {

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

		orderitem.setSkuCost(priceHook.resolveProductCost(product, cart.getCurrency()));
		orderitem.setSkuPrice(priceHook.resolveProductPrice(product, cart.getCurrency()));

		discountHook.applyItemDiscount(orderitem);
		orderitem.setOrder(cart);
		cart.getOrderitems().add(orderitem);

		commerceHook.calculateShipping(orderitem);

		return cartRepository.saveAndFlush(cart);
	}

	@Override
	@Transactional
	public Cart cartUpdate(Orderitem orderitem) throws InventoryNotAvailableException, ProductNotFoundException {
		Cart cart = createSessionCart();
		return cartUpdate(orderitem, cart);
	}

	@Override
	@Transactional
	public Cart cartAdd(AbstractProduct product, double quantity, boolean merge) throws InventoryNotAvailableException,
			ProductNotFoundException {
		Cart cart = createSessionCart();
		return cartAdd(product, quantity, cart, merge);
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
	public Cart cartAdd(String sku, double quantity, Cart cart, boolean merge) throws InventoryNotAvailableException,
			ProductNotFoundException {
		Product product = productHook.resolveSKU(sku);
		return cartAdd(product, quantity, merge);
	}

	@Override
	@Transactional
	public Cart cartAdd(String sku, double quantity, boolean merge) throws InventoryNotAvailableException,
			ProductNotFoundException {
		Cart cart = createSessionCart();
		return cartAdd(sku, quantity, cart, merge);

	}

	@Override
	public Cart prepareCart() throws InventoryNotAvailableException, ProductNotFoundException {
		Cart cart = findSessionCart();
		Assert.notNull(cart);

		return prepareCart(cart);
	}

	@Override
	@Transactional
	public Cart prepareCart(Cart cart) throws InventoryNotAvailableException, ProductNotFoundException {
		return prepareCart(cart, false);
	}

	@Override
	@Transactional
	public Cart prepareCart(Cart cart, boolean updateInventory) throws InventoryNotAvailableException,
			ProductNotFoundException {

		cart.setOrderAmount(new BigDecimal(0));
		cart.setTotalProduct(new BigDecimal(0));
		cart.setTotalShipping(new BigDecimal(0));
		cart.setTotalTax(new BigDecimal(0));
		for (Orderitem orderitem : cart.getOrderitems()) {
			Product product = productHook.resolveSKU(orderitem.getSku());
			if (updateInventory)
				inventoryHook.updateInventory(product);
			else
				inventoryHook.checkInventory(product);
			orderitem.setSkuCost(priceHook.resolveProductCost(product, cart.getCurrency()));
			orderitem.setSkuPrice(priceHook.resolveProductPrice(product, cart.getCurrency()));

			discountHook.applyItemDiscount(orderitem);
			commerceHook.calculateShipping(orderitem);
		}

		commerceHook.calculateProductTotal(cart);

		discountHook.applyOrderDiscount(cart);

		commerceHook.calculateShiping(cart);
		commerceHook.calculateTax(cart);
		commerceHook.calculateOrderTotale(cart);

		return cartRepository.saveAndFlush(cart);
	}

	@Override
	@Transactional
	public void cartDelete() {
		Cart cart = findSessionCart();
		cartDelete(cart);

	}

	@Override
	@Transactional
	public void cartDelete(Cart cart) {
		cartRepository.delete(cart);

	}
}
