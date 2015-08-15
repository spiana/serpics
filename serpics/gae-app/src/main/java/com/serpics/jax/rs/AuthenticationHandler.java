package com.serpics.jax.rs;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.ws.rs.core.Response;

import org.apache.cxf.jaxrs.ext.RequestHandler;
import org.apache.cxf.jaxrs.model.ClassResourceInfo;
import org.apache.cxf.message.Message;
import org.springframework.transaction.annotation.Transactional;

import com.serpics.commerce.core.CommerceEngine;
import com.serpics.core.SerpicsException;
import com.serpics.membership.services.BaseService;

public class AuthenticationHandler implements RequestHandler {

	@Resource
	CommerceEngine commerceEngine;
	
	@Resource
	BaseService baseService;

	@Override
	@Transactional(readOnly=true)
	public Response handleRequest(final Message message, final ClassResourceInfo resourceClass) {
	
		Map<String, List<String>> headers = (Map<String, List<String>>) message.get(Message.PROTOCOL_HEADERS);
	
		if (!baseService.isInitialized())
	    	baseService.initIstance();
		
		String sessionId = null;
		
		List<String> sessionids = headers.get("ssid");
		
		if (sessionids != null && !sessionids.isEmpty()){
			sessionId = sessionids.get(0);
			commerceEngine.bind(sessionId);
		}else{
			try {
				commerceEngine.connect("default-store");
			} catch (SerpicsException e) {
				return Response.status(401).header("WWW-Authenticate", "Basic").build();
			}
		}
		
		return null;
	}

}
