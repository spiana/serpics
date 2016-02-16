package com.serpics.jaxrs;

import java.util.List;

import javax.annotation.Resource;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.qmino.miredot.annotations.ReturnType;
import com.serpics.catalog.facade.BrandFacade;
import com.serpics.catalog.facade.data.BrandData;
import com.serpics.jaxrs.data.ApiRestResponse;
import com.serpics.jaxrs.data.ApiRestResponseStatus;
import com.serpics.jaxrs.data.BrandDataRequest;


@Path("/brandService")
@Transactional(readOnly = true)
public class BrandRestServiceImpl implements BrandRestService {
	
	Logger LOG = LoggerFactory.getLogger(CustomerServiceImpl.class);

	@Resource
	BrandFacade brandFacade;

	
    /**
     * This method adds a brand to catalog.
     * @summary  Method: addBrand(BrandDataRequest brandDataRequest)
     * @param brandDataRequest The brand to add
     * @param ssid The sessionId for the store authentication
     * @return Response		object type: apiRestResponse
     * 
     */
	@Override
	@Consumes(MediaType.APPLICATION_JSON)
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@ReturnType("com.serpics.jaxrs.data.ApiRestResponse<com.serpics.catalog.facade.data.BrandData>")
	public Response addBrand(BrandDataRequest brandDataRequest, @HeaderParam(value = "ssid") String ssid ) {
		
		ApiRestResponse<BrandData> apiRestResponse = new ApiRestResponse<BrandData>();
		BrandData brandData = new BrandData();
		ResponseBuilder responseBuilder = null;
		
		try{
			BeanUtils.copyProperties(brandDataRequest, brandData);
			Assert.notNull(brandData, "brand can not be null !");
			BrandData brandDataAdded = brandFacade.addBrand(brandData);
			apiRestResponse.setStatus(ApiRestResponseStatus.OK);
			apiRestResponse.setResponseObject(brandDataAdded);
			responseBuilder = Response.ok();
		}
		catch(BeansException e){
			LOG.error("Error converting bean",e);
			apiRestResponse.setStatus(ApiRestResponseStatus.ERROR);
			apiRestResponse.setMessage("Error Converting Request Bean");
			responseBuilder = Response.status(500);
		}

		return responseBuilder.entity(apiRestResponse).build();

	}

    /**
     * This method finds all brands into catalog, the response is a pageable list of brands.
     * @summary  Method: findAll(page,size)
     * @param page number of page requested
     * @param size number of brand to display into page
     * @param ssid The sessionId for the store authentication
     * @return Response		object type: apiRestResponse
     */
	@Override
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@ReturnType("com.serpics.jaxrs.data.ApiRestResponse<org.springframework.data.domain.Page<com.serpics.catalog.facade.data.BrandData>>")
	public Response findAll(@QueryParam("page") @DefaultValue("0") int page,
			@QueryParam("size") @DefaultValue("10") int size, @HeaderParam(value = "ssid") String ssid ) {
		
		ApiRestResponse<Page<BrandData> > apiRestResponse = new ApiRestResponse<Page<BrandData> >();
		
		apiRestResponse.setStatus(ApiRestResponseStatus.OK);
		apiRestResponse.setResponseObject( brandFacade.pageBrand(new PageRequest(page, size)));
		return Response.ok(apiRestResponse).build();

	}
	
    /**
     * This method finds all brands into catalog, the response is a list of brands.
     * @summary  Method: findAllList()
     * @return Response		object type: apiRestResponse
     */
	@Override
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/list")
	@ReturnType("com.serpics.jaxrs.data.ApiRestResponse<java.util.List<com.serpics.catalog.facade.data.BrandData>>")
	public Response findAllList( @HeaderParam(value = "ssid") String ssid ) {
		
		ApiRestResponse<List<BrandData>> apiRestResponse = new ApiRestResponse<List<BrandData>>();
		
		apiRestResponse.setStatus(ApiRestResponseStatus.OK);
		apiRestResponse.setResponseObject( brandFacade.listBrand());
		return Response.ok(apiRestResponse).build();

	}

