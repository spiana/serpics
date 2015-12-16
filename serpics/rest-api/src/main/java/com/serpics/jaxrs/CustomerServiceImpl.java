package com.serpics.jaxrs;

import javax.annotation.Resource;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.serpics.commerce.core.CommerceEngine;
import com.serpics.commerce.data.model.Cart;
import com.serpics.commerce.session.CommerceSessionContext;
import com.serpics.commerce.strategies.CartStrategy;
import com.serpics.core.SerpicsException;
import com.serpics.jaxrs.data.ApiRestResponse;
import com.serpics.jaxrs.data.ApiRestResponseStatus;
import com.serpics.membership.UserType;
import com.serpics.membership.data.model.Member;
import com.serpics.membership.facade.UserFacade;
import com.serpics.membership.facade.data.AddressData;
import com.serpics.membership.facade.data.UserData;

@Path("/customerService")
@Transactional(readOnly = true)
public class CustomerServiceImpl implements CustomerService {

	Logger LOG = LoggerFactory.getLogger(CustomerServiceImpl.class);
	@Autowired
	UserFacade userFacade;

	@Resource
	CommerceEngine commerceEngine;

	@Autowired
	CartStrategy cartStrategy;

	@Override
	@Consumes(MediaType.APPLICATION_JSON)
	@POST
	@Path("register")
	public Response create(final UserData user) {
		Assert.notNull(user);
		ApiRestResponse<UserData> apiRestResponse = new ApiRestResponse<UserData>();
		userFacade.registerUser(user);
		apiRestResponse.setStatus(ApiRestResponseStatus.OK);
		return Response.ok(apiRestResponse).build();
	}

	@Override
	@GET
	@Path("getCurrent")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCurrent() {

		ApiRestResponse<UserData> apiRestResponse = new ApiRestResponse<UserData>();

		apiRestResponse.setStatus(ApiRestResponseStatus.OK);
		apiRestResponse.setResponseObject(userFacade.getCurrentuser());
		return Response.ok(apiRestResponse).build();
		
	}

	@Override
	@Consumes(MediaType.APPLICATION_JSON)
	@PUT
	public Response update(final UserData entity) {
		if (userFacade.getCurrentuser().getUserType() != UserType.ANONYMOUS) {
			userFacade.updateUser(entity);
		}

		ApiRestResponse<UserData> apiRestResponse = new ApiRestResponse<UserData>();
		apiRestResponse.setStatus(ApiRestResponseStatus.OK);
		return Response.ok(apiRestResponse).build();
	}

	@Override
	@GET
	@Path("login")
	public Response login(@QueryParam("username") String username, @QueryParam("password") String password) {

		ApiRestResponse<Cart> apiRestResponse = new ApiRestResponse<Cart>();
		CommerceSessionContext context = commerceEngine.getCurrentContext();
		try {
			commerceEngine.connect(context, username, password.toCharArray());
		} catch (SerpicsException e) {
			LOG.error("Error On Connect ", e);
			apiRestResponse.setStatus(ApiRestResponseStatus.ERROR);
			apiRestResponse.setMessage("Error On Connect " + e.getMessage());
			return Response.status(401).entity(apiRestResponse).build();
		}		

		// Verificare se è necessario restituire il carrello
		try {
			cartStrategy.mergeCart((Member) context.getUserPrincipal(), (Member) context.getCustomer());
		} catch (SerpicsException e) {
			LOG.error("Error On Connect ", e);
			apiRestResponse.setStatus(ApiRestResponseStatus.ERROR);
			apiRestResponse.setMessage("Error On Connect " + e.getMessage());
			return Response.status(501).entity(apiRestResponse).build();
		}
		
		apiRestResponse.setStatus(ApiRestResponseStatus.OK);
		return Response.ok(apiRestResponse).build();
	}

	@Override
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("updateContactAddress")
	public Response updateContactAddress(AddressData address) {

		ApiRestResponse<UserData> apiRestResponse = new ApiRestResponse<UserData>();

		userFacade.updateContactAddress(address);

		apiRestResponse.setStatus(ApiRestResponseStatus.OK);
		return Response.ok(apiRestResponse).build();
	}

	@Override
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("updateBillingAddress")
	public Response updateBillingAddress(AddressData address) {

		ApiRestResponse<UserData> apiRestResponse = new ApiRestResponse<UserData>();

		userFacade.updateBillingAddress(address);

		apiRestResponse.setStatus(ApiRestResponseStatus.OK);
		return Response.ok(apiRestResponse).build();
	}

	@Override
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("updateDestinationAddress")
	public Response updateDestinationAddress(AddressData address) {
		Assert.notNull(address, "address can not be null !");
		Assert.notNull(address.getUuid(), "UUID can not ve null !");
		ApiRestResponse<UserData> apiRestResponse = new ApiRestResponse<UserData>();

		userFacade.updateDestinationAddress(address, address.getUuid());

		apiRestResponse.setStatus(ApiRestResponseStatus.OK);
		return Response.ok(apiRestResponse).build();
	}

	@Override
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("addDestinationAddress")
	public Response addDestinationAddress(AddressData address) {

		Assert.notNull(address, "address can not be null !");
		ApiRestResponse<UserData> apiRestResponse = new ApiRestResponse<UserData>();

		userFacade.addDestinationAddress(address);

		apiRestResponse.setStatus(ApiRestResponseStatus.OK);
		return Response.ok(apiRestResponse).build();
	}

	@Override
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("deleteDestinationAddress")
	public Response deleteDestinationAddress(String addressUID) {

		ApiRestResponse<UserData> apiRestResponse = new ApiRestResponse<UserData>();

		userFacade.deleteDestinationAddress(addressUID);

		apiRestResponse.setStatus(ApiRestResponseStatus.OK);
		return Response.ok(apiRestResponse).build();
	}


	@Override
	@POST
	@Path("logout")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response logout(String sessionId) {
		ApiRestResponse<UserData> apiRestResponse = new ApiRestResponse<UserData>();

		apiRestResponse.setStatus(ApiRestResponseStatus.OK);
		commerceEngine.disconnect(sessionId);
		apiRestResponse.setMessage("Disconnect current user logged with session id:  " + sessionId);
		apiRestResponse.setResponseObject(userFacade.getCurrentuser());
		return Response.ok(apiRestResponse).build();
	}

}
