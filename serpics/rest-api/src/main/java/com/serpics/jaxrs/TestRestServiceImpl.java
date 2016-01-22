package com.serpics.jaxrs;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.qmino.miredot.annotations.MireDotIgnore;
import com.serpics.membership.data.model.User;
@MireDotIgnore
public class TestRestServiceImpl implements TestRestService {

	@Override
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.TEXT_HTML)
	@JsonSerialize(include=JsonSerialize.Inclusion.ALWAYS)
	public User ping() {
		User u = new User();
		
		return u;
	}

	@Override
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.TEXT_HTML)
	@Path("{id}")
	public Long echo(@PathParam("id")   Long id) {
		// TODO Auto-generated method stub
		return id;
	}

}
