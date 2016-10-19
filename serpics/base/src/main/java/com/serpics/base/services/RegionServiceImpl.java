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
import com.serpics.base.data.model.Region;
import com.serpics.base.data.repositories.CountryRepository;
import com.serpics.base.data.repositories.RegionRepository;
import com.serpics.core.data.Repository;
import com.serpics.core.service.AbstractEntityService;
import com.serpics.core.session.SessionContext;

@Service("regionService")
@Scope("store")
public class RegionServiceImpl extends AbstractEntityService<Region, Long, SessionContext> implements RegionService{
	
	@Autowired
	RegionRepository regionRepository;
	
	@Autowired
	CountryRepository countryRepository;
	
	@Override
	public Repository<Region, Long> getEntityRepository() {
		return regionRepository;
	}
	
	@Override
	public Set<Region> getRegionByCountry(Long countryId){
		Assert.notNull(countryId);
		Country country = countryRepository.findOne(countryId);
		return country.getRegions();
	}
	
	@Override
	public Region getRegionByCode(String IsoCode){
		return regionRepository.getRegionByCode(IsoCode);
	}
	
	
}


