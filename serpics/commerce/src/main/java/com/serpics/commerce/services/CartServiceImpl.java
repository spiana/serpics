package com.serpics.commerce.services;

import java.util.Iterator;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.serpics.base.data.model.Currency;
import com.serpics.catalog.ProductNotFoundException;
import com.serpics.catalog.data.model.AbstractProduct;
import com.serpics.catalog.data.model.Product;
import com.serpics.commerce.data.model.Cart;
import com.serpics.commerce.data.model.Cartitem;
import com.serpics.commerce.data.model.Shipmode;
import com.serpics.commerce.data.repositories.CartItemRepository;
import com.serpics.commerce.data.repositories.CartRepository;
import com.serpics.commerce.data.repositories.OrderItemRepository;
import com.serpics.commerce.session.CommerceSessionContext;
import com.serpics.commerce.strategies.CommerceStrategy;
import com.serpics.commerce.strategies.DiscountStrategy;
import com.serpics.commerce.strategies.PriceStrategy;
import com.serpics.commerce.strategies.ProductStrategy;
import com.serpics.core.service.AbstractService;
import com.serpics.membership.data.model.Address;
import com.serpics.membership.data.model.BillingAddress;
import com.serpics.membership.data.model.Member;
import com.serpics.membership.data.model.PermanentAddress;
import com.serpics.membership.data.model.Store;
import com.serpics.membership.data.model.User;
import com.serpics.membership.data.repositories.AddressRepository;
import com.serpics.warehouse.InventoryNotAvailableException;
import com.serpics.warehouse.data.model.InventoryStatusEnum;
import com.serpics.warehouse.strategies.InventoryStrategy;

@Service("cartService")
@Scope("store")
@Transactional(readOnly = true)
public class CartServiceImpl extends AbstractService<CommerceSessionContext> implements CartService {

	Logger LOG = LoggerFactory.getLogger(CartServiceImpl.class);

	@Resource
	CartRepository cartRepository;

	@Resource
	CartItemRepository cartItemRepository;

	@Resource
	OrderItemRepository orderitemrepository;

	@Resource
	DiscountStrategy discountStrategy;

	@Resource
	PriceStrategy priceStrategy;

	@Resource
	ProductStrategy productStrategy;
	@Resource
	CommerceStrategy commerceStrategy;

	@Resource
	InventoryStrategy inventoryStrategy;

	@Resource
	AddressRepository addressRepository;

	@Override
	@Transactional
	public Cart createSessionCart() {
		Cart cart = cartRepository.findByCookie(getCurrentContext().getUserCookie());
		if (cart == null) {

			cart = new Cart((User) getCurrentContext().getUserPrincipal(), (Store) getCurrentContext().getStoreRealm(),
					getCurrentContext().getUserCookie());

			cart.setCurrency((Currency) getCurrentContext().getCurrency());
			cart.setCustomer((Member) getCurrentContext().getCustomer());
			cartRepository.save(cart);
		}
		putCartinSession(cart);
		return cart;
	}

	private void putCartinSession(Cart cart) {
		getCurrentContext().setAttribute(SESSION_CART, cart);
	}

	private void removeCartFromSession() {
		getCurrentContext().setAttribute(SESSION_CART, null);
	}

	@Override
	public Cart getSessionCart() {
		Cart currentCart = (Cart) getCurrentContext().getAttribute(SESSION_CART);
		if (currentCart == null) {
			LOG.warn("NOT found cart in commerce session for user {}",
					((User) getCurrentContext().getUserPrincipal()).getUuid());
			currentCart = createSessionCart();
		} else {
			currentCart = cartRepository.findOne(currentCart.getId());
			LOG.info("found cart in commerce session for user {}", currentCart.getUser().getUuid());

		}
		return currentCart;
	}

	@Override
	@Transactional
	public Cart cartUpdate(final Cartitem orderitem, final Cart cart)
			throws InventoryNotAvailableException, ProductNotFoundException {
		Assert.notNull(orderitem, "Item to update must not to be null!");
		Assert.notNull(cart, "Cart to update must not to be null!");

		if (orderitem.getId() != null) {
			// OrderItem Exist
			if (orderitem.getQuantity() == 0) {
				// I must to remove orderItem
				cartItemDelete(orderitem.getId());

			} else {

				Cartitem item = cartItemRepository.findOne(orderitem.getId());
				item.setQuantity(orderitem.getQuantity());
				cartItemRepository.saveAndFlush(item);
				prepareCart(cart, true);
			}
		}

		return getSessionCart();
	}

