package com.serpics.jaxrs;

import java.util.List;

import javax.annotation.Resource;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.qmino.miredot.annotations.ReturnType;
import com.serpics.base.facade.CountryFacade;
import com.serpics.base.facade.RegionFacade;
import com.serpics.commerce.facade.CartFacade;
import com.serpics.commerce.facade.data.CartData;
import com.serpics.commerce.facade.data.CartItemData;
import com.serpics.commerce.facade.data.CartModification;
import com.serpics.commerce.facade.data.PaymethodData;
import com.serpics.commerce.facade.data.ShipmodeData;
import com.serpics.jaxrs.data.AddressDataRequest;
import com.serpics.jaxrs.data.ApiRestResponse;
import com.serpics.jaxrs.data.ApiRestResponseStatus;
import com.serpics.jaxrs.data.CartItemDataRequest;
import com.serpics.jaxrs.utils.RestServiceUtils;
import com.serpics.membership.facade.data.AddressData;


@Path("/cartService")
@Transactional(readOnly=true)
public class CartRestServiceImpl implements CartRestService {
	
	Logger LOG = LoggerFactory.getLogger(CartRestServiceImpl.class);

	@Resource
	CartFacade cartFacade;
	
	@Autowired
	RegionFacade regionFacade;
	
	@Autowired
	CountryFacade countryFacade;
	
	@Autowired
	RestServiceUtils restUtils;
	
    /**
     * This method returns the session cart.
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
     * @param sku Stock Keeping Unit of an item
     * @param quantity The quantity to add into current cart
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
     * This method updates current cart with a CartItemDataRequest.
     * @summary  Method: cartUpdate(CartItemDataRequest cartItemDataRequest)
     * @param cartItemDataRequest The cartItem to add to current cart
     * @return Response		object type: apiRestResponse
     * 
     */
	@Override
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@ReturnType("com.serpics.jaxrs.data.ApiRestResponse<com.serpics.commerce.facade.data.CartModification>")
	public Response cartUpdate(CartItemDataRequest cartItemDataRequest) {
		ApiRestResponse<CartModification> apiRestResponse = new ApiRestResponse<CartModification>();
		CartItemData cartItemData = new CartItemData();
		ResponseBuilder responseBuilder = null;
		
		try{
			BeanUtils.copyProperties(cartItemDataRequest, cartItemData,new String[]{"product"});
		
			Assert.notNull(cartItemData, "cartItemData can not be null !");
			
			CartModification  cartItemModification = cartFacade.update(cartItemData);
			apiRestResponse.setStatus(ApiRestResponseStatus.OK);
			apiRestResponse.setResponseObject(cartItemModification);
			responseBuilder = Response.ok();
		}
		catch(BeansException e){
			LOG.error("Error converting bean",e);
			apiRestResponse.setStatus(ApiRestResponseStatus.ERROR);
			apiRestResponse.setMessage("Error Converting Request Bean");
			responseBuilder = Response.status(500);
		}

		return responseBuilder.entity(apiRestResponse).build();

	}

    /**
     * This method deletes an item.
     * @summary  Method: deleteItem(Long itemId)
     * @param itemId The item Id to delete
     * @return Response		object type: apiRestResponse
     * 
     */
	@Override
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
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
     * @summary  Method: addBillingAddress(AddressDataRequest billingAddressRequest)
     * @param billingAddressRequest The billingAddress to add
     * @return Response		object type: apiRestResponse
     * 
     */
	@Override
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@POST
	@Path("address/billing")
	@ReturnType("com.serpics.jaxrs.data.ApiRestResponse<com.serpics.commerce.facade.data.CartData>")
	public Response addBillingAddress(AddressDataRequest billingAddressRequest){
		
		ApiRestResponse<CartData> apiRestResponse = new ApiRestResponse<CartData>();
		AddressData billingAddress = new AddressData();
		ResponseBuilder responseBuilder = null;
		
		try{
			billingAddress = restUtils.addressDataRequestToAddressData(billingAddressRequest, billingAddress);
			Assert.notNull(billingAddress, "billingAddress can not be null !");
			CartData cartData = cartFacade.addBillingAddress(billingAddress);
			apiRestResponse.setStatus(ApiRestResponseStatus.OK);
			apiRestResponse.setResponseObject(cartData);
			responseBuilder = Response.ok();
		}
		catch(BeansException e){
			LOG.error("Error converting bean",e);
			apiRestResponse.setStatus(ApiRestResponseStatus.ERROR);
			apiRestResponse.setMessage("Error Converting Request Bean");
			responseBuilder = Response.status(500);
		}

		return responseBuilder.entity(apiRestResponse).build();

	}
    
