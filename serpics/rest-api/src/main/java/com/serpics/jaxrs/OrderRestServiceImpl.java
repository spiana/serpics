package com.serpics.jaxrs;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
//@Transactional(readOnly = true)
public class OrderRestServiceImpl implements OrderRestService {

	Logger LOG = LoggerFactory.getLogger(OrderRestServiceImpl.class);

	@Autowired
	OrderFacade orderFacade;

	/**
	 * This method gets current user orders.
	 * 
	 * @param page
	 *            number of page requested
	 * @param size
	 *            number of orders to display into page
	 * @param ssid
	 *            The sessionId for the store authentication
	 * @summary Method: getOrders()
	 * @return Response object type: apiRestResponse
	 */
	@Override
	@Consumes(MediaType.APPLICATION_JSON)
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@ReturnType("com.serpics.jaxrs.data.ApiRestResponse<org.springframework.data.domain.Page<com.serpics.commerce.facade.data.OrderData>>")
	public Response getOrders(@QueryParam("page") @DefaultValue("0") int page,
			@QueryParam("size") @DefaultValue("10") int size, @HeaderParam(value = "ssid") String ssid) {

		ApiRestResponse<Page<OrderData>> apiRestResponse = new ApiRestResponse<Page<OrderData>>();

		apiRestResponse.setStatus(ApiRestResponseStatus.OK);
		apiRestResponse.setResponseObject(orderFacade.getPagedOrders(new PageRequest(page, size)));
		return Response.ok(apiRestResponse).build();
	}

	/**
	 * This method adds payment data to an order.
	 * 
	 * @summary Method: addPayment(Long orderId, OrderPaymentDataRequest
	 *          orderPaymentDataRequest)
	 * @param orderId
	 *            The order id to add payment
	 * @param orderPaymentDataRequest
	 *            The paymentData to add
	 * @param ssid
	 *            The sessionId for the store authentication
	 * @return Response object type: apiRestResponse
	 */
	@Override
	@Consumes(MediaType.APPLICATION_JSON)
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/addPayment/{orderId}")
	@ReturnType("com.serpics.jaxrs.data.ApiRestResponse<com.serpics.commerce.facade.data.OrderData>")
	public Response addPayment(@PathParam("orderId") Long orderId, OrderPaymentDataRequest orderPaymentDataRequest,
			@HeaderParam(value = "ssid") String ssid) {
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
	 * @summary Method: placeOrder(String ssid)
	 * @param ssid
	 *            The sessionId for the store authentication
	 * @return Response object type: apiRestResponse
	 */
	@Override
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/placeOrder")
	@ReturnType("com.serpics.jaxrs.data.ApiRestResponse<com.serpics.commerce.facade.data.OrderData>")
	public Response placeOrder(@HeaderParam(value = "ssid") String ssid) {
		ApiRestResponse<OrderData> apiRestResponse = new ApiRestResponse<OrderData>();

		OrderData orderData = orderFacade.placeOrder();
		apiRestResponse.setStatus(ApiRestResponseStatus.OK);
		apiRestResponse.setResponseObject(orderData);
		return Response.ok(apiRestResponse).build();
	}

	/**
	 * This method creates an order from a given cart.
	 * 
	 * @summary Method: createOrder(CartDataRequest cartDataRequest,String ssid)
	 * @param cartDataRequest
	 *            The cart to turns into order
	 * @param ssid
	 *            The sessionId for the store authentication
	 * @return Response object type: apiRestResponse
	 */
	@Override
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/createOrder")
	@ReturnType("com.serpics.jaxrs.data.ApiRestResponse<com.serpics.commerce.facade.data.OrderData>")
	public Response createOrder(CartDataRequest cartDataRequest, @HeaderParam(value = "ssid") String ssid) {
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
