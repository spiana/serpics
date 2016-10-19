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
package com.serpics.base.facade.data;


import com.serpics.core.facade.AbstractData;


public class CountryData  extends AbstractData{ 
	
	protected GeocodeData geocode;
	protected String iso2Code;
	protected String iso3Code;
	protected String description;

	public GeocodeData getGeocode() {
		return geocode;
	}
	public void setGeocode(GeocodeData geocode) {
		this.geocode = geocode;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getIso2Code() {
		return iso2Code;
	}
	public void setIso2Code(String iso2Code) {
		this.iso2Code = iso2Code;
	}
	public String getIso3Code() {
		return iso3Code;
	}
	public void setIso3Code(String iso3Code) {
		this.iso3Code = iso3Code;
	}

}
