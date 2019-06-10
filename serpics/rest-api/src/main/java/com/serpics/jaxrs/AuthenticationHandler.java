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

import java.io.IOException;

import javax.annotation.Resource;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import com.serpics.base.commerce.CommerceEngine;
import com.serpics.system.web.WebCostant;

public class AuthenticationHandler implements ContainerRequestFilter {
	private static transient Logger LOG = LoggerFactory.getLogger(AuthenticationHandler.class);

	@Resource
	CommerceEngine commerceEngine;


	@Override
	@Transactional(readOnly=true)
	public void filter(ContainerRequestContext requestContext) throws IOException {
		
		String sessionId = requestContext.getHeaderString(WebCostant.SSID_SERPICS_TOKEN);
		
		if (sessionId != null){
			
			try{
				if(commerceEngine.bind(sessionId)==null){
					//FIXME in caso di sessionexpired
					//403 Forbidden La richiesta è legittima ma il server si rifiuta di soddisfarla.
					requestContext.abortWith(Response.status(403).build());
					
				}
				
			}catch(Exception e){
				requestContext.abortWith(Response.status(412).build());
				
			}
		}else{
			requestContext.abortWith(Response.status(401).build());
			
		}
		
	}
}