	@Override
	@Transactional
	public Cart cartAdd(final AbstractProduct product, final double quantity, Cart cart, final boolean merge)
			throws InventoryNotAvailableException, ProductNotFoundException {

		Cartitem cartItem = new Cartitem();
		cartItem.setSku(product.getCode());
		cartItem.setQuantity(quantity);
		cartItem.setProduct(product);

		InventoryStatusEnum status = (InventoryStatusEnum) inventoryStrategy.checkInventory(product, quantity);
		if (status == InventoryStatusEnum.OutOfStock)
			throw new InventoryNotAvailableException(product.getCode(), quantity);

		if (merge)
			cartItem = mergeCart(cart, cartItem);

		cartItem.setSkuCost(priceStrategy.resolveProductCost(product, cart.getCurrency()));
		cartItem.setSkuPrice(priceStrategy.resolveProductPrice(product, cart.getCurrency()));

		discountStrategy.applyItemDiscount(cartItem);
		cartItem.setOrder(cart);

		// cartItem.setProduct(product);
		cart.getCartitems().add(cartItem);

		commerceStrategy.calculateShipping(cartItem);

		cartRepository.saveAndFlush(cart);
		cartRepository.refresh(cart);

		return cart;
	}

	@Override
	@Transactional
	public Cart cartUpdate(final Cartitem cartItem) throws InventoryNotAvailableException, ProductNotFoundException {
		final Cart cart = createSessionCart();
		return cartUpdate(cartItem, cart);
	}

	@Override
	@Transactional
	public Cart cartAdd(final AbstractProduct product, final double quantity, final boolean merge)
			throws InventoryNotAvailableException, ProductNotFoundException {
		final Cart cart = createSessionCart();
		return cartAdd(product, quantity, cart, merge);
	}

	private Cartitem mergeCart(final Cart cart, final Cartitem cartItem) {

		final Iterator<Cartitem> items = cart.getCartitems().iterator();

		while (items.hasNext()) {
			final Cartitem oi = items.next();
			if (oi.getSku().equals(cartItem.getSku())) {
				cartItem.setQuantity(cartItem.getQuantity() + oi.getQuantity());
				// oi.setOrder(null);
				items.remove();
			}
		}

		return cartItem;
	}

	@Override
	@Transactional
	public Cart cartAdd(final String sku, final double quantity, final Cart cart, final boolean merge)
			throws InventoryNotAvailableException, ProductNotFoundException {
		final Product product = productStrategy.resolveSKU(sku);
		return cartAdd(product, quantity, merge);
	}

	@Override
	@Transactional
	public Cart cartAdd(final String sku, final double quantity, final boolean merge)
			throws InventoryNotAvailableException, ProductNotFoundException {
		final Cart cart = createSessionCart();
		return cartAdd(sku, quantity, cart, merge);

	}

	@Override
	public Cart prepareCart() throws InventoryNotAvailableException, ProductNotFoundException {
		final Cart cart = getSessionCart();
		Assert.notNull(cart);
		cartRepository.refresh(cart);

		return prepareCart(cart);
	}

	@Override
	@Transactional
	public Cart prepareCart(final Cart cart) throws InventoryNotAvailableException, ProductNotFoundException {
		return prepareCart(cart, false);
	}

	@Override
	@Transactional
	public Cart prepareCart(Cart cart, final boolean updateInventory)
			throws InventoryNotAvailableException, ProductNotFoundException {

		cart.setOrderAmount(0D);
		cart.setTotalProduct(0D);
		cart.setTotalShipping(0D);
		cart.setTotalTax(0D);

		for (final Cartitem orderitem : cart.getCartitems()) {
			final Product product = productStrategy.resolveSKU(orderitem.getSku());
			if (updateInventory)
				inventoryStrategy.updateInventory(product, orderitem.getQuantity());

			orderitem.setSkuCost(priceStrategy.resolveProductCost(product, cart.getCurrency()));
			orderitem.setSkuPrice(priceStrategy.resolveProductPrice(product, cart.getCurrency()));

			discountStrategy.applyItemDiscount(orderitem);
			commerceStrategy.calculateShipping(orderitem);
		}

		commerceStrategy.calculateProductTotal(cart);

		discountStrategy.applyOrderDiscount(cart);

		commerceStrategy.calculateShiping(cart);
		commerceStrategy.calculateTax(cart);
		commerceStrategy.calculateOrderTotal(cart);

		cart = cartRepository.save(cart);

		putCartinSession(cart);

		return cart;
	}

