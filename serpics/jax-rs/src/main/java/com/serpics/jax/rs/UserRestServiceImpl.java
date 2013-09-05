package com.serpics.jax.rs;

import java.util.List;

import javax.annotation.Resource;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.serpics.core.CommerceEngine;
import com.serpics.core.SerpicsException;
import com.serpics.membership.persistence.User;
import com.serpics.membership.services.BaseService;
import com.serpics.membership.services.UserService;


@Path("/userService")

public class UserRestServiceImpl  {

	@Resource
	UserService userService;
	
	@Resource
	CommerceEngine commerceEngine ;

	@Resource
	BaseService baseService;
	
//	@Override
//	@POST
//	public Response create(User entity) {
//		userService.create(entity);
//		return Response.ok().build();
//	}
//
//	@Override
//	@DELETE
//	@Path("/{id}")
//	public Response delete(@PathParam("id") Long id) {
//		User user = userService.findOne(id);
//		if (user != null){
//			userService.delete(user);
//		}else
//			return Response.status(Response.Status.NOT_FOUND).build();
//		
//		return Response.ok().build();
//	}
//
//	@Override
//	@Produces(MediaType.APPLICATION_JSON)
//	public Page<User> findAll(Pageable page) {
//		return userService.findAll(page);
//	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional(readOnly=false , propagation= Propagation.REQUIRES_NEW)
	public List<User> findAll() throws SerpicsException {
		if (!baseService.isInitialized())
			baseService.initIstance();
		
		commerceEngine.connect("default-store", "superuser", "admin".toCharArray());
		
		List<User> l = userService.findAll();
		
		return l;
	}
	
//	@Override
//	@PUT
//	public Response update(User entity) {
//		userService.update(entity);
//		
//		return Response.ok().build();
//	}
//
//	@Override
//	public List<User> findByexample(User example) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional(readOnly=true , propagation= Propagation.REQUIRED)
	public User findOne(@PathParam("id") Long id) throws SerpicsException {
		//commerceEngine.connect("default-store", "superuser", "admin".toCharArray());
		User u =userService.findOne(id); 
		u.getMemberAttributes().size();
		
		
		return u;
	}

	

}
