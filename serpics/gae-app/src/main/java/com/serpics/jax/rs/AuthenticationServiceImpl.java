package com.serpics.jax.rs;

import javax.annotation.Resource;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.serpics.commerce.core.CommerceEngine;
import com.serpics.commerce.session.CommerceSessionContext;
import com.serpics.core.SerpicsException;

public class AuthenticationServiceImpl implements AuthenticationService {

	@Resource
	CommerceEngine commerceEngine;
	
	@Override
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("connect/{store}")
	public String connect(@PathParam("store") String store) {
		
		if (store == null){
			store = "default-store";
		}
		try {
			CommerceSessionContext context= commerceEngine.connect(store);
			return context.getSessionId();
		} catch (SerpicsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
