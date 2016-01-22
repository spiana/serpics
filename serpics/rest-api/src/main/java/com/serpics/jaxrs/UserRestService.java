package com.serpics.jaxrs;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.data.domain.Page;

import com.qmino.miredot.annotations.MireDotIgnore;
import com.serpics.membership.facade.data.UserData;
@MireDotIgnore
public interface UserRestService  {
	
	@Consumes(MediaType.APPLICATION_JSON)
	@POST
	
	public Response create(UserData entity);
	
	@DELETE
	@Path("{id}")
	public Response delete(@PathParam("id") String id);

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Page<UserData> findAll(@QueryParam("page") @DefaultValue("0") int page , @QueryParam("size" ) @DefaultValue("10") int size);
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(UserData entity);
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("get/{id}")
	public UserData findOne(@PathParam("id") String id);
	
	
	public String connect(@PathParam("store") String store);

	public Response logon( @QueryParam("logonid") String logonid , @QueryParam("password") String password );

}