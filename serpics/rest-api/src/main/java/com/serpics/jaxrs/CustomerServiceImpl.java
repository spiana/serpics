package com.serpics.jaxrs;

import javax.annotation.Resource;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.serpics.commerce.core.CommerceEngine;
import com.serpics.commerce.session.CommerceSessionContext;
import com.serpics.core.SerpicsException;
import com.serpics.membership.UserType;
import com.serpics.membership.facade.UserFacade;
import com.serpics.membership.facade.data.AddressData;
import com.serpics.membership.facade.data.UserData;

@Path("/customerService")
@Transactional(readOnly = true)
public class CustomerServiceImpl implements CustomerService {
	@Autowired
	UserFacade userFacade;

	@Resource
	CommerceEngine commerceEngine;

	@Override
	@Consumes(MediaType.APPLICATION_JSON)
	@POST
	@Path("register")
	public Response create(final UserData user) {
		Assert.notNull(user);
		userFacade.registerUser(user);
		return Response.ok().build();
	}

	@Override
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public UserData getCurrent(){
		return userFacade.getCurrentuser();
	}
	
	@Override
	@Consumes(MediaType.APPLICATION_JSON)
	@PUT
	public Response update(final UserData entity) {
		if (userFacade.getCurrentuser().getUserType() != UserType.ANONYMOUS){
			userFacade.updateUser(entity);
		}
		return Response.ok().build();
	}

	@Override
	@GET
	@Path("login")
	public Response login(@QueryParam("username") String username, @QueryParam("password") String password) {
		CommerceSessionContext context = commerceEngine.getCurrentContext();
		try {
			commerceEngine.connect(context, username, password.toCharArray());
		} catch (SerpicsException e) {
			e.printStackTrace();
			return Response.status(401).build();
		}
		return Response.ok().build();
	}

	@Override
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("updateContactAddress")
	public Response updateContactAddress(AddressData address) {
		userFacade.updateContactAddress(address);
		return Response.ok().build();
	}
	@Override
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("updateBillingAddress")
	public Response updateBillingAddress(AddressData address) {
		userFacade.updateBillingAddress(address);
		return Response.ok().build();
	}

	@Override
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("updateDestinationAddress")
	public Response updateDestinationAddress(AddressData address) {
		Assert.notNull(address , "address can not be null !");
		Assert.notNull(address.getUuid() , "UUID can not ve null !");
		userFacade.updateDestinationAddress(address, address.getUuid());
		return Response.ok().build();
	}

	@Override
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("addDestinationAddress")
	public Response addDestinationAddress(AddressData address) {
		Assert.notNull(address , "address can not be null !");
		userFacade.addDestinationAddress(address);
		return Response.ok().build();
	}

	@Override
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("deleteDestinationAddress")
	public Response deleteDestinationAddress(String addressUID) {
		userFacade.deleteDestinationAddress(addressUID);
		return Response.ok().build();
	}
}
