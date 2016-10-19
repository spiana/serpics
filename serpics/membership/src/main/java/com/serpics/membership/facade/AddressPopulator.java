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
package com.serpics.membership.facade;

import org.springframework.beans.factory.annotation.Required;

import com.serpics.base.data.model.Country;
import com.serpics.base.data.model.District;
import com.serpics.base.data.model.Region;
import com.serpics.base.facade.data.CountryData;
import com.serpics.base.facade.data.DistrictData;
import com.serpics.base.facade.data.RegionData;
import com.serpics.core.facade.AbstractPopulatingConverter;
import com.serpics.core.facade.Populator;
import com.serpics.membership.data.model.AbstractAddress;
import com.serpics.membership.facade.data.AddressData;
public class AddressPopulator implements Populator<AbstractAddress, AddressData>{
	private AbstractPopulatingConverter<Country, CountryData> countryConverter;
	private AbstractPopulatingConverter<Region, RegionData> regionConverter;
	private AbstractPopulatingConverter<District, DistrictData> districtConverter;
	@Override 
	public void populate(AbstractAddress source, AddressData target) {
		
		target.setId(source.getId());
		target.setCreated(source.getCreated());
		target.setUpdated(source.getUpdated());
		
		target.setNickname(source.getNickname());
		target.setFirstname(source.getFirstname());
		target.setLastname(source.getLastname());
		
		target.setAddress1(source.getAddress1());
		target.setAddress2(source.getAddress2());
		target.setAddress3(source.getAddress3());
		target.setStreetNumber(source.getStreetNumber());
		target.setZipcode(source.getZipcode());
		target.setCity(source.getCity());
			
		target.setEmail(source.getEmail());
		
		target.setField1(source.getField1());
		target.setField2(source.getField2());
		
		target.setFax(source.getFax());
		target.setMobile(source.getMobile());
		target.setPhone(source.getPhone());
		
		if(source.getRegion() != null){
			target.setRegion(regionConverter.convert(source.getRegion()));
		} else {
			target.setRegion(null);
		}
		if(source.getCountry() != null){
			target.setCountry(countryConverter.convert(source.getCountry()));
		} else {
			target.setCountry(null);
		}
		if(source.getDistrict() != null){
			target.setDistrict(districtConverter.convert(source.getDistrict()));
		} else {
			target.setDistrict(null);
		}
		
	}
	
	
	@Required
	public void setCountryConverter(
			AbstractPopulatingConverter<Country, CountryData> countryConverter) {
		this.countryConverter = countryConverter;
	}
	
	@Required
	public void setRegionConverter(
			AbstractPopulatingConverter<Region, RegionData> regionConverter) {
		this.regionConverter = regionConverter;
	}

	@Required
	public void setDistrictConverter(AbstractPopulatingConverter<District, DistrictData> districtConverter) {
		this.districtConverter = districtConverter;
	}

}
