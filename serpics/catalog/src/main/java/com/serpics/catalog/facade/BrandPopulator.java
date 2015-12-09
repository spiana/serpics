package com.serpics.catalog.facade;

import org.springframework.beans.factory.annotation.Autowired;

import com.serpics.catalog.data.model.Brand;
import com.serpics.catalog.facade.data.BrandData;
import com.serpics.catalog.services.BrandService;
import com.serpics.core.facade.Populator;

public class BrandPopulator implements Populator<Brand, BrandData> {

	@Autowired
	BrandService brandService;
	
	@Override
	public void populate(Brand source, BrandData target) {
		if(source.getLogoSrc() != null)
			target.setLogo(source.getLogoSrc());
		target.setName(source.getName());
		target.setId(source.getId());
		target.setUuid(source.getUuid());
		target.setCreated(source.getCreated());
		target.setUpdated(source.getUpdated());
		target.setPublished(source.getPublished());
		target.setBrandProductNumber(brandService.getBrandProduct(source));
		
	}

}
