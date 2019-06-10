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

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import com.serpics.base.data.model.District;
import com.serpics.base.facade.data.DistrictData;
import com.serpics.base.services.CountryService;
import com.serpics.base.services.DistrictService;
import com.serpics.core.facade.AbstractPopulatingConverter;
import com.serpics.i18n.data.model.MultilingualString;
import com.serpics.stereotype.StoreFacade;


@StoreFacade("districtFacade")
@Transactional(readOnly=true)
public class DistrictFacadeImpl implements DistrictFacade {
	@Autowired
	DistrictService districtService;
	
	@Autowired
	CountryService countryService;
	
	@Resource(name="districtConverter")
	AbstractPopulatingConverter<District, DistrictData> districtConverter;
	
	
	@Override
	public Page<DistrictData> findAll(Pageable page) {
		Page<District> districts = districtService.findAll(page); 
		
		List<DistrictData> clist = new ArrayList<DistrictData>();
		for (District country : districts.getContent()) {
			clist.add(districtConverter.convert(country));
		}
		
		Page<DistrictData> cdata= new PageImpl<DistrictData>(clist ,page , districts.getTotalElements());
		return cdata;
	}
	
	
	@Override
	@Transactional
	public DistrictData addDistrict(DistrictData data) {
		
		String locale = "it";
		final MultilingualString description = new MultilingualString(locale, data.getDescription());
		District r = new District();
		r.setIsoCode(data.getIsoCode());
		r.setDescription(description);
		r = districtService.create(r);
		data = districtConverter.convert(r);
		return data;
	}


	@Override
	public DistrictData findDistrictByUuid(String districtUuid) {
		return districtConverter.convert(districtService.findByUUID(districtUuid));
	}
	
	@Override
	public DistrictData findDistrictByCode(String name) {
		return districtConverter.convert(districtService.getDistrictByCode(name));
	}
	
	@Override
	public List<DistrictData> findDistrictByCountry(Long countryId){
		List<DistrictData> districtList = new ArrayList<DistrictData>();
		for (District district : districtService.getDistrictByCountry(countryId)){
			districtList.add(districtConverter.convert(district));
		}
		return districtList;
	}


	@Override
	public List<DistrictData> findDistrictByRegion(Long regionId) {
		List<DistrictData> districtList = new ArrayList<DistrictData>();
		for (District district : districtService.getDistrictByRegion(regionId)){
			districtList.add(districtConverter.convert(district));
		}
		return districtList;
	}
}
