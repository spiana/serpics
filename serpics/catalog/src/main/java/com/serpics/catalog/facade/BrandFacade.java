package com.serpics.catalog.facade;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.serpics.catalog.facade.data.BrandData;

public interface BrandFacade {
	
	public BrandData addBrand(BrandData entity);
	public BrandData updateBrand(BrandData entity);
	public void deleteBrand(Long id);
	
	public Page<BrandData> listBrand(Pageable page);
	public BrandData findBrandById(Long id);
	public BrandData findBrandByName(String name);
	
}
