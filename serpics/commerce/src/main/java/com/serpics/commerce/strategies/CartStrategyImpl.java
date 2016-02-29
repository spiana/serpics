package com.serpics.commerce.strategies;

import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.serpics.catalog.ProductNotFoundException;
import com.serpics.catalog.data.model.Product;
import com.serpics.commerce.data.model.Cart;
import com.serpics.commerce.data.model.Cartitem;
import com.serpics.commerce.data.repositories.CartRepository;
import com.serpics.stereotype.StoreStrategy;
import com.serpics.warehouse.InventoryNotAvailableException;

@StoreStrategy(value = "cartStrategy")
public class CartStrategyImpl implements CartStrategy {

	Logger LOG = LoggerFactory.getLogger(CartStrategyImpl.class);
	
	@Autowired
	CartRepository cartRepository;

	@Override
	@Transactional
	public void mergeCart(Cart repositoryCart, Cart sessionCart) throws InventoryNotAvailableException, ProductNotFoundException {

		// TODO implementare un metodo che faccia il merge del carrello in
		// sessione con il carrello nel repository in base allo userid e al
		// customerid
		// il merge viene fatto in maniera addizionale senza controllare il
		// contenuto del carrello.

		// Merge di due carrelli repositorycart e sessioncart
		final Iterator<Cartitem> repoItems = repositoryCart.getCartitems().iterator();

			while (repoItems.hasNext()) {

				final Cartitem repoItem = repoItems.next();

				Cartitem cartItem = new Cartitem();
				Product product = repoItem.getProduct();
				cartItem.setSku(repoItem.getSku());
				cartItem.setQuantity(repoItem.getQuantity());
				cartItem.setProduct(product);

				
				final Iterator<Cartitem> items = sessionCart.getCartitems().iterator();

				while (items.hasNext()) {
					final Cartitem oi = items.next();
					if (oi.getSku().equals(cartItem.getSku())) {
						cartItem.setQuantity(cartItem.getQuantity() + oi.getQuantity());
						// oi.setOrder(null);
						items.remove();
					}
				}

				cartItem.setCart(sessionCart);

				sessionCart.getCartitems().add(cartItem);

				cartRepository.saveAndFlush(sessionCart);
				cartRepository.refresh(sessionCart);

			}
	}
}
