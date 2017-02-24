/*******************************************************************************
 * Copyright 2014-2016  StepFour Srl
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *  
 *      http://www.apache.org/licenses/LICENSE-2.0
 *  
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *  
 *******************************************************************************/
package com.serpics.commerce.strategies;

import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.serpics.catalog.ProductNotFoundException;
import com.serpics.catalog.data.model.AbstractProduct;
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
		final Iterator<Cartitem> repoItems = repositoryCart.getItems().iterator();

			while (repoItems.hasNext()) {

				final Cartitem repoItem = repoItems.next();

				Cartitem cartItem = new Cartitem();
				AbstractProduct product = repoItem.getProduct();
				cartItem.setSku(repoItem.getSku());
				cartItem.setQuantity(repoItem.getQuantity());
				cartItem.setProduct(product);
				//cartItem.setSkuNetPrice(repoItem.getSkuNetPrice());

				
				final Iterator<Cartitem> items = sessionCart.getItems().iterator();

				while (items.hasNext()) {
					final Cartitem oi = items.next();
					if (oi.getSku().equals(cartItem.getSku())) {
						cartItem.setQuantity(cartItem.getQuantity() + oi.getQuantity());
						// oi.setOrder(null);
						items.remove();
					}
				}

				cartItem.setOrder(sessionCart);

				sessionCart.getItems().add(cartItem);

				cartRepository.saveAndFlush(sessionCart);
				cartRepository.refresh(sessionCart);

			}
	}
}
