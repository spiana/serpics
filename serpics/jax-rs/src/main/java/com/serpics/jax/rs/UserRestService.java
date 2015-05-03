package com.serpics.jax.rs;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.serpics.core.SerpicsException;
import com.serpics.membership.data.model.User;


public interface UserRestService extends AbstractRestService<User, Long> {


}