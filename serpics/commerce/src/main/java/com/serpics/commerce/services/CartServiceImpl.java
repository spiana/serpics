package com.serpics.commerce.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.serpics.base.data.model.Country;
import com.serpics.base.data.model.Currency;
import com.serpics.base.data.model.District;
import com.serpics.base.data.model.Region;
import com.serpics.base.data.model.Store;
import com.serpics.catalog.ProductNotFoundException;
import com.serpics.catalog.data.model.Product;
import com.serpics.commerce.PaymentException;
import com.serpics.commerce.PaymentIntent;
import com.serpics.commerce.ShipmodeException;
import com.serpics.commerce.core.CommerceEngine;
import com.serpics.commerce.data.model.Cart;
import com.serpics.commerce.data.model.Cartitem;
import com.serpics.commerce.data.model.Payment;
import com.serpics.commerce.data.model.Paymethod;
import com.serpics.commerce.data.model.Paymethodlookup;
import com.serpics.commerce.data.model.Shipmode;
import com.serpics.commerce.data.repositories.CartItemRepository;
import com.serpics.commerce.data.repositories.CartRepository;
import com.serpics.commerce.data.repositories.OrderItemRepository;
import com.serpics.commerce.data.repositories.PaymentRepository;
import com.serpics.commerce.data.repositories.PaymethodRepository;
import com.serpics.commerce.data.repositories.ShipmodeRepository;
import com.serpics.commerce.session.CommerceSessionContext;
import com.serpics.commerce.strategies.CartStrategy;
import com.serpics.commerce.strategies.CommerceStrategy;
import com.serpics.commerce.strategies.DiscountStrategy;
import com.serpics.commerce.strategies.PriceStrategy;
import com.serpics.commerce.strategies.ProductStrategy;
import com.serpics.core.service.AbstractService;
import com.serpics.membership.data.model.Address;
import com.serpics.membership.data.model.BillingAddress;
import com.serpics.membership.data.model.Member;
import com.serpics.membership.data.model.PermanentAddress;
import com.serpics.membership.data.model.User;
import com.serpics.membership.data.repositories.AddressRepository;
import com.serpics.warehouse.InventoryNotAvailableException;
import com.serpics.warehouse.InventoryStatusEnum;
import com.serpics.warehouse.service.InventoryService;

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
	PaymentService paymentService;


	@Resource
	PriceStrategy priceStrategy;

	@Resource
	ProductStrategy productStrategy;
	@Resource
	CommerceStrategy commerceStrategy;
	
	@Resource
	CartStrategy cartStrategy;

	@Resource
	InventoryService inventoryService;

	@Resource
	AddressRepository addressRepository;

	
	@Resource
	ShipmodeRepository shipmodeRepository;
	
	@Resource
	PaymethodRepository paymethodRepository;
	
	@Resource
	PaymentRepository paymentRepository;
	
	@Resource
	CommerceEngine commerceEngine;
	
	@Resource
	ShipmodeService shipmodeService;

	protected Specification<Shipmode> findShipmodeByNameSpecification(final String name) {
		return new Specification<Shipmode>() {
			@Override
			public Predicate toPredicate(final Root<Shipmode> root, final CriteriaQuery<?> query, 
					final CriteriaBuilder cb) {
						Predicate p = cb.equal(root.get("name"), name);
						return p;
			}
		};
	}
	
	protected Specification<Paymethod> findPaymethodByNameSpecification(final String name) {
		return new Specification<Paymethod>() {
			@Override
			public Predicate toPredicate(final Root<Paymethod> root, final CriteriaQuery<?> query, 
					final CriteriaBuilder cb) {
						Predicate p = cb.equal(root.get("name"), name);
						return p;
			}
		};
	}
	
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

	@Override
	public void removeCartFromSession() {
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
	public Cart cartAdd(final Product product, final double quantity, Cart cart, final boolean merge)
			throws InventoryNotAvailableException, ProductNotFoundException {

		Cartitem cartItem = new Cartitem();
		cartItem.setSku(product.getCode());
		cartItem.setQuantity(quantity);
		cartItem.setProduct(product);
		cartItem.setTaxcategory(product.getTaxcategory());

		InventoryStatusEnum status = (InventoryStatusEnum) inventoryService.checkInventory(product, quantity);
		if (status == InventoryStatusEnum.OutOfStock)
			throw new InventoryNotAvailableException(product.getCode(), quantity);

		if (merge)
			cartItem = mergeCart(cart, cartItem);

		cartItem.setSkuCost(priceStrategy.resolveProductCost(product, cart.getCurrency()));
		cartItem.setSkuPrice(priceStrategy.resolveProductPrice(product, cart.getCurrency()));

		discountStrategy.applyItemDiscount(cartItem);
		cartItem.setCart(cart);

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
	public Cart cartAdd(final Product product, final double quantity, final boolean merge)
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
	@Transactional
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
				inventoryService.reserve(product, orderitem.getQuantity());

			orderitem.setSkuCost(priceStrategy.resolveProductCost(product, cart.getCurrency()));
			orderitem.setSkuPrice(priceStrategy.resolveProductPrice(product, cart.getCurrency()));

			discountStrategy.applyItemDiscount(orderitem);
			commerceStrategy.calculateShipping(orderitem);
		}

		commerceStrategy.calculateProductTotal(cart);

		discountStrategy.applyOrderDiscount(cart);

		try {
			commerceStrategy.calculateShipping(cart);
		} catch (ShipmodeException e) {
			LOG.error("Error to calculate Shipping Cost", e);
		}
		
		commerceStrategy.calculateTax(cart);
		commerceStrategy.calculateOrderTotal(cart);

		cart = cartRepository.saveAndFlush(cart);

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
	public void cartRepositoryDelete(final Cart cart) {
		cartRepository.delete(cart);
	}

	@Override
	@Transactional
	public void cartItemDelete(Cartitem item) throws InventoryNotAvailableException {
		Cart cart = createSessionCart();

		if (cart.getCartitems().contains(item))
			cart.getCartitems().remove(item);

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
		c.setShippingAddress(address);
		cartRepository.saveAndFlush(c);
		putCartinSession(c);
	}

	@Override
	public Cart getCartByUser(Member userId, Member customerId, Store store, String sessionId) {

		Page<Cart> resultPage = cartRepository.findAllCartByUserId(userId, customerId, store, sessionId,
				new PageRequest(0, 10, Sort.Direction.DESC, "updated"));
		Cart result = new Cart();
		if (resultPage.getContent().size() == 0) {
			LOG.warn("Non ci sono carrelli associati all'utente {} e al customer {} nel repository", userId,
					customerId);
			
			result = null;

		} else {
			if (resultPage.getContent().size() > 1) {
				LOG.warn("Ci sono più carrelli associati all'utente {} e al customer {} nel repository", userId,
						customerId);
				// devo prendere il primo carrello della lista
				result = resultPage.getContent().get(0);
			} else {
				LOG.warn("Singolo Carrello associato all'utente {} e al customer {}", userId, customerId);
				// carrello singolo
				result = resultPage.getContent().get(0);
			}

		}
		return result;
	}

	@Override
	@Transactional
	public void mergeSessionRepositoryCart(Cart repositoryCart, Cart sessionCart)
			throws InventoryNotAvailableException, ProductNotFoundException {
		// Merge di due carrelli repositorycart e sessioncart
		final Iterator<Cartitem> repoItems = repositoryCart.getCartitems().iterator();

		if (sessionCart != null) {
			while (repoItems.hasNext()) {

				final Cartitem repoItem = repoItems.next();

				Cartitem cartItem = new Cartitem();
				Product product = repoItem.getProduct();
				cartItem.setSku(repoItem.getSku());
				cartItem.setQuantity(repoItem.getQuantity());
				cartItem.setProduct(product);

				cartItem = mergeCart(sessionCart, cartItem);

				cartItem.setCart(sessionCart);

				sessionCart.getCartitems().add(cartItem);

				cartRepository.saveAndFlush(sessionCart);
				cartRepository.refresh(sessionCart);

			}
			prepareCart(sessionCart);

			cartRepositoryDelete(repositoryCart);

		}
		putCartinSession(sessionCart);

	}

	
	@Override
	public List<Shipmode> getShipmode(){
		List<Shipmode> shipmodeList = new ArrayList<Shipmode>();
		Cart cart = getSessionCart();
		Store store = (Store) commerceEngine.getCurrentContext().getStoreRealm();
		Address address = cart.getShippingAddress();
		if (address != null){
			String zipCode = address.getZipcode();
			Country country = address.getCountry();
			Region region = address.getRegion();
			District district = address.getDistrict();
//			Geocode geocode = address.getGeocode();
			if (zipCode != null){
				shipmodeList.addAll(shipmodeRepository.getShipmodeFromZipCode(store, zipCode));
			}
			if (district != null){
				shipmodeList.addAll(shipmodeRepository.getShipmodeFromDistrict(store, district));
			}
			if (region != null){
				shipmodeList.addAll(shipmodeRepository.getShipmodeFromRegion(store, region));
			}
			if (country != null){
				shipmodeList.addAll(shipmodeRepository.getShipmodeFromCountry(store, country));
			}
//			if (geocode != null){
//				shipmodeList.addAll(shipmodeRepository.getShipmodeFromGeocode(store, geocode));
//			}
		}
		shipmodeList.addAll(shipmodeRepository.getShipmodeFromStore(store));
		Iterator<Shipmode> i = shipmodeList.iterator();
		Iterator<Shipmode> k = shipmodeList.iterator();
		while (i.hasNext()){
			Shipmode shipmode = i.next();
			k = i;
			while (k.hasNext()){
				if (shipmode.getName().equals(k.next().getName())){
					k.remove();
				}
			}
		}
		return shipmodeList;
	}
	
	@Override
	@Transactional
	public void addShipmode(String shipmodeName){
		Shipmode shipmode = shipmodeRepository.findOne(findShipmodeByNameSpecification(shipmodeName));
		Assert.notNull(shipmode, "shipmode can not be null !");
		Cart c = getSessionCart();
		c.setShipmode(shipmode);
		cartRepository.saveAndFlush(c);
		putCartinSession(c);
	}
	
	@Override
	public List<Paymethod> getPaymethod(){
		Store store = (Store) commerceEngine.getCurrentContext().getStoreRealm();
		List<Paymethod> paymethodList = paymethodRepository.findActivePaymentmethod(store);		
		return paymethodList;
	}


	@Override
	@Transactional
	public void addPaymethod(String paymethodName){
		Paymethod paymethod = paymethodRepository.findOne(findPaymethodByNameSpecification(paymethodName));
		Assert.notNull(paymethod, "paymethod can not be null !");
		Cart c = getSessionCart();
		c.setPaymethod(paymethod);
		cartRepository.saveAndFlush(c);
		putCartinSession(c);
	}

	@Override
	@Transactional
	public void mergeCartAtLogin(Member user, Member customer, Store store, String sessionId) throws InventoryNotAvailableException, ProductNotFoundException {

		Cart repositoryCart = getCartByUser(user, customer, store, sessionId);
		Cart sessionCart = getSessionCart();

		if (repositoryCart != null) {
			if (sessionCart != null) {
				// sono presenti dei carrelli nel repository devo effettuare il merge con quello in sessione
				cartStrategy.mergeCart(repositoryCart, sessionCart);
				cartRepositoryDelete(repositoryCart);				
			} else {
				LOG.debug("è presente solamente il repositoryCart");
			}
			
			//putCartinSession(sessionCart);

		} else {
			// non sono presenti non viene effettuato il merge
			// in questo caso o è presente il carrello in sessione oppure non ci
			// sono carrelli per l'utente loggato
			LOG.debug("Non sono presenti carrelli per effettuare il merge");
		}

		prepareCart(sessionCart);
		sessionCart.setUser((User)user);
		sessionCart.setCustomer(customer);	
		
		cartRepository.saveAndFlush(sessionCart);
		
		
	}

	@Override
	@Transactional
	public Payment createPayment() throws PaymentException {

		Cart sessionCart = getSessionCart();
		Payment payment = null;
		Paymethod payMethod = sessionCart.getPaymethod();
		
		
		if ( payMethod != null){
			
			Paymethodlookup paymethodlookup = paymentService.findPaymethodInfo(payMethod);
			PaymentIntent intent = paymethodlookup.getIntent();

			LOG.debug("Strategy for del current cart payment: {}",sessionCart.getPaymethod().getPaymentStrategy());
			try{
				payment = paymentService.createPayment(sessionCart, intent);
			}catch(PaymentException e){
				LOG.debug("Create Payment error ",e);
				throw new PaymentException("Create Payment error ",e);
			}
			//il salvataggio del payment viene fatto nella strategy
			//Salvo il payment
//			paymentRepository.saveAndFlush(payment);
			
		} else {
			LOG.debug("In this sessionCart there isn't a Paymethod!");
			throw new PaymentException("In this sessionCart there isn't a Paymethod!");
		}
		
		return payment;
	}
	
	@Override
	@Transactional
	public void addPaymentInfo(String paymentIdentifier, String PayerID) throws PaymentException{
		
		Cart sessionCart = getSessionCart();
		Payment payment = paymentService.findCurrentPendingPayment(sessionCart);
		
		Assert.notNull(payment, "Payment can not be null!");
		if (payment != null){
			payment.setPayerId(PayerID);
			//TODO Verificare il paymentIdentifier
			payment.setPaymentIdentifier((payment.getPaymentIdentifier() != null) ?  paymentIdentifier : paymentIdentifier);
		} else {
			LOG.debug("payment can not be null!");
			throw new PaymentException("Payment can not be null!");
		}

		cartRepository.saveAndFlush(sessionCart);
		putCartinSession(sessionCart);
	}
	
}
