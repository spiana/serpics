package com.serpics.jaxrs;

import javax.ws.rs.core.Response;

import com.serpics.jaxrs.data.AddressDataRequest;
import com.serpics.jaxrs.data.LoginDataRequest;
import com.serpics.jaxrs.data.UserDataRequest;


public interface CustomerService  {
	
	public Response create(UserDataRequest entity, String ssid);
	public Response update(UserDataRequest entity, String ssid);
	public Response getCurrent(String ssid);
	public Response login(LoginDataRequest loginDataRequest,String ssid);

	public Response logout(String ssid);
	public Response updateContactAddress(AddressDataRequest addressDataRequest,String ssid);
	public Response updateBillingAddress(AddressDataRequest addressDataRequest,String ssid);
	public Response updateDestinationAddress(AddressDataRequest addressDataRequest,String ssid);
	public Response addDestinationAddress(AddressDataRequest addressDataRequest,String ssid);
	public Response deleteDestinationAddress(Long addressId,String ssid);

}