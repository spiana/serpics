package com.serpics.catalog.reslet;

import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.Get;
import org.springframework.beans.factory.annotation.Autowired;

import com.serpics.catalog.services.CatalogService;
import com.serpics.core.restlet.AbstractServerResource;

public class Category extends AbstractServerResource {
	@Autowired(required = true)
	CatalogService catalogService;

	@Get("xml")
	public Representation rapresentHtml(String sessionId) {

		return new StringRepresentation("");
	}
}
