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

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.ws.rs.core.Response;

import org.apache.cxf.jaxrs.ext.RequestHandler;
import org.apache.cxf.jaxrs.model.ClassResourceInfo;
import org.apache.cxf.message.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import com.serpics.commerce.core.CommerceEngine;
import com.serpics.system.web.WebCostant;

public class AuthenticationHandler implements RequestHandler {
	private static transient Logger LOG = LoggerFactory.getLogger(AuthenticationHandler.class);

	@Resource
	CommerceEngine commerceEngine;
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly=true)
	public Response handleRequest(final Message message, final ClassResourceInfo resourceClass) {
	
		Map<String, List<String>> headers = (Map<String, List<String>>) message.get(Message.PROTOCOL_HEADERS);
	
		String uri = (	String) message.get(Message.REQUEST_URI);
		
		LOG.info("uri {}" , uri);
		
		String sessionId = null;
		List<String> sessionids = headers.get(WebCostant.SSID_SERPICS_TOKEN);
		
		if (sessionids != null && !sessionids.isEmpty()){
			sessionId = sessionids.get(0);
			try{
				if(commerceEngine.bind(sessionId)==null){
					//FIXME in caso di sessionexpired
					//403 Forbidden La richiesta è legittima ma il server si rifiuta di soddisfarla.
					return Response.status(403).build();
				}
				
			}catch(Exception e){
				return Response.status(412).build();
			}
		}else{
			return Response.status(401).build();
			
		}
		
		return null;
	}

}
