package com.serpics.commerce.strategies;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.serpics.base.data.model.Store;
import com.serpics.catalog.ProductNotFoundException;
import com.serpics.commerce.data.model.Cart;
import com.serpics.commerce.data.repositories.CartRepository;
import com.serpics.commerce.services.CartService;
import com.serpics.membership.data.model.Member;
import com.serpics.membership.data.model.User;
import com.serpics.membership.services.UserService;
import com.serpics.stereotype.StoreStrategy;
import com.serpics.warehouse.InventoryNotAvailableException;

@StoreStrategy(value = "cartStrategy")
public class CartStrategyImpl extends AbstractStrategy implements CartStrategy {
	
	Logger LOG = LoggerFactory.getLogger(CartStrategyImpl.class);
	
	@Autowired
	CartService cartService;
	
	@Autowired
	CartRepository cartRepository;
	
	@Autowired
	UserService userService;
	

	@Override
	@Transactional
	public void mergeCart(Member user, Member customer,Store store, String sessionId) throws InventoryNotAvailableException, ProductNotFoundException{
		
		// TODO implementare un metodo che faccia il merge del carrello in
		// sessione con il carrello nel repository in base allo userid e al customerid
		//il merge viene fatto in maniera addizionale senza controllare il contenuto del carrello.
		Cart repositoryCart = cartService.getCartByUser(user, customer, store,sessionId);
		Cart sessionCart= cartService.getSessionCart();
		
		if (repositoryCart != null){
			//sono presenti dei carrelli nel repository devo effettuare il merge con quello in sessione
//			try {
				
				cartService.mergeSessionRepositoryCart(repositoryCart, sessionCart);
				
//			} catch (InventoryNotAvailableException e) {
//				LOG.error("Merge Cart Error (InventoryNotAvailableException): ",e);
//			} catch (ProductNotFoundException e) {
//				LOG.error("Merge Cart Error (ProductNotFoundException): ",e);
//			}
		}else{
			//non sono presenti non viene effettuato il merge
			//in questo caso o è presente il carrello in sessione oppure non ci sono carrelli per l'utente loggato
			LOG.debug("Non sono presenti carrelli per effettuare il merge");
		}
		
		sessionCart.setUser((User)user);
		sessionCart.setCustomer(customer);	
		
		cartRepository.saveAndFlush(sessionCart);
	}

}
	