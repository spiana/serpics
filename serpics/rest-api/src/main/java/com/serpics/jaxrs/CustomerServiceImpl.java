package com.serpics.jaxrs;

import javax.annotation.Resource;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.qmino.miredot.annotations.ReturnType;
import com.serpics.base.data.model.Store;
import com.serpics.commerce.core.CommerceEngine;
import com.serpics.commerce.session.CommerceSessionContext;
import com.serpics.commerce.strategies.CartStrategy;
import com.serpics.core.SerpicsException;
import com.serpics.core.facade.AbstractPopulatingConverter;
import com.serpics.jaxrs.data.AddressDataRequest;
import com.serpics.jaxrs.data.ApiRestResponse;
import com.serpics.jaxrs.data.ApiRestResponseStatus;
import com.serpics.jaxrs.data.UserDataRequest;
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
	
	@Resource(name="userDataRequestConverter")
	AbstractPopulatingConverter<UserDataRequest, UserData> userDataRequestConverter;
	
	@Resource(name="addressDataRequestConverter")
	AbstractPopulatingConverter<AddressDataRequest, AddressData> addressDataRequestConverter;

    /**
     * This method creates a user.
     * @summary  Method: create(UserDataRequest userDataRequest)
     * @param user The user to create
     * @return Response		object type: apiRestResponse
     */
	@Override
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@POST
	@Path("register")
	@ReturnType("com.serpics.jaxrs.data.ApiRestResponse<com.serpics.membership.facade.data.UserData>")
	public Response create(UserDataRequest userDataRequest) {
		
		UserData user = userDataRequestConverter.convert(userDataRequest);
		
		Assert.notNull(user);
		ApiRestResponse<UserData> apiRestResponse = new ApiRestResponse<UserData>();
		try {
			userFacade.registerUser(user);
		} catch (SerpicsException e) {
			apiRestResponse.setStatus(ApiRestResponseStatus.ERROR);
			apiRestResponse.setMessage("Error On register " + e.getMessage());
			return Response.status(400).entity(apiRestResponse).build();
		}
		apiRestResponse.setStatus(ApiRestResponseStatus.OK);
		return Response.ok(apiRestResponse).build();
	}

    /**
     * This method gets the current user.
     * @summary  Method: getCurrent()
     * @return Response		object type: apiRestResponse
     */
	@Override
	@GET
	@Path("getCurrent")
	@Produces(MediaType.APPLICATION_JSON)
	@ReturnType("com.serpics.jaxrs.data.ApiRestResponse<com.serpics.membership.facade.data.UserData>")
	public Response getCurrent() {

		ApiRestResponse<UserData> apiRestResponse = new ApiRestResponse<UserData>();

		apiRestResponse.setStatus(ApiRestResponseStatus.OK);
		apiRestResponse.setResponseObject(userFacade.getCurrentuser());
		return Response.ok(apiRestResponse).build();
		
	}

    /**
     * This method updates a user.
     * @summary  Method: update(UserDataRequest userDataRequest)
     * @param entity The user to update
     * @return Response		object type: apiRestResponse
     */
	@Override
	@Consumes(MediaType.APPLICATION_JSON)
	@PUT
	@ReturnType("com.serpics.jaxrs.data.ApiRestResponse<com.serpics.membership.facade.data.UserData>")
	public Response update(UserDataRequest userDataRequest) {
		
		UserData entity = userDataRequestConverter.convert(userDataRequest);
		
		if (userFacade.getCurrentuser().getUserType() != UserType.ANONYMOUS) {
			userFacade.updateUser(entity);
		}

		ApiRestResponse<UserData> apiRestResponse = new ApiRestResponse<UserData>();
		apiRestResponse.setStatus(ApiRestResponseStatus.OK);
		return Response.ok(apiRestResponse).build();
	}

    /**
     * This method makes the user login
     * @summary  Method: login(String username, String password)
     * @param username The username to login
     * @param password The password to login
     * @return Response		object type: apiRestResponse
     */
	@Override
	@GET
	@Path("login")
	@Transactional
	@ReturnType("com.serpics.jaxrs.data.ApiRestResponse<com.serpics.membership.facade.data.UserData>")
	public Response login(@QueryParam("username") String username, @QueryParam("password") String password) {

		ApiRestResponse<UserData> apiRestResponse = new ApiRestResponse<UserData>();
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
			LOG.info("try to merge UserCart "+context.getUserPrincipal().getName()+context.getStoreRealm().getId());
			cartStrategy.mergeCart((Member) context.getUserPrincipal(), (Member) context.getCustomer(), (Store) context.getStoreRealm(), context.getSessionId());
		} catch (SerpicsException e) {
			LOG.error("Error On Connect ", e);
		}
		
		apiRestResponse.setStatus(ApiRestResponseStatus.OK);
		apiRestResponse.setResponseObject(userFacade.getCurrentuser());
		return Response.ok(apiRestResponse).build();
	}

    /**
     * This method updates the address of a user
     * @summary  Method: updateContactAddress(AddressDataRequest addressDataRequest)
     * @param address The address to update
     * @return Response		object type: apiRestResponse
     */
	@Override
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("updateContactAddress")
	@ReturnType("com.serpics.jaxrs.data.ApiRestResponse<com.serpics.membership.facade.data.UserData>")
	public Response updateContactAddress(AddressDataRequest addressDataRequest) {
		
		AddressData address = addressDataRequestConverter.convert(addressDataRequest);

		ApiRestResponse<UserData> apiRestResponse = new ApiRestResponse<UserData>();

		userFacade.updateContactAddress(address);

		apiRestResponse.setStatus(ApiRestResponseStatus.OK);
		return Response.ok(apiRestResponse).build();
	}

    /**
     * This method updates the BillingAddress of a user
     * @summary  Method: updateBillingAddress(AddressDataRequest addressDataRequest)
     * @param address The BillingAddress to update
     * @return Response		object type: apiRestResponse
     */
	@Override
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("updateBillingAddress")
	@ReturnType("com.serpics.jaxrs.data.ApiRestResponse<com.serpics.membership.facade.data.UserData>")
	public Response updateBillingAddress(AddressDataRequest addressDataRequest) {
		
		AddressData address = addressDataRequestConverter.convert(addressDataRequest);

		ApiRestResponse<UserData> apiRestResponse = new ApiRestResponse<UserData>();

		userFacade.updateBillingAddress(address);

		apiRestResponse.setStatus(ApiRestResponseStatus.OK);
		return Response.ok(apiRestResponse).build();
	}

    /**
     * This method updates the DestinationAddress of a user
     * @summary  Method:  updateDestinationAddress(AddressDataRequest addressDataRequest)
     * @param address The DestinationAddress to update
     * @return Response		object type: apiRestResponse
     */
	@Override
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("updateDestinationAddress")
	@ReturnType("com.serpics.jaxrs.data.ApiRestResponse<com.serpics.membership.facade.data.UserData>")
	public Response updateDestinationAddress(AddressDataRequest addressDataRequest) {
		AddressData address = addressDataRequestConverter.convert(addressDataRequest);
		Assert.notNull(address, "address can not be null !");
		Assert.notNull(address.getUuid(), "UUID can not ve null !");
		ApiRestResponse<UserData> apiRestResponse = new ApiRestResponse<UserData>();

		userFacade.updateDestinationAddress(address, address.getUuid());

		apiRestResponse.setStatus(ApiRestResponseStatus.OK);
		return Response.ok(apiRestResponse).build();
	}

    /**
     * This method adds the DestinationAddress to a user
     * @summary  Method:  addDestinationAddress(AddressDataRequest addressDataRequest)
     * @param address The DestinationAddress to add
     * @return Response		object type: apiRestResponse
     */
	@Override
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("addDestinationAddress")
	@ReturnType("com.serpics.jaxrs.data.ApiRestResponse<com.serpics.membership.facade.data.UserData>")
	public Response addDestinationAddress(AddressDataRequest addressDataRequest) {
		
		AddressData address = addressDataRequestConverter.convert(addressDataRequest);

		Assert.notNull(address, "address can not be null !");
		ApiRestResponse<UserData> apiRestResponse = new ApiRestResponse<UserData>();

		userFacade.addDestinationAddress(address);

		apiRestResponse.setStatus(ApiRestResponseStatus.OK);
		return Response.ok(apiRestResponse).build();
	}

    /**
     * This method deletes the DestinationAddress of a user
     * @summary  Method:  deleteDestinationAddress(String addressUID)
     * @param addressUID The DestinationAddress UID to delete
     * @return Response		object type: apiRestResponse
     */
	@Override
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("deleteDestinationAddress/{addressId}")
	@ReturnType("com.serpics.jaxrs.data.ApiRestResponse<com.serpics.membership.facade.data.UserData>")
	public Response deleteDestinationAddress(@PathParam("addressId") String addressUID) {

		ApiRestResponse<UserData> apiRestResponse = new ApiRestResponse<UserData>();

		userFacade.deleteDestinationAddress(addressUID);

		apiRestResponse.setStatus(ApiRestResponseStatus.OK);
		return Response.ok(apiRestResponse).build();
	}


    /**
     * This method makes the user logout
     * @summary  Method:  logout(String sessionId)
     * @param sessionId The sessionId to logout
     * @return Response		object type: apiRestResponse
     */
	@Override
	@POST
	@Path("logout")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@ReturnType("com.serpics.jaxrs.data.ApiRestResponse<com.serpics.membership.facade.data.UserData>")
	public Response logout(String sessionId) {
		ApiRestResponse<UserData> apiRestResponse = new ApiRestResponse<UserData>();

		apiRestResponse.setStatus(ApiRestResponseStatus.OK);
		commerceEngine.disconnect(sessionId);
		//apiRestResponse.setMessage("Disconnect current user logged with session id:  " + sessionId);
		return Response.ok(apiRestResponse).build();
	}

}
