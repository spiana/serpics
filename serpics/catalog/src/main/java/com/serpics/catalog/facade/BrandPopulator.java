package com.serpics.catalog.facade;

import com.serpics.catalog.data.model.Brand;
import com.serpics.catalog.facade.data.BrandData;
import com.serpics.core.facade.Populator;

public class BrandPopulator implements Populator<Brand, BrandData> {

	@Override
	public void populate(Brand source, BrandData target) {
		if(source.getLogoSrc() != null)
			target.setLogo(source.getLogoSrc());
		target.setName(source.getName());
		target.setId(source.getId());
		target.setUuid(source.getUuid());
		target.setCreated(source.getCreated());
		target.setUpdated(source.getUpdated());
		
	}

}
