package com.serpics.catalog.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.serpics.catalog.data.model.Media;
import com.serpics.catalog.data.repositories.MediaRepository;
import com.serpics.commerce.service.AbstractCommerceEntityService;
import com.serpics.core.data.Repository;

@Service("mediaService")
@Scope("store")
@Transactional(readOnly = true)
public class MediaServiceImpl extends AbstractCommerceEntityService<Media, Long>
		implements MediaService {

	@Autowired
	MediaRepository mediaRepository;
	
	@Override
	public Repository<Media, Long> getEntityRepository() {
		return mediaRepository;
	}

	

}
