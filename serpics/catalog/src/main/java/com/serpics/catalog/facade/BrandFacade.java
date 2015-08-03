package com.serpics.catalog.facade;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.serpics.catalog.facade.data.BrandData;

public interface BrandFacade {
	public BrandData addBrand(BrandData entity);
	public Page<BrandData> findAll(Pageable page);
	
}
