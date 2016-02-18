package com.serpics.catalog.facade;

import javax.annotation.Resource;

import com.serpics.catalog.data.model.Ctentry;
import com.serpics.catalog.facade.data.CtentryData;
import com.serpics.commerce.core.CommerceEngine;
import com.serpics.core.facade.Populator;

public class CtentryPopulator implements Populator<Ctentry, CtentryData> {

	@Resource
	CommerceEngine commerceEngine;
	
	@Override
	public void populate(Ctentry source, CtentryData target) {
		target.setCode(source.getCode());
		target.setId(source.getId());
		target.setName(source.getName().getText(commerceEngine.getCurrentContext().getLocale().getLanguage()));
		target.setUuid(source.getUuid());
		target.setCreated(source.getCreated());
		target.setUpdated(source.getUpdated());
		
		target.setUrl(source.getUrl());
		if(source.getDescription() != null)
			target.setDescription(source.getDescription().getText(commerceEngine.getCurrentContext().getLocale().getLanguage()));
		//target.setDescription(source.getDescription().getText("it"));
		
		
	}
	
	
	

}
