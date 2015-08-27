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

import com.serpics.catalog.ProductNotFoundException;
import com.serpics.catalog.data.model.AbstractProduct;
import com.serpics.catalog.data.model.Product;
import com.serpics.commerce.data.model.Cart;
import com.serpics.commerce.data.model.Cartitem;
import com.serpics.commerce.data.repositories.CartItemRepository;
import com.serpics.commerce.data.repositories.CartRepository;
import com.serpics.commerce.data.repositories.OrderItemRepository;
import com.serpics.commerce.session.CommerceSessionContext;
import com.serpics.commerce.strategies.CommerceStrategy;
import com.serpics.commerce.strategies.DiscountStrategy;
import com.serpics.commerce.strategies.InventoryStrategy;
import com.serpics.commerce.strategies.PriceStrategy;
import com.serpics.commerce.strategies.ProductStrategy;
import com.serpics.core.service.AbstractService;
import com.serpics.membership.data.model.Store;
import com.serpics.membership.data.model.User;
import com.serpics.warehouse.InventoryNotAvailableException;

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

    @Override
    @Transactional
    public Cart createSessionCart() {
        Cart cart = cartRepository.findByCookie(getCurrentContext().getUserCookie());
        if (cart == null) {

            cart = new Cart((User) getCurrentContext().getUserPrincipal(), (Store) getCurrentContext().getStoreRealm(),
                    getCurrentContext().getUserCookie());

            cart.setCurrency(((Store) getCurrentContext().getStoreRealm()).getCurrency());
            cart.setCustomer((User)getCurrentContext().getCustomer());
            cartRepository.create(cart);
        }
        getCurrentContext().setAttribute(SESSION_CART, cart);
        return cart;
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
    public Cart cartUpdate(final Cartitem orderitem, final Cart cart) throws InventoryNotAvailableException,
    ProductNotFoundException {
        final Product product = productStrategy.resolveSKU(orderitem.getSku());

        orderitem.setSkuCost(priceStrategy.resolveProductCost(product, cart.getCurrency()));
        orderitem.setSkuPrice(priceStrategy.resolveProductPrice(product, cart.getCurrency()));
        discountStrategy.applyItemDiscount(orderitem);

        orderitem.setOrder(cart);
        cart.getCartitems().add(orderitem);

        commerceStrategy.calculateShipping(orderitem);

        return cartRepository.update(cart);

    }

    
    
    
    @Override
    @Transactional
	public Cart cartUpdateProduct(Hashtable<Product, Double> list)
			throws InventoryNotAvailableException, ProductNotFoundException {

		Cart cart = getSessionCart();
		cart.getOrderitems().clear();
	//	cart = cartRepository.update(cart);
		
//		Set<AbstractOrderitem> abs = new HashSet<AbstractOrderitem>();
//		cart.setCartitems(abs);

		Enumeration<Product> e = list.keys();
		while (e.hasMoreElements()) {
			Product product = (Product) e.nextElement();
			double q = list.get(product);
			if(q > 0)  cartAdd(product, q,cart, true);
		}
		cart = cartRepository.update(cart);
		return cart;
    	
    	

	}
    
    @Override
    @Transactional
    public Cart cartAdd(final AbstractProduct product, final double quantity, final Cart cart, final boolean merge)
            throws InventoryNotAvailableException, ProductNotFoundException {

        Cartitem cartItem = new Cartitem();
        cartItem.setSku(product.getCode());
        cartItem.setQuantity(quantity);
       
        if (merge)
            cartItem = mergeCart(cart, cartItem);

        cartItem.setSkuCost(priceStrategy.resolveProductCost(product, cart.getCurrency()));
        cartItem.setSkuPrice(priceStrategy.resolveProductPrice(product, cart.getCurrency()));

        discountStrategy.applyItemDiscount(cartItem);
        cartItem.setOrder(cart);
        
        //cartItem.setProduct(product);
        cart.getCartitems().add(cartItem);

        commerceStrategy.calculateShipping(cartItem);

        return cartRepository.update(cart);
    }

    @Override
    @Transactional
    public Cart cartUpdate(final Cartitem cartItem) throws InventoryNotAvailableException, ProductNotFoundException {
        final Cart cart = createSessionCart();
        return cartUpdate(cartItem, cart);
    }

    @Override
    @Transactional
    public Cart cartAdd(final AbstractProduct product, final double quantity, final boolean merge) throws InventoryNotAvailableException,
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
    public Cart cartAdd(final String sku, final double quantity, final Cart cart, final boolean merge) throws InventoryNotAvailableException,
    ProductNotFoundException {
        final Product product = productStrategy.resolveSKU(sku);
        return cartAdd(product, quantity, merge);
    }

    @Override
    @Transactional
    public Cart cartAdd(final String sku, final double quantity, final boolean merge) throws InventoryNotAvailableException,
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
                inventoryStrategy.updateInventory(product);
            else
                inventoryStrategy.checkInventory(product);
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

    }
}
