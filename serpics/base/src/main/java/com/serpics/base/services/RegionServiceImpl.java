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
	
	
}


