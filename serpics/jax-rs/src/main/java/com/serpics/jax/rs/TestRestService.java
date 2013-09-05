package com.serpics.jax.rs;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("testService")
public interface TestRestService {

	@GET
	public String ping();
	
}
