package com.serpics.jaxrs;

import javax.annotation.Resource;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.transaction.annotation.Transactional;

import com.serpics.commerce.facade.CartFacade;
import com.serpics.commerce.facade.data.CartData;
import com.serpics.commerce.facade.data.CartItemModification;
import com.serpics.membership.facade.data.AddressData;


@Path("/cartService")
@Transactional(readOnly=true)
public class CartRestServiceImpl implements CartRestService {

	@Resource
	CartFacade cartFacade;
	
	@Override
	@Produces(MediaType.APPLICATION_JSON)
	@GET
	public CartData getCurrentCart() {
		return cartFacade.getCurrentCart();
	}

	@Override
	public CartItemModification carAdd(String sku, int quantity, double price) {
	
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public CartItemModification carAdd(@FormParam("sku") String sku,@FormParam("qty") @DefaultValue("1") int quantity ) {
		CartItemModification  cartItemModification= cartFacade.cartAdd(sku, quantity);
		return cartItemModification;
	}

	@Override
	public CartItemModification cartUpdate(Long cartUtemID, int quantity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response setBillingAddress(AddressData billingAddress) {
		cartFacade.addBillingAddress(billingAddress);
		return null;
	}

	@Override
	public Response setDestinationAddrress(AddressData destinationAddress) {
		// TODO Auto-generated method stub
		return null;
	}

}
