package com.serpics.commerce.facade;

import org.springframework.beans.factory.annotation.Autowired;

import com.serpics.commerce.core.CommerceEngine;
import com.serpics.commerce.data.model.Shipmode;
import com.serpics.commerce.facade.data.ShipmodeData;
import com.serpics.core.facade.Populator;

public class ShipmodePopulator implements Populator<Shipmode, ShipmodeData>{
	
	@Autowired
	CommerceEngine commerceEngine;
	
	@Override 
	public void populate(Shipmode source, ShipmodeData target) {		
	
		target.setId(source.getId());
		target.setCreated(source.getCreated());
		if(source.getDescription() != null ){
			target.setDescription(source.getDescription().getText(commerceEngine.getCurrentContext().getLocale().getLanguage()));
		}
		target.setName(source.getName());
		
		target.setUuid(source.getUuid());
		target.setUpdated(source.getUpdated());
		
	}

	
}
