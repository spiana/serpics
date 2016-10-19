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
