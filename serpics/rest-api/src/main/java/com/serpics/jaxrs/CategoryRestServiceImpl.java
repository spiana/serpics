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
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.serpics.catalog.facade.CategoryFacade;
import com.serpics.catalog.facade.data.CategoryData;

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
	public CategoryData getCategoryByCode(@PathParam("category") String code){
		Assert.notNull(code);
		return categoryFacade.findCategoryByCode(code);
	}
	
	@Override
	@Consumes(MediaType.APPLICATION_JSON)
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{category}")
	public CategoryData getCategoryById(@PathParam("category") Long categoryId){
		Assert.notNull(categoryId);
		return categoryFacade.findCategoryById(categoryId);
	}
		
	@Override
	@Consumes(MediaType.APPLICATION_JSON)
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{parent}")
	public Response createParent(CategoryData category, @PathParam("parent") Long parentId){
		Assert.notNull(category);
		Assert.notNull(parentId);
		CategoryData categoryData = null;
		categoryData = categoryFacade.create(category, parentId);
		return Response.ok(categoryData).build();
	}
	
	@Override
	@Consumes(MediaType.APPLICATION_JSON)
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response create(CategoryData category){
		Assert.notNull(category);
		CategoryData categoryData = null;
		categoryData = categoryFacade.create(category);
		return Response.ok(categoryData).build();
	}
	
	@Override
	@Consumes(MediaType.APPLICATION_JSON)
	@POST
	@Path("addParent/{child}/{parent}")
	public Response addParent(@PathParam("child") Long childId, @PathParam("parent") Long parentId){
		Assert.notNull(childId);
		Assert.notNull(parentId);
		categoryFacade.addCategoryParent(childId, parentId);
		return Response.ok().build();
	}
	
	@Override
	@Consumes(MediaType.APPLICATION_JSON)
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getChild/{parent}")
	public List<CategoryData> getChild(@PathParam("parent") Long parentId){
		Assert.notNull(parentId);
		return categoryFacade.listChildCategories(parentId);
	}
	
	@Override
	@Consumes(MediaType.APPLICATION_JSON)
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	public Response update(CategoryData category){
		Assert.notNull(category);
		CategoryData categoryData = null;
		categoryData = categoryFacade.updateCategory(category);
		return Response.ok(categoryData).build();
	}
	
	@Override
	@Consumes(MediaType.APPLICATION_JSON)
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{category}")
	public Response delete(@PathParam("category") Long categoryId){
		Assert.notNull(categoryId);
		categoryFacade.deleteCategory(categoryId);
		return Response.ok().build();
	}
	
	@Override
	@Consumes(MediaType.APPLICATION_JSON)
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Page<CategoryData> findAll(@QueryParam("page") @DefaultValue("0") int page, @QueryParam("size" ) @DefaultValue("10") int size){
		Pageable pageable = new PageRequest(page, size);
		return categoryFacade.listCategory(pageable);
	}
	
	@Override
	@Consumes(MediaType.APPLICATION_JSON)
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/top")
	public List<CategoryData> getTop(){
		return categoryFacade.listTopCategory();
	}
	
}
