package com.serpics.jaxrs;

import javax.ws.rs.core.Response;

import com.serpics.jaxrs.data.BrandDataRequest;

public interface BrandRestService {
	
	public Response addBrand(BrandDataRequest brand,String ssid);
	public Response updateBrand(BrandDataRequest brand,String ssid);
	public Response deleteBrand(Long id,String ssid);
	public Response findAll(int page , int size,String ssid);
	public Response findBrandById(Long id,String ssid);
	public Response findBrandByName(String name,String ssid);
	public Response findAllList(String ssid);
	

}
