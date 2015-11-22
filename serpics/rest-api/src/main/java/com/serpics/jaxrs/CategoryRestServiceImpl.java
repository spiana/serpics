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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.serpics.catalog.facade.CategoryFacade;
import com.serpics.catalog.facade.data.CategoryData;
import com.serpics.jaxrs.data.ApiRestResponse;
import com.serpics.jaxrs.data.ApiRestResponseStatus;

@Path("/categoryService")
@Transactional(readOnly=true)
public class CategoryRestServiceImpl implements CategoryRestService{
	
	@Autowired
	CategoryFacade categoryFacade;
	
	@Override
	@Consumes(MediaType.APPLICATION_JSON)
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/code/{category}")
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
	
	@Override
	@Consumes(MediaType.APPLICATION_JSON)
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{category}")
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
		
	@Override
	@Consumes(MediaType.APPLICATION_JSON)
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{parent}")
	public Response createParent(CategoryData category, @PathParam("parent") Long parentId){
		Assert.notNull(category);
		Assert.notNull(parentId);
		ApiRestResponse<CategoryData> apiRestResponse = new ApiRestResponse<CategoryData>();
		CategoryData categoryData = null;
		categoryData = categoryFacade.create(category, parentId);
		apiRestResponse.setStatus(ApiRestResponseStatus.OK);
		apiRestResponse.setResponseObject(categoryData);
		return Response.ok(apiRestResponse).build();
	}
	
	@Override
	@Consumes(MediaType.APPLICATION_JSON)
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response create(CategoryData category){
		Assert.notNull(category);
		ApiRestResponse<CategoryData> apiRestResponse = new ApiRestResponse<CategoryData>();
		CategoryData categoryData = null;
		categoryData = categoryFacade.create(category);
		apiRestResponse.setStatus(ApiRestResponseStatus.OK);
		apiRestResponse.setResponseObject(categoryData);
		return Response.ok(apiRestResponse).build();
	}
	
	@Override
	@Consumes(MediaType.APPLICATION_JSON)
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("addParent/{child}/{parent}")
	public Response addParent(@PathParam("child") Long childId, @PathParam("parent") Long parentId){
		Assert.notNull(childId);
		Assert.notNull(parentId);
		categoryFacade.addCategoryParent(childId, parentId);
		ApiRestResponse<CategoryData> apiRestResponse = new ApiRestResponse<CategoryData>();
		apiRestResponse.setMessage("OK, category relation added");
		return Response.ok(apiRestResponse).build();
	}
	
	@Override
	@Consumes(MediaType.APPLICATION_JSON)
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getChild/{parent}")
	public Response getChild(@PathParam("parent") Long parentId){
		Assert.notNull(parentId);
		List<CategoryData> listCategoryData = categoryFacade.listChildCategories(parentId);
		ApiRestResponse<List<CategoryData>> apiRestResponse = new ApiRestResponse<List<CategoryData>>();
		apiRestResponse.setStatus(ApiRestResponseStatus.OK);
		apiRestResponse.setResponseObject(listCategoryData);
		return Response.ok(apiRestResponse).build();
	}
	
	@Override
	@Consumes(MediaType.APPLICATION_JSON)
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	public Response update(CategoryData category){
		Assert.notNull(category);
		ApiRestResponse<CategoryData> apiRestResponse = new ApiRestResponse<CategoryData>();
		CategoryData categoryData = null;
		categoryData = categoryFacade.updateCategory(category);
		apiRestResponse.setStatus(ApiRestResponseStatus.OK);
		apiRestResponse.setResponseObject(categoryData);
		return Response.ok(apiRestResponse).build();
	}
	
	@Override
	@Consumes(MediaType.APPLICATION_JSON)
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{category}")
	public Response delete(@PathParam("category") Long categoryId){
		Assert.notNull(categoryId);
		ApiRestResponse<CategoryData> apiRestResponse = new ApiRestResponse<CategoryData>();
		categoryFacade.deleteCategory(categoryId);
		apiRestResponse.setStatus(ApiRestResponseStatus.OK);
		return Response.ok(apiRestResponse).build();
	}
	
	@Override
	@Consumes(MediaType.APPLICATION_JSON)
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response findAll(@QueryParam("page") @DefaultValue("0") int page, @QueryParam("size" ) @DefaultValue("10") int size){
		ApiRestResponse<Page<CategoryData> > apiRestResponse = new ApiRestResponse<Page<CategoryData> >();
		apiRestResponse.setStatus(ApiRestResponseStatus.OK);
		apiRestResponse.setResponseObject( categoryFacade.listCategory(new PageRequest(page, size)));
		return Response.ok(apiRestResponse).build();
	}
	
	@Override
	@Consumes(MediaType.APPLICATION_JSON)
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/top")
	public Response getTop(){
		ApiRestResponse<List<CategoryData>> apiRestResponse = new ApiRestResponse<List<CategoryData>>();
		apiRestResponse.setStatus(ApiRestResponseStatus.OK);
		apiRestResponse.setResponseObject(categoryFacade.listTopCategory());
		return Response.ok(apiRestResponse).build();
	}
	
}
