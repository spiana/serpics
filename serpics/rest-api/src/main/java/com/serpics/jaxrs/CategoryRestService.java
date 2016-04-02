package com.serpics.jaxrs;

import javax.ws.rs.core.Response;

import com.serpics.jaxrs.data.CategoryDataRequest;

public interface CategoryRestService {
	
	public Response createParent(CategoryDataRequest category, Long parentId,String ssid);
	public Response create(CategoryDataRequest category,String ssid);
	public Response addParent(Long childId, Long parentId,String ssid);
	public Response getChild(Long parentId,String ssid);
	public Response getCategoryById(Long categoryId,String ssid);
	public Response getCategoryByCode(String categoryCode,String ssid);
	public Response update(Long categoryId,CategoryDataRequest category,String ssid);
	public Response delete(Long categoryId,String ssid);
	public Response findAll(int page, int size,String ssid);
	public Response getTop(String ssid);
	public Response getChildByCode(String code, String ssid);
	
}
