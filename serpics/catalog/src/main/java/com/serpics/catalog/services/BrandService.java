package com.serpics.catalog.services;

import com.serpics.catalog.data.model.Brand;
import com.serpics.core.service.EntityService;

public interface BrandService extends EntityService<Brand, Long> {
	
	
	public Brand findOneByCode(String code);

	public int getBrandProduct(Brand brand);
	
}