	@Override
	@Transactional
	public void cartDelete() {
		final Cart cart = getSessionCart();
		cartDelete(cart);

	}

	@Override
	@Transactional
	public void cartDelete(final Cart cart) {
		cartRepository.delete(cart);
		removeCartFromSession();
	}

	@Override
	@Transactional
	public void cartItemDelete(Cartitem item) throws InventoryNotAvailableException {
		Cart cart = createSessionCart();

		if (cart.getOrderitems().contains(item))
			cart.getOrderitems().remove(item);

		cart = cartRepository.save(cart);
		try {
			prepareCart(cart, false);
		} catch (ProductNotFoundException e) {
			LOG.error("Error not expected in remove CartItem", e);
			throw new RuntimeException(e);
		}

	}

	@Override
	@Transactional
	public void cartItemDelete(Long id) throws InventoryNotAvailableException {
		Cartitem item = cartItemRepository.findOne(id);
		if (item != null)
			cartItemDelete(item);
	}

	@Override
	@Transactional
	public void setBillingAddress(BillingAddress address) {
		setBillingAddress(addressRepository.clone(address));
	}

	@Override
	@Transactional
	public void setDestinationAddress(PermanentAddress address) {
		setDestinationAddress(addressRepository.clone(address));
	}

	@Override
	public void setShippingMode(Shipmode shippingMode) {
		// TODO Auto-generated method stub

	}

	@Override
	@Transactional
	public void setBillingAddress(Address address) {
		Assert.notNull(address, "Billing address can not be null !");
		Cart c = getSessionCart();
		c.setBillingAddress(address);
		cartRepository.saveAndFlush(c);
		putCartinSession(c);
	}

	@Override
	@Transactional
	public void setDestinationAddress(Address address) {
		Assert.notNull(address, "Destination address can not be null !");
		Cart c = getSessionCart();
		address = addressRepository.save(address);
		c.setShippingAddress(address);
		cartRepository.saveAndFlush(c);
		putCartinSession(c);
	}

	@Override
	public Cart getCartByUser(Member userId, Member customerId) {

		Page<Cart> resultPage = cartRepository.findAllCartByUserId(userId, customerId,
				new PageRequest(0, 10, Sort.Direction.DESC, "updated"));
		Cart result = new Cart();
		if (resultPage.getContent().size() == 0 ) {
			LOG.warn("Non ci sono carrelli associati all'utente {} e al customer {} nel repository", userId, customerId);
			// devo prendere il primo carrello della lista
			result = null;
			
		} else {
			if(resultPage.getContent().size() >1){
				LOG.warn("Ci sono più carrelli associati all'utente {} e al customer {} nel repository", userId, customerId);
				result = resultPage.getContent().get(0);
			}else{
				LOG.warn("Singolo Carrello associato all'utente {} e al customer {}", userId, customerId);
				// carrello singolo	
				result = resultPage.getContent().get(0);
			}
			
		}
		return result;
	}

	@Override
	public void mergeSessionRepositoryCart(Cart repositoryCart, Cart sessionCart)
			throws InventoryNotAvailableException, ProductNotFoundException {
		// Merge di due carrelli repositorycart e sessioncart
		final Iterator<Cartitem> repoItems = repositoryCart.getCartitems().iterator();
		boolean added = false;

		if (sessionCart != null) {
			while (repoItems.hasNext()) {
				final Iterator<Cartitem> sessionItems = sessionCart.getCartitems().iterator();
				final Cartitem repoItem = repoItems.next();
				added = false;
				while (sessionItems.hasNext()) {

					final Cartitem sessionItem = repoItems.next();
					if (sessionItem.getSku().equals(repoItem.getSku())) {

						LOG.debug("Prodotto {} presente sia nel sessionCart sia nel repositoryCart", repoItem.getSku());
						sessionItem.setQuantity(sessionItem.getQuantity() + repoItem.getQuantity());
						added = true;
					}
				}
				if (!added) {
					LOG.debug("Prodotto {} non presente nel sessionCart, quindi lo aggiungo al sessionCart", repoItem.getSku());
					sessionCart.getCartitems().add(repoItem);
				}
			}
			
			prepareCart(sessionCart, true);
			cartDelete(repositoryCart);
			
		} else {
			LOG.debug("Carrello in sessione nullo, inserisco nella sessione il carrello del repository");
			putCartinSession(repositoryCart);
		}

	}

}
