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

import com.qmino.miredot.annotations.ReturnType;
import com.serpics.commerce.facade.CartFacade;
import com.serpics.commerce.facade.data.CartData;
import com.serpics.commerce.facade.data.CartItemData;
import com.serpics.commerce.facade.data.CartModification;
import com.serpics.jaxrs.data.ApiRestResponse;
import com.serpics.jaxrs.data.ApiRestResponseStatus;
import com.serpics.membership.facade.data.AddressData;


@Path("/cartService")
@Transactional(readOnly=true)
public class CartRestServiceImpl implements CartRestService {

	@Resource
	CartFacade cartFacade;
	
    /**
     * This method returns the session car.
     * @summary  Method: getCurrentCart()
     * @return Response		object type: apiRestResponse
     * 
     */
	@Override
	@Produces(MediaType.APPLICATION_JSON)
	@GET
	@ReturnType("com.serpics.jaxrs.data.ApiRestResponse<com.serpics.commerce.facade.data.CartData>")
	public Response getCurrentCart() {
		ApiRestResponse<CartData> apiRestResponse = new ApiRestResponse<CartData>();
		CartData cartData = cartFacade.getCurrentCart();
		apiRestResponse.setStatus(ApiRestResponseStatus.OK);
		apiRestResponse.setResponseObject(cartData);
		return Response.ok(apiRestResponse).build();
	}

    /**
     * This method adds a product into current cart.
     * @summary  Method: cartAdd(String sku,int quantity )
     * @param sku Stock Keeping Unit of a item
     * @return Response		object type: apiRestResponse
     * 
     */
	@Override
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	@ReturnType("com.serpics.jaxrs.data.ApiRestResponse<com.serpics.commerce.facade.data.CartData>")
	public Response cartAdd(@FormParam("sku") String sku, @FormParam("qty") @DefaultValue("1") int quantity ) {
		Assert.notNull(sku);
		ApiRestResponse<CartModification> apiRestResponse = new ApiRestResponse<CartModification>();
		CartModification  cartItemModification = cartFacade.cartAdd(sku, quantity);
		apiRestResponse.setStatus(ApiRestResponseStatus.OK);
		apiRestResponse.setResponseObject(cartItemModification);
		return Response.ok(apiRestResponse).build();
	}

    /**
     * This method updates current cart ith a CartItemData.
     * @summary  Method: cartUpdate(CartItemData cartItem)
     * @param cartItem The cartItem to add to current cart
     * @return Response		object type: apiRestResponse
     * 
     */
	@Override
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@ReturnType("com.serpics.jaxrs.data.ApiRestResponse<com.serpics.commerce.facade.data.CartModification>")
	public Response cartUpdate(CartItemData cartItem) {
		Assert.notNull(cartItem);
		ApiRestResponse<CartModification> apiRestResponse = new ApiRestResponse<CartModification>();
		CartModification  cartItemModification = cartFacade.update(cartItem);
		apiRestResponse.setStatus(ApiRestResponseStatus.OK);
		apiRestResponse.setResponseObject(cartItemModification);
		return Response.ok(apiRestResponse).build();
	}

    /**
     * This method deletes an item.
     * @summary  Method: deleteItem(Long itemId)
     * @param itemId The brand to add
     * @return Response		object type: apiRestResponse
     * 
     */
	@Override
	@DELETE
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@ReturnType("com.serpics.jaxrs.data.ApiRestResponse<com.serpics.commerce.facade.data.CartData>")
	public Response deleteItem(@FormParam("itemId") Long itemId){
		Assert.notNull(itemId);
		ApiRestResponse<CartModification> apiRestResponse = new ApiRestResponse<CartModification>();
		CartModification  cartItemModification = cartFacade.cartItemDelete(itemId);
		apiRestResponse.setStatus(ApiRestResponseStatus.OK);
		apiRestResponse.setResponseObject(cartItemModification);
		return Response.ok(apiRestResponse).build();
	}
	
    /**
     * This method adds a billingAddress to current cart.
     * @summary  Method: addBillingAddress(AddressData billingAddress)
     * @param billingAddress The billingAddress to add
     * @return Response		object type: apiRestResponse
     * 
     */
	@Override
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@POST
	@Path("address/billing")
	@ReturnType("com.serpics.jaxrs.data.ApiRestResponse<com.serpics.commerce.facade.data.CartData>")
	public Response addBillingAddress(AddressData billingAddress){
		Assert.notNull(billingAddress);
		ApiRestResponse<CartData> apiRestResponse = new ApiRestResponse<CartData>();
		CartData cartData = cartFacade.addBillingAddress(billingAddress);
		apiRestResponse.setStatus(ApiRestResponseStatus.OK);
		apiRestResponse.setResponseObject(cartData);
		return Response.ok(apiRestResponse).build();
	}
    
    /**
     * This method adds a shippingAddress to current cart.
     * @summary  Method: addShippingAddress(AddressData shippingAddress)
     * @param shippingAddress The shippingAddress to add
     * @return Response		object type: apiRestResponse
     * 
     */
	@Override
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@POST
	@Path("address/shipping")
	@ReturnType("com.serpics.jaxrs.data.ApiRestResponse<com.serpics.commerce.facade.data.CartData>")
	public Response addShippingAddress(AddressData shippingAddress){
		Assert.notNull(shippingAddress);
		ApiRestResponse<CartData> apiRestResponse = new ApiRestResponse<CartData>();
		CartData cartData = cartFacade.addShippingAddress(shippingAddress);
		apiRestResponse.setStatus(ApiRestResponseStatus.OK);
		apiRestResponse.setResponseObject(cartData);
		return Response.ok(apiRestResponse).build();
	}

}
