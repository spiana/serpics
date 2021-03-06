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



import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.serpics.base.commerce.CommerceEngine;
import com.serpics.base.commerce.session.CommerceSessionContext;
import com.serpics.catalog.services.CatalogService;
import com.serpics.core.SerpicsException;
import com.serpics.i18n.data.repositories.LocaleRepository;
@Path("/connect/")
public class AuthenticationServiceImpl implements AuthenticationService {
	
	Logger LOG = LoggerFactory.getLogger(AuthenticationServiceImpl.class);

	@Resource
	CommerceEngine commerceEngine;

	@Resource
	CatalogService catalogService;
	
	@Autowired
	LocaleRepository localeRepository;
	
	@Context private HttpServletRequest servletRequest;
	
    /**
     * This method connects session to a store.
     * @summary  Method: connect(String store)
     * @param store The store connected
     * @return A string into body response with the Ssid
     * 
     */
	@Override
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("{store}")
	public Response connect(@PathParam("store") String store  ) {
		
		if (store == null){
			store = "default-store";
		}
		try {
			CommerceSessionContext context= internalConnect(store);
			
			return Response.ok(context.getSessionId()).build();
		} catch (SerpicsException e) {
			// TODO Auto-generated catch block
			LOG.error("Error On Connect/{Store}", e);
			return Response.status(Status.FORBIDDEN).build();
		}
		
	}
	
	private CommerceSessionContext internalConnect(String store) throws SerpicsException{
		CommerceSessionContext context= commerceEngine.connect(store);
		// Initialize the default-catalog
		catalogService.initialize();
		context.setLocale(localeRepository.findByLanguage(servletRequest.getLocale().getLanguage()));
		return context;
		
	}

}
