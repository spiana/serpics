package com.serpics.base.facade;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.serpics.base.facade.data.GeocodeData;



public interface GeocodeFacade {
	public GeocodeData addGeocode(GeocodeData geocode);
	public Page<GeocodeData> findAll(Pageable page);
	
}
