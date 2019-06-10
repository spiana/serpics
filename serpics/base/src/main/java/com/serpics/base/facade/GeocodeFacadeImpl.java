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

import com.serpics.base.data.model.Geocode;
import com.serpics.base.facade.data.GeocodeData;
import com.serpics.base.services.GeocodeService;
import com.serpics.core.facade.AbstractPopulatingConverter;
import com.serpics.i18n.data.model.MultilingualString;
import com.serpics.stereotype.StoreFacade;

@StoreFacade("geocodeFacade")
@Transactional(readOnly=true)
public class GeocodeFacadeImpl implements GeocodeFacade {
	@Autowired
	GeocodeService geocodeService;
	
	@Resource(name="geocodeConverter")
	AbstractPopulatingConverter<Geocode, GeocodeData> geocodeConvert;
	
	@Override
	@Transactional
	public GeocodeData addGeocode(GeocodeData data) {
		String locale = "it";
		final MultilingualString description = new MultilingualString(locale, data.getDescription());
		
		Geocode g = new Geocode();
		g.setCode(data.getName());
		g.setDescription(description);
		g= geocodeService.create(g);
		
		data = geocodeConvert.convert(g);
		return data;
	} 
	
	
	@Override
	public Page<GeocodeData> findAll(Pageable page) {
		Page<Geocode> geo = geocodeService.findAll(page); 
		
		List<GeocodeData> clist = new ArrayList<GeocodeData>();
		for (Geocode g : geo.getContent()) {
			clist.add(geocodeConvert.convert(g));
		}
		
		Page<GeocodeData> cdata= new PageImpl<GeocodeData>(clist ,page , geo.getTotalElements());
		return cdata;
	}
	
}