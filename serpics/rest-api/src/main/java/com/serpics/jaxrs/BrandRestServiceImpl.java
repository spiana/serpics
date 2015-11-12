package com.serpics.jaxrs;

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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.serpics.catalog.facade.BrandFacade;
import com.serpics.catalog.facade.data.BrandData;

@Path("/brandService")
@Transactional(readOnly = true)
public class BrandRestServiceImpl implements BrandRestService {

	@Resource
	BrandFacade brandFacade;

	@Override
	@Consumes(MediaType.APPLICATION_JSON)
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response addBrand(BrandData brand) {

		Assert.notNull(brand, "brand can not be null !");
		BrandData brandData = brandFacade.addBrand(brand);
		return Response.ok(brandData).build();

	}

	@Override
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Page<BrandData> findAll(@QueryParam("page") @DefaultValue("0") int page,
			@QueryParam("size") @DefaultValue("10") int size) {

		return brandFacade.listBrand(new PageRequest(page, size));

	}

	@Override
	@Consumes(MediaType.APPLICATION_JSON)
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateBrand(BrandData brand) {

		Assert.notNull(brand, "brand can not be null !");
		Assert.notNull(brand.getId(), "brandId can not be null !");
		BrandData brandData = null;
		brandData = brandFacade.updateBrand(brand);
		return Response.ok(brandData).build();
	}

	@Override
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public Response deleteBrand(@PathParam("id") Long id) {

		brandFacade.deleteBrand(id);
		return Response.ok().build();
	}

	@Override
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public BrandData findBrandById(@PathParam("id") Long id) {

		Assert.notNull(id, "id can not be null !");
		return brandFacade.findBrandById(id);

	}

	@Override
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/name/{name}")
	public BrandData findBrandByName(@PathParam("name") String name) {

		Assert.notNull(name,"name can not be null !");
		return brandFacade.findBrandByName(name);

	}

}
