package com.serpics.catalog.services;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.serpics.base.services.AbstractMediaService;
import com.serpics.catalog.data.model.CtentryMedia;
import com.serpics.catalog.data.repositories.CtentryMediaRepository;
import com.serpics.core.data.Repository;

@Service("catalogMediaService")
@Scope("store")
public class CatalogMediaServiceImpl extends AbstractMediaService<CtentryMedia> implements CatalogMediaService{

	@Resource
	CtentryMediaRepository mediarepository;
	
	@Override
	public Repository<CtentryMedia, Long> getRepository() {
		return  mediarepository;
	}
	

}
