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

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.serpics.base.facade.data.DistrictData;



public interface DistrictFacade {
	public Page<DistrictData> findAll(Pageable page);
	public DistrictData addDistrict( DistrictData district);
	public DistrictData findDistrictByUuid(String districtUuid);
	public List<DistrictData> findDistrictByCountry(Long countryId);
	public List<DistrictData> findDistrictByRegion(Long regionId);
	public DistrictData findDistrictByCode(String name);
}
