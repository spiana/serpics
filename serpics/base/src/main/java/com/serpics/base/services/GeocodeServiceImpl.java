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


