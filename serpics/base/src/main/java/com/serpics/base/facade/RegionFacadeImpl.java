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

import com.serpics.base.data.model.MultilingualString;
import com.serpics.base.data.model.Region;
import com.serpics.base.facade.data.RegionData;
import com.serpics.base.services.CountryService;
import com.serpics.base.services.RegionService;
import com.serpics.core.facade.AbstractPopulatingConverter;
import com.serpics.stereotype.StoreFacade;


@StoreFacade("regionFacade")
@Transactional(readOnly=true)
public class RegionFacadeImpl implements RegionFacade {
	@Autowired
	RegionService regionService;
	
	@Autowired
	CountryService countryService;
	
	@Resource(name="regionConverter")
	AbstractPopulatingConverter<Region, RegionData> regionConverter;
	
	
	@Override
	public Page<RegionData> findAll(Pageable page) {
		Page<Region> regions = regionService.findAll(page); 
		
		List<RegionData> clist = new ArrayList<RegionData>();
		for (Region country : regions.getContent()) {
			clist.add(regionConverter.convert(country));
		}
		
		Page<RegionData> cdata= new PageImpl<RegionData>(clist ,page , regions.getTotalElements());
		return cdata;
	}
	
	
	@Override
	@Transactional
	public RegionData addRegion(RegionData data) {
		
		String locale = "it";
		final MultilingualString description = new MultilingualString(locale, data.getDescription());
		Region r = new Region();
		r.setIsoCode(data.getIsoCode());
		r.setDescription(description);
		r = regionService.create(r);
		data = regionConverter.convert(r);
		return data;
	}


	@Override
	public RegionData findRegionByUuid(String regionUuid) {
		return regionConverter.convert(regionService.findByUUID(regionUuid));
	}
	
	@Override
	public RegionData findRegionByCode(String isoCode) {
		return regionConverter.convert(regionService.getRegionByCode(isoCode));
	}
	
	@Override
	public List<RegionData> findRegionByCountry(Long countryId){
		List<RegionData> regionList = new ArrayList<RegionData>();
		for (Region region : regionService.getRegionByCountry(countryId)){
			regionList.add(regionConverter.convert(region));
		}
		return regionList;
	}
}
