package com.serpics.jaxrs;

import java.util.List;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.qmino.miredot.annotations.ReturnType;
import com.serpics.catalog.facade.CategoryFacade;
import com.serpics.catalog.facade.data.CategoryData;
import com.serpics.jaxrs.data.ApiRestResponse;
import com.serpics.jaxrs.data.ApiRestResponseStatus;
import com.serpics.jaxrs.data.CategoryDataRequest;

@Path("/categoryService")
@Transactional(readOnly=true)
public class CategoryRestServiceImpl implements CategoryRestService{
	
	Logger LOG = LoggerFactory.getLogger(CategoryRestServiceImpl.class);
	
	@Autowired
	CategoryFacade categoryFacade;
	
    /**
     * This method gets a category with some code.
     * @summary  Method: getCategoryByCode(String code)
     * @param categoryCode The code of category to search
     * @param ssid The sessionId for the store authentication
     * @return Response		object type: apiRestResponse
     * @statuscode 200 id found and correct 
     * @statuscode 404 Not Found - Category ID Not Found
     * 
     */
	@Override
	@Consumes(MediaType.APPLICATION_JSON)
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/code/{categoryCode}")
	@ReturnType("com.serpics.jaxrs.data.ApiRestResponse<com.serpics.catalog.facade.data.CategoryData>")
	public Response getCategoryByCode(@PathParam("categoryCode") String categoryCode,@HeaderParam(value = "ssid") String ssid){
		ApiRestResponse<CategoryData> apiRestResponse = new ApiRestResponse<CategoryData>();
		CategoryData categoryData = categoryFacade.findCategoryByCode(categoryCode);
		ResponseBuilder responseBuilder = null;
		
		if (categoryData != null) {
			// 200 OK
			apiRestResponse.setStatus(ApiRestResponseStatus.OK);
			apiRestResponse.setResponseObject(categoryData);
			responseBuilder = Response.ok();

		} else {
			// 404 Not Found - Category Not Found
			apiRestResponse.setStatus(ApiRestResponseStatus.ERROR);
			apiRestResponse.setMessage("ERROR, category not found");
			responseBuilder = Response.status(404);
		}
		return responseBuilder.entity(apiRestResponse).build();
	}
	
	
    /**
     * This method gets a category with some Id.
     * @summary  Method: getCategoryById(Long categoryId)
     * @param categoryId The Id of category to search
     * @param ssid The sessionId for the store authentication
     * @return Response		object type: apiRestResponse
     * @statuscode 200 Category id found and correct 
     * @statuscode 404 Not Found - Category ID Not Found
     * 
     */
	@Override
	@Consumes(MediaType.APPLICATION_JSON)
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/id/{categoryId}")
	@ReturnType("com.serpics.jaxrs.data.ApiRestResponse<com.serpics.catalog.facade.data.CategoryData>")
	public Response getCategoryById(@PathParam("categoryId") Long categoryId,@HeaderParam(value = "ssid") String ssid){
		ApiRestResponse<CategoryData> apiRestResponse = new ApiRestResponse<CategoryData>();
		CategoryData categoryData = categoryFacade.findCategoryById(categoryId);
		ResponseBuilder responseBuilder = null;
		if (categoryData != null) {
			// 200 OK
			apiRestResponse.setStatus(ApiRestResponseStatus.OK);
			apiRestResponse.setResponseObject(categoryData);
			responseBuilder = Response.ok();

		} else {
			// 404 Not Found - Category Not Found
			apiRestResponse.setStatus(ApiRestResponseStatus.ERROR);
			apiRestResponse.setMessage("ERROR, category not found");
			responseBuilder = Response.status(404);
		}
		return responseBuilder.entity(apiRestResponse).build();
	}
	
