package com.serpics.jaxrs;

import java.util.List;

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
     * @param code The code of category to search
     * @return Response		object type: apiRestResponse
     * @statuscode 200 id found and correct 
     * @statuscode 404 Not Found - Category ID Not Found
     * 
     */
	@Override
	@Consumes(MediaType.APPLICATION_JSON)
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/code/{category}")
	@ReturnType("com.serpics.jaxrs.data.ApiRestResponse<com.serpics.catalog.facade.data.CategoryData>")
	public Response getCategoryByCode(@PathParam("category") String code){
		ApiRestResponse<CategoryData> apiRestResponse = new ApiRestResponse<CategoryData>();
		CategoryData categoryData = categoryFacade.findCategoryByCode(code);
		if (categoryData != null) {
			// 200 OK
			apiRestResponse.setStatus(ApiRestResponseStatus.OK);
			apiRestResponse.setResponseObject(categoryData);
			return Response.ok(apiRestResponse).build();

		} else {
			// 404 Not Found - Category Not Found
			apiRestResponse.setStatus(ApiRestResponseStatus.ERROR);
			apiRestResponse.setMessage("ERROR, category not found");
			return Response.status(404).entity(apiRestResponse).build();
		}
	}
	
	
    /**
     * This method gets a category with some Id.
     * @summary  Method: getCategoryById(Long categoryId)
     * @param categoryId The Id of category to search
     * @return Response		object type: apiRestResponse
     * @statuscode 200 Category id found and correct 
     * @statuscode 404 Not Found - Category ID Not Found
     * 
     */
	@Override
	@Consumes(MediaType.APPLICATION_JSON)
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{category}")
	@ReturnType("com.serpics.jaxrs.data.ApiRestResponse<com.serpics.catalog.facade.data.CategoryData>")
	public Response getCategoryById(@PathParam("category") Long categoryId){
		ApiRestResponse<CategoryData> apiRestResponse = new ApiRestResponse<CategoryData>();
		CategoryData categoryData = categoryFacade.findCategoryById(categoryId);
		if (categoryData != null) {
			// 200 OK
			apiRestResponse.setStatus(ApiRestResponseStatus.OK);
			apiRestResponse.setResponseObject(categoryData);
			return Response.ok(apiRestResponse).build();

		} else {
			// 404 Not Found - Category Not Found
			apiRestResponse.setStatus(ApiRestResponseStatus.ERROR);
			apiRestResponse.setMessage("ERROR, category not found");
			return Response.status(404).entity(apiRestResponse).build();
		}
	}
	
    /**
     * This method creates a category with a parent.
     * @summary  Method: createParent(CategoryDataRequest categoryDataRequest, Long parentId)
     * @param category The category to create
     * @param parentId The id of parent
     * @return Response		object type: apiRestResponse
     * 
     */
	@Override
	@Consumes(MediaType.APPLICATION_JSON)
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{parent}")
	@ReturnType("com.serpics.jaxrs.data.ApiRestResponse<com.serpics.catalog.facade.data.CategoryData>")
	public Response createParent(CategoryDataRequest categoryDataRequest, @PathParam("parent") Long parentId){
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
     * @summary  Method: create(CategoryData category)
     * @param category The category to create
     * @return Response		object type: apiRestResponse
     * 
     */
	@Override
	@Consumes(MediaType.APPLICATION_JSON)
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@ReturnType("com.serpics.jaxrs.data.ApiRestResponse<com.serpics.catalog.facade.data.CategoryData>")
	public Response create(CategoryDataRequest categoryDataRequest){
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
     * @param parentId The category id of the parent
     * @return Response		object type: apiRestResponse
     * 
     */
	@Override
	@Consumes(MediaType.APPLICATION_JSON)
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("addParent/{child}/{parent}")
	@ReturnType("com.serpics.jaxrs.data.ApiRestResponse<com.serpics.catalog.facade.data.CategoryData>")
	public Response addParent(@PathParam("child") Long childId, @PathParam("parent") Long parentId){
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
     * @return Response		object type: apiRestResponse
     * 
     */
	@Override
	@Consumes(MediaType.APPLICATION_JSON)
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getChild/{parent}")
	@ReturnType("com.serpics.jaxrs.data.ApiRestResponse<java.util.List<com.serpics.catalog.facade.data.CategoryData>>")
	public Response getChild(@PathParam("parent") Long parentId){
		Assert.notNull(parentId);
		List<CategoryData> listCategoryData = categoryFacade.listChildCategories(parentId);
		ApiRestResponse<List<CategoryData>> apiRestResponse = new ApiRestResponse<List<CategoryData>>();
		apiRestResponse.setStatus(ApiRestResponseStatus.OK);
		apiRestResponse.setResponseObject(listCategoryData);
		return Response.ok(apiRestResponse).build();
	}
	
    /**
     * This method updates a category
     * @summary  Method: update(CategoryData category)
     * @param category The category to update
     * @return Response		object type: apiRestResponse
     * 
     */
	@Override
	@Consumes(MediaType.APPLICATION_JSON)
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@ReturnType("com.serpics.jaxrs.data.ApiRestResponse<com.serpics.catalog.facade.data.CategoryData>")
	public Response update(CategoryDataRequest categoryDataRequest){
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
     * @return Response		object type: apiRestResponse
     * 
     */
	@Override
	@Consumes(MediaType.APPLICATION_JSON)
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{category}")
	@ReturnType("com.serpics.jaxrs.data.ApiRestResponse<com.serpics.catalog.facade.data.CategoryData>")
	public Response delete(@PathParam("category") Long categoryId){
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
     * @return Response		object type: apiRestResponse
     * 
     */
	@Override
	@Consumes(MediaType.APPLICATION_JSON)
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@ReturnType("com.serpics.jaxrs.data.ApiRestResponse<com.serpics.catalog.facade.data.CategoryData>")
	public Response findAll(@QueryParam("page") @DefaultValue("0") int page, @QueryParam("size" ) @DefaultValue("10") int size){
		ApiRestResponse<Page<CategoryData> > apiRestResponse = new ApiRestResponse<Page<CategoryData> >();
		apiRestResponse.setStatus(ApiRestResponseStatus.OK);
		apiRestResponse.setResponseObject( categoryFacade.listCategory(new PageRequest(page, size)));
		return Response.ok(apiRestResponse).build();
	}
	
    /**
     * This method gets all top categories
     * @summary  Method: getTop()
     * @return Response		object type: apiRestResponse
     * 
     */
	@Override
	@Consumes(MediaType.APPLICATION_JSON)
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/top")
	@ReturnType("com.serpics.jaxrs.data.ApiRestResponse<com.serpics.catalog.facade.data.CategoryData>")
	public Response getTop(){
		ApiRestResponse<List<CategoryData>> apiRestResponse = new ApiRestResponse<List<CategoryData>>();
		apiRestResponse.setStatus(ApiRestResponseStatus.OK);
		apiRestResponse.setResponseObject(categoryFacade.listTopCategory());
		return Response.ok(apiRestResponse).build();
	}
	
}