    /**
     * This method updates a brand.
     * @summary  Method: updateBrand(BrandDataRequest brandDataRequest)
     * @param brandDataRequest The brand to update
     * @param ssid The sessionId for the store authentication
     * @return Response		object type: apiRestResponse
     */
	@Override
	@Consumes(MediaType.APPLICATION_JSON)
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@ReturnType("com.serpics.jaxrs.data.ApiRestResponse<com.serpics.catalog.facade.data.BrandData>")
	public Response updateBrand(BrandDataRequest brandDataRequest, @HeaderParam(value = "ssid") String ssid ) {
		ApiRestResponse<BrandData> apiRestResponse = new ApiRestResponse<BrandData>();
		BrandData brandData = new BrandData();
		ResponseBuilder responseBuilder = null;
		
		try{
			BeanUtils.copyProperties(brandDataRequest, brandData);
			Assert.notNull(brandData, "brand can not be null !");
			Assert.notNull(brandData.getId(), "brandId can not be null !");
			BrandData brandDataAdded = brandFacade.updateBrand(brandData);
			apiRestResponse.setStatus(ApiRestResponseStatus.OK);
			apiRestResponse.setResponseObject(brandDataAdded);
			responseBuilder = Response.ok();
		}
		catch(BeansException e){
			LOG.error("Error converting bean",e);
			apiRestResponse.setStatus(ApiRestResponseStatus.ERROR);
			apiRestResponse.setMessage("Error Converting Request Bean");
			responseBuilder = Response.status(500);
		}

		return responseBuilder.entity(apiRestResponse).build();

	}

    /**
     * This meted deletes a brand.
     * @summary  Method: deleteBrand(Long id)
     * @param id The brand to delete
     * @param ssid The sessionId for the store authentication
     * @return Response		object type: apiRestResponse
     */
	@Override
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	@ReturnType("com.serpics.jaxrs.data.ApiRestResponse<com.serpics.catalog.facade.data.BrandData>")
	public Response deleteBrand(@PathParam("id") Long id, @HeaderParam(value = "ssid") String ssid ) {
		
		ApiRestResponse<BrandData> apiRestResponse = new ApiRestResponse<BrandData>();

		brandFacade.deleteBrand(id);
		
		apiRestResponse.setStatus(ApiRestResponseStatus.OK);
		return Response.ok(apiRestResponse).build();
		
	}

		
    /**
     * This method finds a brand with some id.
     * @param id The id of brand to find
     * @param ssid The sessionId for the store authentication
     * @return Response		object type: apiRestResponse
     * @summary 	Method: findBrandById(Long id)
     * @statuscode 200 id is correct
     * @statuscode 404 id not found
     */
	@Override
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/code/{id}")
	@ReturnType("com.serpics.jaxrs.data.ApiRestResponse<com.serpics.catalog.facade.data.BrandData>")
	public Response findBrandById(@PathParam("id") Long id, @HeaderParam(value = "ssid") String ssid ) {

		// Assert.notNull(id, "id can not be null !");
		ApiRestResponse<BrandData> apiRestResponse = new ApiRestResponse<BrandData>();
		BrandData brandData = brandFacade.findBrandById(id);
		ResponseBuilder responseBuilder = null;
		
		if (brandData != null) {
			// 200 OK
			apiRestResponse.setStatus(ApiRestResponseStatus.OK);
			apiRestResponse.setResponseObject(brandData);
			responseBuilder = Response.ok();

		} else {
			// 404 Not Found - Brand Not Found
			apiRestResponse.setStatus(ApiRestResponseStatus.ERROR);
			apiRestResponse.setMessage("ERROR, brand not found");
			responseBuilder = Response.status(404);
		}
		return responseBuilder.entity(apiRestResponse).build();
	}

    /**
     * This method finds a brand with some name.
     * @param brandName The name of brand to find
     * @param ssid The sessionId for the store authentication
     * @return Response		object type: apiRestResponse
     * @summary  Method: findBrandByName(String name)
     * @statuscode 200 name is correct
     * @statuscode 404 name not found
     */
	@Override
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	// @Path("/name/{brandName : (brandName)?}")
	@Path("/name/{brandName}")
	@ReturnType("com.serpics.jaxrs.data.ApiRestResponse<com.serpics.catalog.facade.data.BrandData>")
	public Response findBrandByName(@PathParam("brandName") String brandName, @HeaderParam(value = "ssid") String ssid ) {

		// Assert.notNull(brandName, "name can not be null !");

		ApiRestResponse<BrandData> apiRestResponse = new ApiRestResponse<BrandData>();
		// if (brandName != null) {
		BrandData brandData = brandFacade.findBrandByName(brandName);
		ResponseBuilder responseBuilder = null;

		if (brandData != null) {
			// 200 OK
			apiRestResponse.setStatus(ApiRestResponseStatus.OK);
			apiRestResponse.setResponseObject(brandData);
			responseBuilder = Response.ok();

		} else {
			// 404 Not Found - Brand Not Found
			apiRestResponse.setStatus(ApiRestResponseStatus.ERROR);
			apiRestResponse.setMessage("ERROR, brand not found");
			responseBuilder = Response.status(404);
		}
		// } else {
		// // 400 Bad Request - BrandName is Null
		// apiRestResponse.setStatus(ApiRestResponseStatus.ERROR);
		// apiRestResponse.setMessage("ERROR, brandName can not be null !");
		// return Response.status(400).entity(apiRestResponse).build();
		//
		// }

		return responseBuilder.entity(apiRestResponse).build();
	}

}