    /**
     * This method creates a category with a parent. (parenthood)
     * @summary  Method: createCategoryWithParent(CategoryDataRequest categoryDataRequest, Long parentId)
     * @param categoryDataRequest The category to create
     * @param ssid The sessionId for the store authentication
     * @param parentId The id of parent to associate with the category created
     * @return Response		object type: apiRestResponse
     * 
     */
	@Override
	@Consumes(MediaType.APPLICATION_JSON)
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("parent/{parentId}")
	@ReturnType("com.serpics.jaxrs.data.ApiRestResponse<com.serpics.catalog.facade.data.CategoryData>")
	public Response createParent(CategoryDataRequest categoryDataRequest, @PathParam("parentId") Long parentId,@HeaderParam(value = "ssid") String ssid){
		ApiRestResponse<CategoryData> apiRestResponse = new ApiRestResponse<CategoryData>();
		CategoryData categoryData = new CategoryData();
		ResponseBuilder responseBuilder = null;
		
		try{
			BeanUtils.copyProperties(categoryDataRequest, categoryData);
			Assert.notNull(categoryData);
			Assert.notNull(parentId);
			categoryData = categoryFacade.create(categoryData, parentId);
			apiRestResponse.setStatus(ApiRestResponseStatus.OK);
			apiRestResponse.setResponseObject(categoryData);
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
     * This method creates a category.
     * @summary  Method: create(CategoryDataRequest categoryDataRequest)
     * @param categoryDataRequest The category to create
     * @param ssid The sessionId for the store authentication
     * @return Response		object type: apiRestResponse
     * 
     */
	@Override
	@Consumes(MediaType.APPLICATION_JSON)
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@ReturnType("com.serpics.jaxrs.data.ApiRestResponse<com.serpics.catalog.facade.data.CategoryData>")
	public Response create(CategoryDataRequest categoryDataRequest,@HeaderParam(value = "ssid") String ssid){
		ApiRestResponse<CategoryData> apiRestResponse = new ApiRestResponse<CategoryData>();
		CategoryData categoryData = new CategoryData();
		ResponseBuilder responseBuilder = null;
		
		try{
			BeanUtils.copyProperties(categoryDataRequest, categoryData);
			Assert.notNull(categoryData);
			categoryData = categoryFacade.create(categoryData);
			apiRestResponse.setStatus(ApiRestResponseStatus.OK);
			apiRestResponse.setResponseObject(categoryData);
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
     * This method creates a relation between two categories.
     * @summary  Method: addParent(Long childId,Long parentId)
     * @param childId The category id of the child
     * @param parentId The category id of the parent to add to child
     * @param ssid The sessionId for the store authentication
     * @return Response		object type: apiRestResponse
     * 
     */
	@Override
	@Consumes(MediaType.APPLICATION_JSON)
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("parent/{childId}/{parentId}")
	@ReturnType("com.serpics.jaxrs.data.ApiRestResponse<com.serpics.catalog.facade.data.CategoryData>")
	public Response addParent(@PathParam("childId") Long childId, @PathParam("parentId") Long parentId,@HeaderParam(value = "ssid") String ssid){
		Assert.notNull(childId);
		Assert.notNull(parentId);
		categoryFacade.addCategoryParent(childId, parentId);
		ApiRestResponse<CategoryData> apiRestResponse = new ApiRestResponse<CategoryData>();
		apiRestResponse.setMessage("OK, category relation added");
		return Response.ok(apiRestResponse).build();
	}
	
    /**
     * This method gets a list of category whith same parent
     * @summary  Method: getChild(Long parentId)
     * @param parentId The category id of the parent
     * @param ssid The sessionId for the store authentication
     * @return Response		object type: apiRestResponse
     * 
     */
	@Override
	@Consumes(MediaType.APPLICATION_JSON)
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/parent/getChild/{parentId}")
	@ReturnType("com.serpics.jaxrs.data.ApiRestResponse<java.util.List<java.util.List<com.serpics.catalog.facade.data.CategoryData>>")
	public Response getChild(@PathParam("parentId") Long parentId,@HeaderParam(value = "ssid") String ssid){
		Assert.notNull(parentId);
		List<CategoryData> listCategoryData = categoryFacade.listChildCategories(parentId);
		ApiRestResponse<List<CategoryData>> apiRestResponse = new ApiRestResponse<List<CategoryData>>();
		apiRestResponse.setStatus(ApiRestResponseStatus.OK);
		apiRestResponse.setResponseObject(listCategoryData);
		return Response.ok(apiRestResponse).build();
	}
	
    /**
     * This method gets a list of category whith same parent
     * @summary  Method: getChild(String code)
     * @param parentId The category code of the parent
     * @param ssid The sessionId for the store authentication
     * @return Response		object type: apiRestResponse
     * 
     */
	@Override
	@Consumes(MediaType.APPLICATION_JSON)
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/parent/getChild/code/{code}")
	@ReturnType("com.serpics.jaxrs.data.ApiRestResponse<java.util.List<java.util.List<com.serpics.catalog.facade.data.CategoryData>>")
	public Response getChildByCode(@PathParam("code") String code,@HeaderParam(value = "ssid") String ssid){
		Assert.notNull(code);
		List<CategoryData> listCategoryData = categoryFacade.listChildCategories(code);
		ApiRestResponse<List<CategoryData>> apiRestResponse = new ApiRestResponse<List<CategoryData>>();
		apiRestResponse.setStatus(ApiRestResponseStatus.OK);
		apiRestResponse.setResponseObject(listCategoryData);
		return Response.ok(apiRestResponse).build();
	}
	
    /**
     * This method updates a category
     * @summary  Method: update(CategoryDataRequest categoryDataRequest)
     * @param categoryDataRequest The category to update
     * @param categoryId The id of category to update
     * @param ssid The sessionId for the store authentication
     * @return Response		object type: apiRestResponse
     * 
     */
	@Override
	@Consumes(MediaType.APPLICATION_JSON)
	@PUT
	@Path("/id/{categoryId}")
	@Produces(MediaType.APPLICATION_JSON)
	@ReturnType("com.serpics.jaxrs.data.ApiRestResponse<com.serpics.catalog.facade.data.CategoryData>")
	public Response update(@PathParam("categoryId") Long categoryId,CategoryDataRequest categoryDataRequest,@HeaderParam(value = "ssid") String ssid){
		ApiRestResponse<CategoryData> apiRestResponse = new ApiRestResponse<CategoryData>();
		CategoryData categoryData = new CategoryData();
		ResponseBuilder responseBuilder = null;
		
		try{
			BeanUtils.copyProperties(categoryDataRequest, categoryData);
			Assert.notNull(categoryData);
			categoryData = categoryFacade.updateCategory(categoryData);
			apiRestResponse.setStatus(ApiRestResponseStatus.OK);
			apiRestResponse.setResponseObject(categoryData);
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
     * This method deletes a category
     * @summary  Method: delete(Long categoryId)
     * @param categoryId The id of category to delete
     * @param ssid The sessionId for the store authentication
     * @return Response		object type: apiRestResponse
     * 
     */
	@Override
	@Consumes(MediaType.APPLICATION_JSON)
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/id/{categoryId}")
	@ReturnType("com.serpics.jaxrs.data.ApiRestResponse<com.serpics.catalog.facade.data.CategoryData>")
	public Response delete(@PathParam("categoryId") Long categoryId,@HeaderParam(value = "ssid") String ssid){
		Assert.notNull(categoryId);
		ApiRestResponse<CategoryData> apiRestResponse = new ApiRestResponse<CategoryData>();
		categoryFacade.deleteCategory(categoryId);
		apiRestResponse.setStatus(ApiRestResponseStatus.OK);
		return Response.ok(apiRestResponse).build();
	}
	
    /**
     * This method findAll categories
     * @summary  Method: findAll(int page, int size)
     * @param page The page number
     * @param size The number of elements in a page
     * @param ssid The sessionId for the store authentication
     * @return Response		object type: apiRestResponse
     * 
     */
	@Override
	@Consumes(MediaType.APPLICATION_JSON)
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@ReturnType("com.serpics.jaxrs.data.ApiRestResponse<org.springframework.data.domain.Page<com.serpics.catalog.facade.data.CategoryData>>")
	public Response findAll(@QueryParam("page") @DefaultValue("0") int page, @QueryParam("size" ) @DefaultValue("10") int size,@HeaderParam(value = "ssid") String ssid){
		ApiRestResponse<Page<CategoryData> > apiRestResponse = new ApiRestResponse<Page<CategoryData> >();
		apiRestResponse.setStatus(ApiRestResponseStatus.OK);
		apiRestResponse.setResponseObject( categoryFacade.listCategory(new PageRequest(page, size)));
		return Response.ok(apiRestResponse).build();
		
	}
	
    /**
     * This method gets all top categories
     * @summary  Method: getTop()
     * @param ssid The sessionId for the store authentication
     * @return Response		object type: apiRestResponse
     * 
     */
	@Override
	@Consumes(MediaType.APPLICATION_JSON)
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/top")
	@ReturnType("com.serpics.jaxrs.data.ApiRestResponse<java.util.List<com.serpics.catalog.facade.data.CategoryData>>")
	public Response getTop(@HeaderParam(value = "ssid") String ssid){
		ApiRestResponse<List<CategoryData>> apiRestResponse = new ApiRestResponse<List<CategoryData>>();
		apiRestResponse.setStatus(ApiRestResponseStatus.OK);
		apiRestResponse.setResponseObject(categoryFacade.listTopCategory());
		return Response.ok(apiRestResponse).build();
	}
	
}
