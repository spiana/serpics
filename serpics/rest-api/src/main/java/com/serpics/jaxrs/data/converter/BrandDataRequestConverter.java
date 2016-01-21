package com.serpics.jaxrs.data.converter;


import com.serpics.catalog.facade.data.BrandData;
import com.serpics.core.facade.Populator;
import com.serpics.jaxrs.data.BrandDataRequest;

public class BrandDataRequestConverter implements Populator<BrandDataRequest, BrandData>{

		
	@Override
	public void populate(BrandDataRequest source, BrandData target) {
		target.setName(source.getName());
		target.setLogo(source.getLogo());
		target.setPublished(source.isPublished());
		
	}
}
