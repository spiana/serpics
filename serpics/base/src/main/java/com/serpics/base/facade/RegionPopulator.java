package com.serpics.base.facade;


import org.springframework.beans.factory.annotation.Autowired;

import com.serpics.base.data.model.Region;
import com.serpics.base.facade.data.RegionData;
import com.serpics.commerce.core.CommerceEngine;
import com.serpics.core.facade.Populator;

public class RegionPopulator implements Populator<Region, RegionData>{
	
	@Autowired
	CommerceEngine commerceEngine;
	
	@Override
	public void populate(Region source, RegionData target) {
		target.setUuid(source.getUuid());
		target.setId(source.getId());
		target.setIsoCode(source.getIsoCode());
		if(source.getDescription() != null ){
			target.setDescription(source.getDescription().getText(commerceEngine.getCurrentContext().getLocale().getLanguage()));
		}
	}

}
