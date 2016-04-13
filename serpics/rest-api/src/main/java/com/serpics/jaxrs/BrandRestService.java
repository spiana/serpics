package com.serpics.jaxrs;

import javax.ws.rs.core.Response;

import com.serpics.jaxrs.data.BrandDataRequest;

public interface BrandRestService {
	
	public Response addBrand(BrandDataRequest brand,String ssid);
	public Response updateBrand(Long brandId,BrandDataRequest brand,String ssid);
	public Response deleteBrand(Long id,String ssid);
	public Response findAllpage(int page , int size,String ssid);
	public Response findBrandById(Long id,String ssid);
	public Response findBrandByName(String name,String ssid);
	public Response findAllList(String ssid);
	public Response findBrandProductsById(Long id,String ssid);
	public Response findBrandProductsByCode(String code,String ssid);
	public Response brandProductsByCodePage(int page , int size,String brandCode, String ssid);
	public Response brandProductsByIdPage(int page , int size, Long brandId, String ssid);
	

	
	

}
