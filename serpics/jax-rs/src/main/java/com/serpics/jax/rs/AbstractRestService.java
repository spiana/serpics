package com.serpics.jax.rs;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestParam;

public interface AbstractRestService<T , ID> {
	@POST
	public Response create(T entity);
	
	@DELETE
	@Path("/{id}")
	public Response delete(@PathParam("id") ID id);

	@GET
	public Page<T> findAll(Pageable page);
	
	@PUT
	public Response update(T entity);
	public List<T> findByexample(T example);
	
	@GET
	@Path("/{id}")
	public T findOne(@PathParam("id") ID id);
}
