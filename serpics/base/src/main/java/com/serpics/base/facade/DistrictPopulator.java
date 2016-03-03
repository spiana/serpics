package com.serpics.base.facade;


import org.springframework.beans.factory.annotation.Autowired;

import com.serpics.base.data.model.District;
import com.serpics.base.facade.data.DistrictData;
import com.serpics.commerce.core.CommerceEngine;
import com.serpics.core.facade.Populator;

public class DistrictPopulator implements Populator<District, DistrictData>{
	
	@Autowired
	CommerceEngine commerceEngine;
	
	@Override
	public void populate(District source, DistrictData target) {
		target.setUuid(source.getUuid());
		target.setId(source.getId());
		target.setIsoCode(source.getIsoCode());
		if(source.getDescription() != null ){
			target.setDescription(source.getDescription().getText(commerceEngine.getCurrentContext().getLocale().getLanguage()));
		}
	}

}
