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
package com.serpics.base.services;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.serpics.base.data.model.Country;
import com.serpics.base.data.model.District;
import com.serpics.base.data.model.Region;
import com.serpics.base.data.repositories.CountryRepository;
import com.serpics.base.data.repositories.DistrictRepository;
import com.serpics.base.data.repositories.RegionRepository;
import com.serpics.core.data.Repository;
import com.serpics.core.service.AbstractEntityService;
import com.serpics.core.session.SessionContext;

@Service("districtService")
@Scope("store")
public class DistrictServiceImpl extends AbstractEntityService<District, Long, SessionContext> implements DistrictService{
	
	@Autowired
	DistrictRepository districtRepository;
	
	@Autowired
	RegionRepository regionRepository;
	
	@Autowired
	CountryRepository countryRepository;

	@Override
	public Set<District> getDistrictByCountry(Long countryId) {
		Assert.notNull(countryId);
		Country country = countryRepository.findOne(countryId);
		return country.getDistricts();
	}

	@Override
	public Set<District> getDistrictByRegion(Long regionId) {
		Assert.notNull(regionId);
		Region region = regionRepository.findOne(regionId);
		return region.getDistricts();
	}

	@Override
	public District getDistrictByCode(String isoCode) {
		return districtRepository.getDistrictByIsoCode(isoCode);
	}

	@Override
	public Repository<District, Long> getEntityRepository() {
		return districtRepository;
	}
	
	
}


