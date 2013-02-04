package com.serpics.catalog.reslet;

import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.Get;
import org.springframework.beans.factory.annotation.Autowired;

import com.serpics.core.restlet.AbstractServerResource;
import com.serpics.membership.services.MembershipService;

public class Category extends AbstractServerResource{
	@Autowired(required = true)
	MembershipService catalogService;
	
	@Get("xml")
	public Representation rapresentHtml(String sessionId ){
		
		
		return new StringRepresentation("");
	}
}
