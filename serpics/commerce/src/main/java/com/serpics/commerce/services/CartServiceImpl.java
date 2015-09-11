package com.serpics.commerce.services;

import java.math.BigDecimal;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
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
            cart.setCustomer((User)getCurrentContext().getCustomer());
            cartRepository.create(cart);
        }
       putCartinSession(cart);
        return cart;
    }

    private void  putCartinSession(Cart cart){
    	 getCurrentContext().setAttribute(SESSION_CART, cart);
    }
    private void  removeCartFromSession(){
   	 getCurrentContext().setAttribute(SESSION_CART, null);
   }
    
    @Override
    public Cart getSessionCart() {
        Cart currentCart = (Cart) getCurrentContext().getAttribute(SESSION_CART);

        if (currentCart == null) {
            LOG.info("NOT found cart in commerce session for user {}",
                    ((User) getCurrentContext().getUserPrincipal()).getUuid());
            currentCart = createSessionCart();
        } else {
            LOG.info("found cart in commerce session for user {}", currentCart.getUser().getUuid());

        }
        return currentCart;
    }

    @Override
    @Transactional
    public Cartitem cartUpdate(final Cartitem orderitem, final Cart cart) throws InventoryNotAvailableException,
    ProductNotFoundException {
        final Product product = productStrategy.resolveSKU(orderitem.getSku());

        orderitem.setSkuCost(priceStrategy.resolveProductCost(product, cart.getCurrency()));
        orderitem.setSkuPrice(priceStrategy.resolveProductPrice(product, cart.getCurrency()));
        discountStrategy.applyItemDiscount(orderitem);

        orderitem.setOrder(cart);
        cart.getCartitems().add(orderitem);

        commerceStrategy.calculateShipping(orderitem);
        cartRepository.update(cart);
        
        putCartinSession(cart);
        
        return  orderitem;

    }

    
    
    
    @Override
    @Transactional
	public Cart cartUpdateProduct(Hashtable<Product, Double> list)
			throws InventoryNotAvailableException, ProductNotFoundException {

		Cart cart = getSessionCart();
		
		cart = cartRepository.refresh(cart);
		
		cart.getOrderitems().clear();
	
		Enumeration<Product> e = list.keys();
		while (e.hasMoreElements()) {
			Product product = (Product) e.nextElement();
			double q = list.get(product);
			if(q > 0)  cartAdd(product, q,cart, true);
		}
		cart = cartRepository.update(cart);
		
		putCartinSession(cart);
		
		return cart;
    	
    	

	}
    
    @Override
    @Transactional
    public Cartitem cartAdd(final AbstractProduct product, final double quantity, final Cart cart, final boolean merge)
            throws InventoryNotAvailableException, ProductNotFoundException {

        Cartitem cartItem = new Cartitem();
        cartItem.setSku(product.getCode());
        cartItem.setQuantity(quantity);
         InventoryStatusEnum status = (InventoryStatusEnum) inventoryStrategy.checkInventory(product , quantity);
         if (status == InventoryStatusEnum.OutOfStock)
        	 throw new InventoryNotAvailableException(product.getCode(), quantity);
       
        if (merge)
            cartItem = mergeCart(cart, cartItem);

        cartItem.setSkuCost(priceStrategy.resolveProductCost(product, cart.getCurrency()));
        cartItem.setSkuPrice(priceStrategy.resolveProductPrice(product, cart.getCurrency()));

        discountStrategy.applyItemDiscount(cartItem);
        cartItem.setOrder(cart);
        
        //cartItem.setProduct(product);
        cart.getCartitems().add(cartItem);

        commerceStrategy.calculateShipping(cartItem);

       cartRepository.update(cart);
       return cartItem;
    }

    @Override
    @Transactional
    public Cartitem cartUpdate(final Cartitem cartItem) throws InventoryNotAvailableException, ProductNotFoundException {
        final Cart cart = createSessionCart();
        return cartUpdate(cartItem, cart);
    }

    @Override
    @Transactional
    public Cartitem cartAdd(final AbstractProduct product, final double quantity, final boolean merge) throws InventoryNotAvailableException,
    ProductNotFoundException {
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
    public Cartitem cartAdd(final String sku, final double quantity, final Cart cart, final boolean merge) throws InventoryNotAvailableException,
    ProductNotFoundException {
        final Product product = productStrategy.resolveSKU(sku);
        return cartAdd(product, quantity, merge);
    }

    @Override
    @Transactional
    public Cartitem cartAdd(final String sku, final double quantity, final boolean merge) throws InventoryNotAvailableException,
    ProductNotFoundException {
        final Cart cart = createSessionCart();
        return cartAdd(sku, quantity, cart, merge);

    }

    @Override
    public Cart prepareCart() throws InventoryNotAvailableException, ProductNotFoundException {
        final Cart cart = getSessionCart();
        Assert.notNull(cart);

        return prepareCart(cart);
    }

    @Override
    @Transactional
    public Cart prepareCart(final Cart cart) throws InventoryNotAvailableException, ProductNotFoundException {
        return prepareCart(cart, false);
    }

    @Override
    @Transactional
    public Cart prepareCart(final Cart cart, final boolean updateInventory) throws InventoryNotAvailableException,
    ProductNotFoundException {
    	
        cart.setOrderAmount(new BigDecimal(0));
        cart.setTotalProduct(new BigDecimal(0));
        cart.setTotalShipping(new BigDecimal(0));
        cart.setTotalTax(new BigDecimal(0));

        for (final Cartitem orderitem : cart.getCartitems()) {
            final Product product = productStrategy.resolveSKU(orderitem.getSku());
            if (updateInventory)
                inventoryStrategy.updateInventory(product , orderitem.getQuantity());
          
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

        putCartinSession(cart);
        
        return cartRepository.update(cart);
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
    public void cartItemDelete(Cartitem item) {
    	Cart cart =  createSessionCart() ;
    	
    	if (cart.getOrderitems().contains(item))
    		cart.getOrderitems().remove(item);
    	
    	cart = cartRepository.update(cart);
    	
    	putCartinSession(cart);
    	
    }
    @Override
    @Transactional
    public void cartItemDelete(Long id) {
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
	}

	@Override
	@Transactional
	public void setDestinationAddress(Address address) {
		Assert.notNull(address, "Destination address can not be null !");
		Cart c = getSessionCart();
		c.setShippingAddress(address);
		cartRepository.saveAndFlush(c);
	}	
    	
}
