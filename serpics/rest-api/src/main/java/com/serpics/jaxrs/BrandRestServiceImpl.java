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
import com.serpics.jaxrs.data.ApiRestResponse;
import com.serpics.jaxrs.data.ApiRestResponseStatus;

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
		ApiRestResponse<BrandData> apiRestResponse = new ApiRestResponse<BrandData>();

		BrandData brandData = brandFacade.addBrand(brand);

		apiRestResponse.setStatus(ApiRestResponseStatus.OK);
		apiRestResponse.setMessage("OK, brand added");
		apiRestResponse.setResponseObject(brandData);
		return Response.ok(apiRestResponse).build();

	}

	@Override
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response findAll(@QueryParam("page") @DefaultValue("0") int page,
			@QueryParam("size") @DefaultValue("10") int size) {
		
		ApiRestResponse<Page<BrandData> > apiRestResponse = new ApiRestResponse<Page<BrandData> >();
		
		apiRestResponse.setStatus(ApiRestResponseStatus.OK);
		apiRestResponse.setMessage("OK, listBrand ");
		apiRestResponse.setResponseObject( brandFacade.listBrand(new PageRequest(page, size)));
		return Response.ok(apiRestResponse).build();

	}

	@Override
	@Consumes(MediaType.APPLICATION_JSON)
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateBrand(BrandData brand) {

		Assert.notNull(brand, "brand can not be null !");
		Assert.notNull(brand.getId(), "brandId can not be null !");
		ApiRestResponse<BrandData> apiRestResponse = new ApiRestResponse<BrandData>();
		BrandData brandData = null;

		brandData = brandFacade.updateBrand(brand);

		apiRestResponse.setStatus(ApiRestResponseStatus.OK);
		apiRestResponse.setMessage("OK, brand updated");
		apiRestResponse.setResponseObject(brandData);
		return Response.ok(apiRestResponse).build();

	}

	@Override
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public Response deleteBrand(@PathParam("id") Long id) {
		
		ApiRestResponse<BrandData> apiRestResponse = new ApiRestResponse<BrandData>();

		brandFacade.deleteBrand(id);
		
		apiRestResponse.setStatus(ApiRestResponseStatus.OK);
		apiRestResponse.setMessage("OK, brand deleted");
		return Response.ok(apiRestResponse).build();
		
	}

	@Override
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/code/{id}")
	public Response findBrandById(@PathParam("id") Long id) {

		// Assert.notNull(id, "id can not be null !");
		ApiRestResponse<BrandData> apiRestResponse = new ApiRestResponse<BrandData>();
		BrandData brandData = brandFacade.findBrandById(id);
		if (brandData != null) {
			// 200 OK
			apiRestResponse.setStatus(ApiRestResponseStatus.OK);
			apiRestResponse.setMessage("OK, brand found");
			apiRestResponse.setResponseObject(brandData);
			return Response.ok(apiRestResponse).build();

		} else {
			// 404 Not Found - Brand Not Found
			apiRestResponse.setStatus(ApiRestResponseStatus.ERROR);
			apiRestResponse.setMessage("ERROR, brand not found");
			return Response.status(404).entity(apiRestResponse).build();
		}
	}

	@Override
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	// @Path("/name/{brandName : (brandName)?}")
	@Path("/name/{brandName}")
	public Response findBrandByName(@PathParam("brandName") String brandName) {

		// Assert.notNull(brandName, "name can not be null !");

		ApiRestResponse<BrandData> apiRestResponse = new ApiRestResponse<BrandData>();
		// if (brandName != null) {
		BrandData brandData = brandFacade.findBrandByName(brandName);

		if (brandData != null) {
			// 200 OK
			apiRestResponse.setStatus(ApiRestResponseStatus.OK);
			apiRestResponse.setMessage("OK, brand found");
			apiRestResponse.setResponseObject(brandData);
			return Response.ok(apiRestResponse).build();

		} else {
			// 404 Not Found - Brand Not Found
			apiRestResponse.setStatus(ApiRestResponseStatus.ERROR);
			apiRestResponse.setMessage("ERROR, brand not found");
			return Response.status(404).entity(apiRestResponse).build();
		}
		// } else {
		// // 400 Bad Request - BrandName is Null
		// apiRestResponse.setStatus(ApiRestResponseStatus.ERROR);
		// apiRestResponse.setMessage("ERROR, brandName can not be null !");
		// return Response.status(400).entity(apiRestResponse).build();
		//
		// }

	}

}