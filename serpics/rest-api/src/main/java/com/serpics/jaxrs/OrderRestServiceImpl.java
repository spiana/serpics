package com.serpics.jaxrs;

import java.util.List;

import javax.annotation.Resource;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.qmino.miredot.annotations.ReturnType;
import com.serpics.commerce.facade.OrderFacade;
import com.serpics.commerce.facade.data.CartData;
import com.serpics.commerce.facade.data.OrderData;
import com.serpics.commerce.facade.data.OrderPaymentData;
import com.serpics.core.facade.AbstractPopulatingConverter;
import com.serpics.jaxrs.data.ApiRestResponse;
import com.serpics.jaxrs.data.ApiRestResponseStatus;
import com.serpics.jaxrs.data.CartDataRequest;
import com.serpics.jaxrs.data.OrderPaymentDataRequest;

@Path("/orderService")
@Transactional(readOnly = true)
public class OrderRestServiceImpl implements OrderRestService {

	@Autowired
	OrderFacade orderFacade;
	
	@Resource(name="cartDataRequestConverter")
	AbstractPopulatingConverter<CartDataRequest, CartData> cartDataRequestConverter;
	
	@Resource(name="orderPaymentDataRequestConverter")
	AbstractPopulatingConverter<OrderPaymentDataRequest, OrderPaymentData> orderPaymentDataRequestConverter;
	
	
    /**
     * This method gets current user orders.
     * @summary  Method: getOrders()
     * @return Response		object type: apiRestResponse
     */
	@Override
	@Consumes(MediaType.APPLICATION_JSON)
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@ReturnType("com.serpics.jaxrs.data.ApiRestResponse<java.util.List<com.serpics.commerce.facade.data.OrderData>>")
	public Response getOrders() {
		ApiRestResponse<List<OrderData>> apiRestResponse = new ApiRestResponse<List<OrderData>>();

		List<OrderData> listOrderData = orderFacade.getOrders();

		apiRestResponse.setStatus(ApiRestResponseStatus.OK);;
		apiRestResponse.setResponseObject(listOrderData);
		return Response.ok(apiRestResponse).build();
	}

    /**
     * This method adds payment data to an order.
     * @summary  Method: addPayment(Long orderId, OrderPaymentDataRequest orderPaymentDataRequest)
     * @param orderId The user to create
     * @param paymentData The user to create
     * @return Response		object type: apiRestResponse
     */
	@Override
	@Consumes(MediaType.APPLICATION_JSON)
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/addPayment/{orderId}")
	@ReturnType("com.serpics.jaxrs.data.ApiRestResponse<com.serpics.commerce.facade.data.OrderData>")
	public Response addPayment(@PathParam("orderId")Long orderId, OrderPaymentDataRequest orderPaymentDataRequest) {
		
		OrderPaymentData orderPaymentData = orderPaymentDataRequestConverter.convert(orderPaymentDataRequest);
		
		ApiRestResponse<OrderData> apiRestResponse = new ApiRestResponse<OrderData>();

		OrderData orderData = orderFacade.addPayment(orderId, orderPaymentData);

		apiRestResponse.setStatus(ApiRestResponseStatus.OK);;
		apiRestResponse.setResponseObject(orderData);
		return Response.ok(apiRestResponse).build();
	}
	
    /**
     * This method creates an order from a session cart.
     * @summary  Method: placeOrder()
     * @return Response		object type: apiRestResponse
     */
	@Override
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/placeOrder")
	@ReturnType("com.serpics.jaxrs.data.ApiRestResponse<com.serpics.commerce.facade.data.OrderData>")
	public Response placeOrder() {
		ApiRestResponse<OrderData> apiRestResponse = new ApiRestResponse<OrderData>();
		OrderData orderData = orderFacade.placeOrder();
		apiRestResponse.setStatus(ApiRestResponseStatus.OK);;
		apiRestResponse.setResponseObject(orderData);
		return Response.ok(apiRestResponse).build();
	}
	
    /**
     * This method creates an order from a given cart.
     * @summary  Method: createOrder(CartDataRequest cartDataRequest)
     * @param cartData The cart to turns into order
     * @return Response		object type: apiRestResponse
     */
	@Override
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/createOrder")
	@ReturnType("com.serpics.jaxrs.data.ApiRestResponse<com.serpics.commerce.facade.data.OrderData>")
	public Response createOrder(CartDataRequest cartDataRequest) {
		
		CartData cartData = cartDataRequestConverter.convert(cartDataRequest);
		
		ApiRestResponse<OrderData> apiRestResponse = new ApiRestResponse<OrderData>();
		OrderData orderData;
		orderData = orderFacade.createOrder(cartData);
		apiRestResponse.setStatus(ApiRestResponseStatus.OK);;
		apiRestResponse.setResponseObject(orderData);
		return Response.ok(apiRestResponse).build();
	}

}
