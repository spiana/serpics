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
import org.springframework.beans.factory.annotation.Required;

import com.serpics.base.commerce.CommerceEngine;
import com.serpics.base.data.model.Country;
import com.serpics.base.data.model.Geocode;
import com.serpics.base.facade.data.CountryData;
import com.serpics.base.facade.data.GeocodeData;
import com.serpics.core.facade.AbstractPopulatingConverter;
import com.serpics.core.facade.Populator;


public class CountryPopulator  implements Populator<Country, CountryData>{
	private AbstractPopulatingConverter<Geocode, GeocodeData> geocodeConverter;
	
	@Autowired
	CommerceEngine commerceEngine;
	
	@Override
	public void populate(Country source, CountryData target) {
		
		target.setId(source.getCountriesId());
		target.setIso2Code(source.getIso2Code());
		target.setIso3Code(source.getIso3Code());
		if (source.getGeocode() != null){
			target.setGeocode(geocodeConverter.convert(source.getGeocode()));
		}
		if(source.getDescription() != null ){
			target.setDescription(source.getDescription().getText(commerceEngine.getCurrentContext().getLocale().getLanguage()));
		}
		target.setUuid(source.getUuid()); 
	}
	
	@Required
	public void setGeocodeConverter(
			AbstractPopulatingConverter<Geocode, GeocodeData> geocodeConverter) {
		this.geocodeConverter = geocodeConverter;
	}
	
}

