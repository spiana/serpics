package com.serpics.jaxrs;

import javax.ws.rs.core.Response;

import com.serpics.jaxrs.data.AddressDataRequest;
import com.serpics.jaxrs.data.UserDataRequest;


public interface CustomerService  {
	
	public Response create(UserDataRequest entity);
	public Response update(UserDataRequest entity);
	public Response getCurrent();
	public Response login(String username , String password);
	public Response logout(String sessionId);
	public Response updateContactAddress(AddressDataRequest addressDataRequest);
	public Response updateBillingAddress(AddressDataRequest addressDataRequest);
	public Response updateDestinationAddress(AddressDataRequest addressDataRequest);
	public Response addDestinationAddress(AddressDataRequest addressDataRequest);
	public Response deleteDestinationAddress(String addressuid);

}