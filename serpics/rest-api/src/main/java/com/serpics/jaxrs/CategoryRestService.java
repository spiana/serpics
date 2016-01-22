package com.serpics.jaxrs;

import javax.ws.rs.core.Response;

import com.serpics.jaxrs.data.CategoryDataRequest;

public interface CategoryRestService {
	
	public Response createParent(CategoryDataRequest category, Long parentId);
	public Response create(CategoryDataRequest category);
	public Response addParent(Long childId, Long parentId);
	public Response getChild(Long parentId);
	public Response getCategoryById(Long categoryId);
	public Response getCategoryByCode(String categoryCode);
	public Response update(CategoryDataRequest category);
	public Response delete(Long categoryId);
	public Response findAll(int page, int size);
	public Response getTop();
	
}