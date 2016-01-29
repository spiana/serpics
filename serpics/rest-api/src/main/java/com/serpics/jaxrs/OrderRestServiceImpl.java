package com.serpics.jaxrs;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
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
import com.serpics.commerce.facade.OrderFacade;
import com.serpics.commerce.facade.data.CartData;
import com.serpics.commerce.facade.data.OrderData;
import com.serpics.commerce.facade.data.OrderPaymentData;
import com.serpics.commerce.facade.data.ShipmodeData;
import com.serpics.jaxrs.data.ApiRestResponse;
import com.serpics.jaxrs.data.ApiRestResponseStatus;
import com.serpics.jaxrs.data.CartDataRequest;
import com.serpics.jaxrs.data.OrderPaymentDataRequest;
import com.serpics.membership.facade.data.AddressData;

@Path("/orderService")
@Transactional(readOnly = true)
public class OrderRestServiceImpl implements OrderRestService {

	Logger LOG = LoggerFactory.getLogger(OrderRestServiceImpl.class);

	@Autowired
	OrderFacade orderFacade;

	/**
	 * This method gets current user orders.
	 * 
	 * @summary Method: getOrders()
	 * @return Response object type: apiRestResponse
	 */
	@Override
	@Consumes(MediaType.APPLICATION_JSON)
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@ReturnType("com.serpics.jaxrs.data.ApiRestResponse<java.util.List<com.serpics.commerce.facade.data.OrderData>>")
	public Response getOrders() {
		ApiRestResponse<List<OrderData>> apiRestResponse = new ApiRestResponse<List<OrderData>>();

		List<OrderData> listOrderData = orderFacade.getOrders();

		apiRestResponse.setStatus(ApiRestResponseStatus.OK);
		;
		apiRestResponse.setResponseObject(listOrderData);
		return Response.ok(apiRestResponse).build();
	}

	/**
	 * This method adds payment data to an order.
	 * 
	 * @summary Method: addPayment(Long orderId, OrderPaymentDataRequest
	 *          orderPaymentDataRequest)
	 * @param orderId
	 *            The user to create
	 * @param paymentData
	 *            The user to create
	 * @return Response object type: apiRestResponse
	 */
	@Override
	@Consumes(MediaType.APPLICATION_JSON)
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/addPayment/{orderId}")
	@ReturnType("com.serpics.jaxrs.data.ApiRestResponse<com.serpics.commerce.facade.data.OrderData>")
	public Response addPayment(@PathParam("orderId") Long orderId, OrderPaymentDataRequest orderPaymentDataRequest) {
		ApiRestResponse<OrderData> apiRestResponse = new ApiRestResponse<OrderData>();
		OrderPaymentData orderPaymentData = new OrderPaymentData();
		ResponseBuilder responseBuilder = null;

		try {
			BeanUtils.copyProperties(orderPaymentDataRequest, orderPaymentData);
			Assert.notNull(orderPaymentData);
			OrderData orderData = orderFacade.addPayment(orderId, orderPaymentData);
			apiRestResponse.setStatus(ApiRestResponseStatus.OK);
			apiRestResponse.setResponseObject(orderData);
			responseBuilder = Response.ok();
		} catch (BeansException e) {
			LOG.error("Error converting bean", e);
			apiRestResponse.setStatus(ApiRestResponseStatus.ERROR);
			apiRestResponse.setMessage("Error Converting Request Bean");
			responseBuilder = Response.status(500);
		}

		return responseBuilder.entity(apiRestResponse).build();

	}

	/**
	 * This method creates an order from a session cart.
	 * 
	 * @summary Method: placeOrder()
	 * @return Response object type: apiRestResponse
	 */
	@Override
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/placeOrder")
	@ReturnType("com.serpics.jaxrs.data.ApiRestResponse<com.serpics.commerce.facade.data.OrderData>")
	public Response placeOrder() {
		ApiRestResponse<OrderData> apiRestResponse = new ApiRestResponse<OrderData>();
		OrderData orderData = orderFacade.placeOrder();
		apiRestResponse.setStatus(ApiRestResponseStatus.OK);
		;
		apiRestResponse.setResponseObject(orderData);
		return Response.ok(apiRestResponse).build();
	}


	/**
	 * This method creates an order from a given cart.
	 * 
	 * @summary Method: createOrder(CartDataRequest cartDataRequest)
	 * @param cartData
	 *            The cart to turns into order
	 * @return Response object type: apiRestResponse
	 */
	@Override
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/createOrder")
	@ReturnType("com.serpics.jaxrs.data.ApiRestResponse<com.serpics.commerce.facade.data.OrderData>")
	public Response createOrder(CartDataRequest cartDataRequest) {
		ApiRestResponse<OrderData> apiRestResponse = new ApiRestResponse<OrderData>();
		CartData cartData = new CartData();
		ResponseBuilder responseBuilder = null;
		AddressData billingAddressData = null;
		AddressData shippingAddressData = null;
		ShipmodeData shipmodeData = new ShipmodeData();

		try {
			
			BeanUtils.copyProperties(cartDataRequest, cartData,
					new String[] { "shipmodeDataName", "billingAddressDataRequest", "shippingAddressDataRequest" });

			shipmodeData.setName(cartDataRequest.getShipmodeDataName());
			BeanUtils.copyProperties(cartDataRequest.getBillingAddress(), billingAddressData);
			BeanUtils.copyProperties(cartDataRequest.getShippingAddress(), shippingAddressData);
			
			cartData.setBillingAddress(billingAddressData);
			cartData.setShippingAddress(shippingAddressData);
			cartData.setShipmode(shipmodeData);

			Assert.notNull(cartData);

			OrderData orderData = orderFacade.createOrder(cartData);

			apiRestResponse.setStatus(ApiRestResponseStatus.OK);
			apiRestResponse.setResponseObject(orderData);
			responseBuilder = Response.ok();

		} catch (BeansException e) {
			LOG.error("Error Converting Bean", e);
			apiRestResponse.setStatus(ApiRestResponseStatus.ERROR);
			apiRestResponse.setMessage("Error Converting Request Bean");
			responseBuilder = Response.status(500);
		}

		return responseBuilder.entity(apiRestResponse).build();

	}

}
