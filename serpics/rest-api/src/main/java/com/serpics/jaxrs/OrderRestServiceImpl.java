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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.serpics.commerce.facade.OrderFacade;
import com.serpics.commerce.facade.data.OrderData;
import com.serpics.commerce.facade.data.OrderPaymentData;
import com.serpics.jaxrs.data.ApiRestResponse;
import com.serpics.jaxrs.data.ApiRestResponseStatus;

@Path("/orderService")
@Transactional(readOnly = true)
public class OrderRestServiceImpl implements OrderRestService {

	@Autowired
	OrderFacade orderFacade;
	
	@Override
	@Consumes(MediaType.APPLICATION_JSON)
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getOrders() {
		ApiRestResponse<List<OrderData>> apiRestResponse = new ApiRestResponse<List<OrderData>>();

		List<OrderData> listOrderData = orderFacade.getOrders();

		apiRestResponse.setStatus(ApiRestResponseStatus.OK);;
		apiRestResponse.setResponseObject(listOrderData);
		return Response.ok(apiRestResponse).build();
	}

	@Override
	@Consumes(MediaType.APPLICATION_JSON)
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/addPayment/{order}")
	public Response addPayment(@PathParam("order")Long orderId, OrderPaymentData paymentData) {
		ApiRestResponse<OrderData> apiRestResponse = new ApiRestResponse<OrderData>();

		OrderData orderData = orderFacade.addPayment(orderId, paymentData);

		apiRestResponse.setStatus(ApiRestResponseStatus.OK);;
		apiRestResponse.setResponseObject(orderData);
		return Response.ok(apiRestResponse).build();
	}

}
