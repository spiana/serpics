package com.serpics.commerce.services;

import java.math.BigDecimal;
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
import com.serpics.catalog.hooks.InventoryHook;
import com.serpics.catalog.hooks.PriceHook;
import com.serpics.catalog.hooks.ProductHook;
import com.serpics.commerce.data.model.Cart;
import com.serpics.commerce.data.model.Cartitem;
import com.serpics.commerce.hooks.CommerceHook;
import com.serpics.commerce.hooks.DiscountHook;
import com.serpics.commerce.repositories.CartRepository;
import com.serpics.commerce.repositories.OrderItemRepository;
import com.serpics.commerce.session.CommerceSessionContext;
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

            cart = new Cart((User) getCurrentContext().getUserPrincipal(), (Store) getCurrentContext().getStoreRealm(),
                    getCurrentContext().getUserCookie());

            cart.setCurrency(((Store) getCurrentContext().getStoreRealm()).getCurrency());
            cartRepository.saveAndFlush(cart);
        }
        getCurrentContext().setAttribute("cart", cart);
        return cart;
    }

    @Override
    public Cart getSessionCart() {
        Cart currentCart = (Cart) getCurrentContext().getAttribute("cart");

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
        final Product product = productHook.resolveSKU(orderitem.getSku());

        orderitem.setSkuCost(priceHook.resolveProductCost(product, cart.getCurrency()));
        orderitem.setSkuPrice(priceHook.resolveProductPrice(product, cart.getCurrency()));
        discountHook.applyItemDiscount(orderitem);

        orderitem.setOrder(cart);
        cart.getCartitems().add(orderitem);

        commerceHook.calculateShipping(orderitem);

        return cartRepository.saveAndFlush(cart);

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

        cartItem.setSkuCost(priceHook.resolveProductCost(product, cart.getCurrency()));
        cartItem.setSkuPrice(priceHook.resolveProductPrice(product, cart.getCurrency()));

        discountHook.applyItemDiscount(cartItem);
        cartItem.setOrder(cart);
        cart.getCartitems().add(cartItem);

        commerceHook.calculateShipping(cartItem);

        return cartRepository.saveAndFlush(cart);
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
        final Product product = productHook.resolveSKU(sku);
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
            final Product product = productHook.resolveSKU(orderitem.getSku());
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
        commerceHook.calculateOrderTotal(cart);

        return cartRepository.saveAndFlush(cart);
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
