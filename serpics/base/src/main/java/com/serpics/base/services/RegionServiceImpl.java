package com.serpics.base.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;


import org.springframework.transaction.annotation.Transactional;

import com.serpics.base.data.model.Region;
import com.serpics.base.data.repositories.RegionRepository;
import com.serpics.core.data.Repository;
import com.serpics.core.service.AbstractEntityService;
import com.serpics.core.session.SessionContext;

@Service("regionService")
@Scope("store")
public class RegionServiceImpl extends AbstractEntityService<Region, Long, SessionContext> implements RegionService{
	
	@Autowired
	RegionRepository regionRepository;
	
	@Override
	public Repository<Region, Long> getEntityRepository() {
		return regionRepository;
	}
	
	
}


