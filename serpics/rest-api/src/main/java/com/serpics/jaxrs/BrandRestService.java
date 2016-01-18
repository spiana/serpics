package com.serpics.jaxrs;

import javax.ws.rs.core.Response;

import com.serpics.jaxrs.data.BrandDataRequest;

public interface BrandRestService {
	
	public Response addBrand(BrandDataRequest brand);
	public Response updateBrand(BrandDataRequest brand);
	public Response deleteBrand(Long id);
	public Response findAll(int page , int size);
	public Response findBrandById(Long id);
	public Response findBrandByName(String name);
	

}
