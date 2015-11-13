package com.serpics.jaxrs;

import javax.ws.rs.core.Response;

import org.springframework.data.domain.Page;

import com.serpics.catalog.facade.data.BrandData;

public interface BrandRestService {
	
	public Response addBrand(BrandData brand);
	public Response updateBrand(BrandData brand);
	public Response deleteBrand(Long id);
	public Page<BrandData> findAll(int page , int size);
	public BrandData findBrandById(Long id);
	public Response findBrandByName(String name);
	

}
