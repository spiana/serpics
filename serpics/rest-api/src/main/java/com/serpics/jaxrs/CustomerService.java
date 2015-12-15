package com.serpics.jaxrs;

import javax.ws.rs.core.Response;

import com.serpics.membership.facade.data.AddressData;
import com.serpics.membership.facade.data.UserData;


public interface CustomerService  {
	
	public Response create(UserData entity);
	public Response update(UserData entity);
	public Response getCurrent();
	public Response login(String username , String password);
	public Response logout(String sessionId);
	public Response updateContactAddress(AddressData address);
	public Response updateBillingAddress(AddressData address);
	public Response updateDestinationAddress(AddressData address);
	public Response addDestinationAddress(AddressData address);
	public Response deleteDestinationAddress(String addressuid);

}