package com.serpicsframework.filter;

import info.magnolia.cms.filters.AbstractMgnlFilter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;




public class ConnectFilter extends AbstractMgnlFilter{

	@Override
	public void doFilter(HttpServletRequest requests, HttpServletResponse httpresponse,
			FilterChain chain) throws IOException, ServletException {
		
			if (requests.getSession().getAttribute("serpics.session.id") == null){
				String sessionId =  connect();
				System.out.println("create new serpics session with id " + sessionId);
				requests.getSession().setAttribute("serpics.session.id", sessionId);	
			}else{
				System.out.println("found serpics session with id " + requests.getSession().getAttribute("serpics.session.id"));	
			}
			
			chain.doFilter(requests, httpresponse);
			
	}


	private String connect() {
		Client client = ClientBuilder.newClient();
		WebTarget resource = client.target("http://localhost:8080/jax-rs/auth/connect/default-store");
	
		Builder request = resource.request();
		request.accept(MediaType.TEXT_PLAIN);
		Response response = request.get();

		
		return (String) response.readEntity(String.class);
		 
	
	}
	
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return super.isEnabled();
	}

}
