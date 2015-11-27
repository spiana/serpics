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
import javax.ws.rs.core.Response;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.serpics.commerce.facade.CartFacade;
import com.serpics.commerce.facade.data.CartData;
import com.serpics.commerce.facade.data.CartItemData;
import com.serpics.commerce.facade.data.CartItemModification;
import com.serpics.jaxrs.data.ApiRestResponse;
import com.serpics.jaxrs.data.ApiRestResponseStatus;
import com.serpics.membership.facade.data.AddressData;


@Path("/cartService")
@Transactional(readOnly=true)
public class CartRestServiceImpl implements CartRestService {

	@Resource
	CartFacade cartFacade;
	
	@Override
	@Produces(MediaType.APPLICATION_JSON)
	@GET
	public Response getCurrentCart() {
		ApiRestResponse<CartData> apiRestResponse = new ApiRestResponse<CartData>();
		CartData cartData = cartFacade.getCurrentCart();
		apiRestResponse.setStatus(ApiRestResponseStatus.OK);
		apiRestResponse.setResponseObject(cartData);
		return Response.ok(apiRestResponse).build();
	}

	@Override
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Response cartAdd(@FormParam("sku") String sku, @FormParam("qty") @DefaultValue("1") int quantity ) {
		Assert.notNull(sku);
		ApiRestResponse<CartItemModification> apiRestResponse = new ApiRestResponse<CartItemModification>();
		CartItemModification  cartItemModification = cartFacade.cartAdd(sku, quantity);
		apiRestResponse.setStatus(ApiRestResponseStatus.OK);
		apiRestResponse.setResponseObject(cartItemModification);
		return Response.ok(apiRestResponse).build();
	}

	@Override
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response cartUpdate(CartItemData cartItem) {
		Assert.notNull(cartItem);
		ApiRestResponse<CartItemModification> apiRestResponse = new ApiRestResponse<CartItemModification>();
		CartItemModification  cartItemModification = cartFacade.update(cartItem);
		apiRestResponse.setStatus(ApiRestResponseStatus.OK);
		apiRestResponse.setResponseObject(cartItemModification);
		return Response.ok(apiRestResponse).build();
	}

	@Override
	@DELETE
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response deleteItem(@FormParam("itemId") Long itemId){
		Assert.notNull(itemId);
		ApiRestResponse<CartItemModification> apiRestResponse = new ApiRestResponse<CartItemModification>();
		CartItemModification  cartItemModification = cartFacade.cartItemDelete(itemId);
		apiRestResponse.setStatus(ApiRestResponseStatus.OK);
		apiRestResponse.setResponseObject(cartItemModification);
		return Response.ok(apiRestResponse).build();
	}
	
	@Override
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@POST
	@Path("address/billing")
	public Response addBillingAddress(AddressData billingAddress){
		Assert.notNull(billingAddress);
		ApiRestResponse<CartData> apiRestResponse = new ApiRestResponse<CartData>();
		CartData cartData = cartFacade.addBillingAddress(billingAddress);
		apiRestResponse.setStatus(ApiRestResponseStatus.OK);
		apiRestResponse.setResponseObject(cartData);
		return Response.ok(apiRestResponse).build();
	}
	
	@Override
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@POST
	@Path("address/shipping")
	public Response addShippingAddress(AddressData shippingAddress){
		Assert.notNull(shippingAddress);
		ApiRestResponse<CartData> apiRestResponse = new ApiRestResponse<CartData>();
		CartData cartData = cartFacade.addBillingAddress(shippingAddress);
		apiRestResponse.setStatus(ApiRestResponseStatus.OK);
		apiRestResponse.setResponseObject(cartData);
		return Response.ok(apiRestResponse).build();
	}

}
