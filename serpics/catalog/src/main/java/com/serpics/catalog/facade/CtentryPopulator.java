package com.serpics.catalog.facade;

import javax.annotation.Resource;

import com.serpics.base.data.model.Media;
import com.serpics.base.facade.data.MediaData;
import com.serpics.catalog.data.model.Ctentry;
import com.serpics.catalog.facade.data.CtentryData;
import com.serpics.commerce.core.CommerceEngine;
import com.serpics.core.facade.AbstractPopulatingConverter;
import com.serpics.core.facade.Populator;

public class CtentryPopulator implements Populator<Ctentry, CtentryData> {

	@Resource
	CommerceEngine commerceEngine;
	
	@Resource(name= "mediaConverter")
	AbstractPopulatingConverter<Media, MediaData> mediaConverter;
	
	@Override
	public void populate(Ctentry source, CtentryData target) {
		target.setCode(source.getCode());
		target.setId(source.getId());
		if (source.getName() != null){
			target.setName(source.getName().getText(commerceEngine.getCurrentContext().getLocale().getLanguage()));
		}
		target.setUuid(source.getUuid());
		target.setCreated(source.getCreated());
		target.setUpdated(source.getUpdated());
		if (source.getMetaDescription() != null ){
			target.setMetaDescription(source.getMetaDescription().getText(commerceEngine.getCurrentContext().getLocale().getLanguage()));
		}
		if (source.getMetaKeyword() != null){
			target.setMetaKeyword(source.getMetaKeyword().getText(commerceEngine.getCurrentContext().getLocale().getLanguage()));
		}

		target.setUrl(source.getUrl());
		if(source.getDescription() != null)
			target.setDescription(source.getDescription().getText(commerceEngine.getCurrentContext().getLocale().getLanguage()));
		
		 if (source.getPrimaryImage() != null)
			 target.setPrimaryImage(mediaConverter.convert(source.getPrimaryImage()));
		
	}
	
	
	

}
