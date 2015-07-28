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
	public GeocodeData addGeocode(GeocodeData cd) {
		Geocode g = new Geocode();
		g.setCode(cd.getCode());
		g= geocodeService.create(g);
		
		cd = geocodeConvert.convert(g);
		return cd;
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