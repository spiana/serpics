package com.serpics.jax.rs;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.serpics.membership.persistence.User;

@Path("testService")
public interface TestRestService {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.TEXT_HTML)
	@JsonSerialize(include=JsonSerialize.Inclusion.ALWAYS)
	public User ping();
	
}
