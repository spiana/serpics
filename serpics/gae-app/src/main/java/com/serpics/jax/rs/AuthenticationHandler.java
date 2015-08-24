package com.serpics.jax.rs;

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

public class AuthenticationHandler implements RequestHandler {
	private static transient Logger LOG = LoggerFactory.getLogger(AuthenticationHandler.class);

	@Resource
	CommerceEngine commerceEngine;
	
	@Override
	@Transactional(readOnly=true)
	public Response handleRequest(final Message message, final ClassResourceInfo resourceClass) {
	
		Map<String, List<String>> headers = (Map<String, List<String>>) message.get(Message.PROTOCOL_HEADERS);
	
		String uri = (	String) message.get(Message.REQUEST_URI);
		
		LOG.info("uri {}" , uri);
		
		String sessionId = null;
		List<String> sessionids = headers.get("ssid");
		
		if (sessionids != null && !sessionids.isEmpty()){
			sessionId = sessionids.get(0);
			try{
				commerceEngine.bind(sessionId);
			}catch(Exception e){
				return Response.status(412).build();
			}
		}else{
			return Response.status(401).build();
			
		}
		
		return null;
	}

}
