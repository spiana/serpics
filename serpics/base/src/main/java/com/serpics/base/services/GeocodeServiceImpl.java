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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.serpics.base.data.model.Geocode;
import com.serpics.base.data.repositories.GeoCodeRepository;
import com.serpics.core.data.Repository;
import com.serpics.core.service.AbstractEntityService;
import com.serpics.core.session.SessionContext;

@Service("geocodeService")
@Scope("store")
public class GeocodeServiceImpl extends AbstractEntityService<Geocode, Long, SessionContext> implements GeocodeService{
	
	@Autowired
	GeoCodeRepository geocodeReposiotry;
	
	@Override
	public Repository<Geocode, Long> getEntityRepository() {
		return geocodeReposiotry;
	}
	
	
}


