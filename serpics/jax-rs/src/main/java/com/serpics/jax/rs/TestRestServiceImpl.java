package com.serpics.jax.rs;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

public class TestRestServiceImpl implements TestRestService {

	@Override
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String ping() {
		
		return "echo";
	}

}
