package com.serpics.jax.rs;

import java.util.List;

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

public interface AbstractRestService<T , ID> {
	
	@Consumes(MediaType.APPLICATION_JSON)
	@POST
	@Path("create")
	public Response create(T entity);
	
	@DELETE
	@Path("delete/{id}")
	public Response delete(@PathParam("id") ID id);

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Page<T> findAll(@QueryParam("page") @DefaultValue("0") int page , @QueryParam("size" ) @DefaultValue("10") int size);
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("update")
	public Response update(T entity);
	
	@GET
	@Path("find")
	@Produces(MediaType.APPLICATION_JSON)
	public List<T> findByexample(T example);
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("get/{id}")
	public T findOne(@PathParam("id") ID id);
}
