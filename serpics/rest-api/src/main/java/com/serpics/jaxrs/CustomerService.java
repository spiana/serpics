/*******************************************************************************
 * Copyright 2014-2016  StepFour Srl
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *  
 *      http://www.apache.org/licenses/LICENSE-2.0
 *  
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *  
 *******************************************************************************/
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