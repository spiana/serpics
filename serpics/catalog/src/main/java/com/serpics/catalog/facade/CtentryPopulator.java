/*******************************************************************************
 * Copyright 2014-2016  StepFour Srl
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *  
 *      http://www.apache.org/licenses/LICENSE-2.0
 *  
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *  
 *******************************************************************************/
package com.serpics.catalog.facade;

import javax.annotation.Resource;

import com.serpics.base.commerce.CommerceEngine;
import com.serpics.base.data.model.Media;
import com.serpics.base.facade.data.MediaData;
import com.serpics.catalog.data.model.Ctentry;
import com.serpics.catalog.facade.data.CtentryData;
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
