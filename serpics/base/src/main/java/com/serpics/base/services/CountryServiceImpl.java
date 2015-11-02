package com.serpics.base.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.serpics.base.data.model.Country;
import com.serpics.base.data.repositories.CountryRepository;
import com.serpics.core.data.Repository;
import com.serpics.core.service.AbstractEntityService;
import com.serpics.core.session.SessionContext;

@Service("countryService")
@Scope("store")
public class CountryServiceImpl extends AbstractEntityService<Country, Long, SessionContext> implements CountryService{
	
	@Autowired
	CountryRepository countryRepository;
	
	@Override
	public Repository<Country, Long> getEntityRepository() {
		return countryRepository;
	}
	
	
}


