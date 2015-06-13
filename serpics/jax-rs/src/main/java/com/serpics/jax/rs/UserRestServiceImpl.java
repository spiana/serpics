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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.serpics.membership.data.model.User;
import com.serpics.membership.facade.UserData;
import com.serpics.membership.facade.UserFacade;
import com.serpics.membership.services.UserService;



@Path("/userService")
@Transactional(readOnly=true)
public class UserRestServiceImpl  implements UserRestService {

    @Resource
    UserService userService;
   
    @Autowired
    UserFacade userFacade;

    @Override
    @Consumes(MediaType.APPLICATION_JSON)
    @POST
    @Path("create")
    public Response create(final User entity) {
        Assert.notNull(entity);
        userService.create(entity);

        return Response.ok().build();
    }

    @Override
    @Produces(MediaType.APPLICATION_JSON)
    @DELETE
    @Path("delete/{id}")
    public Response delete(@PathParam("id") final String id) {
        final User user = userService.findOne(new Long(id));
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
    public Response update(final User entity) {
        userService.update(entity);
        return Response.ok().build();
    }

    @Override
    @Produces(MediaType.APPLICATION_JSON)
    @GET
    @Path("find")
    public List<User> findByexample(final User example) {
        return userService.findByexample(example);
    }


    @Override
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.TEXT_HTML)
    @GET
    @Path("get/{id}")
    public User findOne(@PathParam("id") final String id)  {
        final User u =userService.findOne(new Long(id)); 
        return u;
    }

    @Override
    @Produces(MediaType.APPLICATION_JSON)
    @GET
    public Page<UserData> findAll(
            @QueryParam("page") @DefaultValue("0") final int page , @QueryParam("size" ) @DefaultValue("10") final int size){
    	
    	
    	Page<UserData> l = userFacade.findAllUser(new PageRequest(page, size));
    	
    	return l;
    	// return userService.findAll(new PageRequest(page, size));
    }



}
