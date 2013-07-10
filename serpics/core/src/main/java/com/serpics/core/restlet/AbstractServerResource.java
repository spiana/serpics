package com.serpics.core.restlet;

import org.restlet.resource.ServerResource;
import org.springframework.beans.factory.annotation.Autowired;

import com.serpics.core.CommerceEngine;
import com.serpics.core.session.CommerceSessionContext;
import com.serpics.core.session.SessionContext;

public class AbstractServerResource extends ServerResource {
	@Autowired(required=true)
	CommerceEngine commerceEngine;
	
	protected CommerceSessionContext getSessionContext(String sessionId){
		return commerceEngine.bind(sessionId);
	}
}
