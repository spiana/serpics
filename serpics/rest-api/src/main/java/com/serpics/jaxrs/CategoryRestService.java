package com.serpics.jaxrs;

import javax.ws.rs.core.Response;

import com.serpics.catalog.facade.data.CategoryData;

public interface CategoryRestService {
	
	public Response createParent(CategoryData category, Long parentId);
	public Response create(CategoryData category);
	public Response addParent(Long childId, Long parentId);
	public Response getChild(Long parentId);
	public Response getCategoryById(Long categoryId);
	public Response getCategoryByCode(String categoryCode);
	public Response update(CategoryData category);
	public Response delete(Long categoryId);
	public Response findAll(int page, int size);
	public Response getTop();
	
}