    /**
     * This method adds a shippingAddress to current cart.
     * @summary  Method: addShippingAddress(AddressDataRequest shippingAddressRequest)
     * @param shippingAddressRequest The shippingAddress to add
     * @return Response		object type: apiRestResponse
     * 
     */
	@Override
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@POST
	@Path("address/shipping")
	@ReturnType("com.serpics.jaxrs.data.ApiRestResponse<com.serpics.commerce.facade.data.CartData>")
	public Response addShippingAddress(AddressDataRequest shippingAddressRequest){
		
		ApiRestResponse<CartData> apiRestResponse = new ApiRestResponse<CartData>();
		AddressData shippingAddress = new AddressData();
		ResponseBuilder responseBuilder = null;
		
		try{
			shippingAddress = restUtils.addressDataRequestToAddressData(shippingAddressRequest, shippingAddress);
			Assert.notNull(shippingAddress, "shippingAddress can not be null !");
			CartData cartData = cartFacade.addShippingAddress(shippingAddress);
			apiRestResponse.setStatus(ApiRestResponseStatus.OK);
			apiRestResponse.setResponseObject(cartData);
			responseBuilder = Response.ok();
		}
		catch(BeansException e){
			LOG.error("Error converting bean",e);
			apiRestResponse.setStatus(ApiRestResponseStatus.ERROR);
			apiRestResponse.setMessage("Error Converting Request Bean");
			responseBuilder = Response.status(500);
		}

		return responseBuilder.entity(apiRestResponse).build();

	}

	
    /**
     * This method adds a shipmode to current cart.
     * @summary  Method: addShipmode(String shipmodeName)
     * @param shipmodeName The shipmode name to add
     * @return Response		object type: apiRestResponse
     * 
     */
	@Override
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@POST
	@Path("/shipmode/{shipmodeName}")
	@ReturnType("com.serpics.jaxrs.data.ApiRestResponse<com.serpics.commerce.facade.data.CartData>")
	public Response addShipmode(@PathParam("shipmodeName") String shipmodeName){
		Assert.notNull(shipmodeName);
		ApiRestResponse<CartData> apiRestResponse = new ApiRestResponse<CartData>();
		CartData cartData = cartFacade.addShipmode(shipmodeName);
		apiRestResponse.setStatus(ApiRestResponseStatus.OK);
		apiRestResponse.setResponseObject(cartData);
		return Response.ok(apiRestResponse).build();
	}

    /**
     * This method finds all shipmodes available for current cart, the response is a list of shipmode.
     * @summary  Method: getShipmodeList()
     * @return Response		object type: apiRestResponse
     */
	@Override
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@GET
	@Path("/shipmode")
	@ReturnType("com.serpics.jaxrs.data.ApiRestResponse<java.util.List<com.serpics.commerce.facade.data.ShipmodeData>>")
	public Response getShipmodeList(){
		ApiRestResponse<List<ShipmodeData>> apiRestResponse = new ApiRestResponse<List<ShipmodeData>>();
		apiRestResponse.setStatus(ApiRestResponseStatus.OK);
		apiRestResponse.setResponseObject(cartFacade.getShipmodeList());
		return Response.ok(apiRestResponse).build();
	}
	
    /**
     * This method finds all paymethods available for current store, the response is a list of paymethod.
     * @summary  Method: getPaymethodList()
     * @return Response		object type: apiRestResponse
     */
	@Override
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@GET
	@Path("/paymethod")
	@ReturnType("com.serpics.jaxrs.data.ApiRestResponse<java.util.List<com.serpics.commerce.facade.data.PaymethodData>>")
	public Response getPaymethodList(){
		ApiRestResponse<List<PaymethodData>> apiRestResponse = new ApiRestResponse<List<PaymethodData>>();
		apiRestResponse.setStatus(ApiRestResponseStatus.OK);
		apiRestResponse.setResponseObject(cartFacade.getPaymethodList());
		return Response.ok(apiRestResponse).build();
	}
	
    /**
     * This method adds a paymethod into current cart.
     * @summary  Method: addPaymethod(Long paymethodId)
     * @param paymethodName The paymethod Name to add
     * @return Response		object type: apiRestResponse
     * 
     */
	@Override
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@POST
	@Path("/paymethod/{paymethodName}")
	@ReturnType("com.serpics.jaxrs.data.ApiRestResponse<com.serpics.commerce.facade.data.CartData>")
	public Response addPaymethod(@PathParam("paymethodName") String paymethodName){
		Assert.notNull(paymethodName);
		ApiRestResponse<CartData> apiRestResponse = new ApiRestResponse<CartData>();
		CartData cartData = cartFacade.addPaymethod(paymethodName);
		apiRestResponse.setStatus(ApiRestResponseStatus.OK);
		apiRestResponse.setResponseObject(cartData);
		return Response.ok(apiRestResponse).build();
	}
	
    /**
     * This method delete the session cart and return a new session cart.
     * @summary  Method: deleteCart()
     * @return Response		object type: apiRestResponse
     * 
     */
	@Override
	@Produces(MediaType.APPLICATION_JSON)
	@DELETE
	@Path("/cart")
	@ReturnType("com.serpics.jaxrs.data.ApiRestResponse<com.serpics.commerce.facade.data.CartData>")
	public Response deleteCart() {
		ApiRestResponse<CartData> apiRestResponse = new ApiRestResponse<CartData>();
		CartData cartData = cartFacade.deleteCart();
		apiRestResponse.setStatus(ApiRestResponseStatus.OK);
		apiRestResponse.setResponseObject(cartData);
		return Response.ok(apiRestResponse).build();
	}

}
