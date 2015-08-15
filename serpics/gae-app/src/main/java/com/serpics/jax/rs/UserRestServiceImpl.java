package com.serpics.jax.rs;

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

import com.serpics.membership.facade.UserFacade;
import com.serpics.membership.facade.data.UserData;



@Path("/customerService")
@Transactional(readOnly=true)
public class UserRestServiceImpl  implements UserRestService {
    @Autowired
    UserFacade userFacade;

    @Override
    @Consumes(MediaType.APPLICATION_JSON)
    @POST
    @Path("register")
    public Response create(final UserData user) {
        Assert.notNull(user);
        userFacade.registerUser(user);
        return Response.ok().build();
    }

    @Override
    @Produces(MediaType.APPLICATION_JSON)
    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") final String id) {
        final UserData user = userFacade.findUserById(new Long(id));
        if (user != null){
           ; // userFacade.r;
        }else
            return Response.status(Response.Status.NOT_FOUND).build();

        return Response.ok().build();
    }


    @Override
    @Consumes(MediaType.APPLICATION_JSON)
    @PUT
    public Response update(final UserData entity) {
        userFacade.updateUser(entity);
        return Response.ok().build();
    }

    @Override
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.TEXT_HTML)
    @GET
    @Path("get/{id}")
    public UserData findOne(@PathParam("id") final String id)  {
        final UserData u =userFacade.findUserById(new Long(id)); 
        return u;
    }

    @Override
    @Produces(MediaType.APPLICATION_JSON)
    @GET
    public Page<UserData> findAll(
            @QueryParam("page") @DefaultValue("0") final int page , @QueryParam("size" ) @DefaultValue("10") final int size){
    	Page<UserData> l = userFacade.findAllUser(new PageRequest(page, size));
    	
    	return l;
    }
   
}
