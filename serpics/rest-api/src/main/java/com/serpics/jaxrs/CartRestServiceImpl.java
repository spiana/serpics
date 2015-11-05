package com.serpics.jaxrs;

import javax.annotation.Resource;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.serpics.commerce.facade.CartFacade;
import com.serpics.commerce.facade.data.CartData;
import com.serpics.commerce.facade.data.CartItemData;
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
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public CartItemModification cartAdd(@FormParam("sku") String sku, @FormParam("qty") @DefaultValue("1") int quantity ) {
		Assert.notNull(sku);
		CartItemModification  cartItemModification= cartFacade.cartAdd(sku, quantity);
		return cartItemModification;
	}

	@Override
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public CartItemModification cartUpdate(CartItemData cartItem) {
		Assert.notNull(cartItem);
		return cartFacade.update(cartItem);
	}

	@Override
	@DELETE
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public CartItemModification deleteItem(@FormParam("itemId") Long itemId){
		Assert.notNull(itemId);
		return cartFacade.cartItemDelete(itemId);
	}
	
	@Override
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@POST
	@Path("address/billing")
	public CartData addBillingAddress(AddressData billingAddress){
		Assert.notNull(billingAddress);
		return cartFacade.addBillingAddress(billingAddress);
	}
	
	@Override
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@POST
	@Path("address/shipping")
	public CartData addShippingAddress(AddressData shippingAddress){
		Assert.notNull(shippingAddress);
		return cartFacade.addBillingAddress(shippingAddress);
	}
	
}
