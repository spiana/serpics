package com.serpics.membership.restlet;

import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;
import com.serpics.core.CommerceEngine;
import com.serpics.core.SerpicsException;
import com.serpics.core.restlet.AbstractServerResource;
import com.serpics.core.session.SessionContext;

public class Connect extends AbstractServerResource {
	@Autowired(required = true)
	CommerceEngine commerceEngine;

	class CommerceSession{
		String sessionId;
		public CommerceSession(String sessionId){
			this.sessionId = sessionId;
		}
	}
	
	@Get("json")
	public Representation makeConnecion() throws SerpicsException
	{
		String realm = (String) getRequestAttributes().get("realm");
		Gson g = new Gson();
		SessionContext sessionid= commerceEngine.connect(realm);
		return new JsonRepresentation( g.toJson(new CommerceSession(sessionid.getSessionId())));
	}

	
}
