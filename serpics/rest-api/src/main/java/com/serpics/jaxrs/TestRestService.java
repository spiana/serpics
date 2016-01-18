package com.serpics.jaxrs;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.qmino.miredot.annotations.MireDotIgnore;
import com.serpics.membership.data.model.User;

@MireDotIgnore
@Path("testService")
public interface TestRestService {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.TEXT_HTML)
	@JsonSerialize(include=JsonSerialize.Inclusion.ALWAYS)
	public User ping();
	
	public Long echo(Long id);
	
}
