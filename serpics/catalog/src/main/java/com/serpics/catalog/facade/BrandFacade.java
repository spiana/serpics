package com.serpics.catalog.facade;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.serpics.catalog.facade.data.BrandData;

public interface BrandFacade {
	
	public BrandData addBrand(BrandData entity);
	public BrandData updateBrand(BrandData entity);
	public void deleteBrand(Long id);
	
	public Page<BrandData> pageBrand(Pageable page);
	public BrandData findBrandById(Long id);
	public BrandData findBrandByName(String name);
	public List<BrandData> listBrand();
	
}
