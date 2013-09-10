package com.serpics.jax.rs;

import java.util.List;

import javax.annotation.Resource;
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
import org.springframework.data.domain.PageRequest;
import org.springframework.util.Assert;

import com.serpics.membership.persistence.PermanentAddress;
import com.serpics.membership.persistence.User;
import com.serpics.membership.persistence.UsersReg;
import com.serpics.membership.services.UserService;


@Path("/userService")
public class UserRestServiceImpl  implements UserRestService {

	@Resource
	UserService userService;

	@Override
	@Consumes(MediaType.APPLICATION_JSON)
	@POST
	@Path("create")
	public Response create(User entity) {
		Assert.notNull(entity);
		if (entity.getUserReg() != null){
			UsersReg reg = entity.getUserReg();
			entity.setUserReg(null);
			userService.registerUser(entity, reg, null);
		}else
			userService.create(entity);
		
		return Response.ok().build();
	}

	@Override
	@Produces(MediaType.APPLICATION_JSON)
	@DELETE
	@Path("delete/{id}")
	public Response delete(@PathParam("id") Long id) {
		User user = userService.findOne(id);
		if (user != null){
			userService.delete(user);
		}else
			return Response.status(Response.Status.NOT_FOUND).build();
		
		return Response.ok().build();
	}

	
	@Override
	@Consumes(MediaType.APPLICATION_JSON)
	@PUT
	@Path("update")
	public Response update(User entity) {
		userService.update(entity);
		return Response.ok().build();
	}

	@Override
	@Produces(MediaType.APPLICATION_JSON)
	@GET
	@Path("find")
	public List<User> findByexample(User example) {
		return userService.findByexample(example);
	}


	@Override
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.TEXT_HTML)
	@GET
	@Path("get/{id}")
	public User findOne(@PathParam("id") Long id)  {
		User u =userService.findOne(id); 
		return u;
	}

	@Override
	@Produces(MediaType.APPLICATION_JSON)
	@GET
	public Page<User> findAll(
			@QueryParam("page") @DefaultValue("0") int page , @QueryParam("size" ) @DefaultValue("10") int size){
		return userService.findAll(new PageRequest(page, size));
	}

	

}
