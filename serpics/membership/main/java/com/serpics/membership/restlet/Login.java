package com.serpics.membership.restlet;

import org.restlet.data.Form;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;
import com.serpics.core.CommerceEngine;
import com.serpics.core.SerpicsException;
import com.serpics.core.restlet.AbstractServerResource;
import com.serpics.core.session.SessionContext;

public class Login extends AbstractServerResource {
	@Autowired(required = true)
	CommerceEngine commerceEngine;

	@Get
	public Representation connect(Representation entity) throws SerpicsException{
		Gson g = new Gson();
		String realm = (String) getRequestAttributes().get("realm");
		
		Form form = new Form(entity);
		String loginId = form.getFirstValue("logonid");
		String password = form.getFirstValue("password");
		
		SessionContext sessionid= commerceEngine.connect(realm, loginId, password.toCharArray());
		return new JsonRepresentation( g.toJson(sessionid.getSessionId()));
	}
}
