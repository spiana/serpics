package com.serpics.base.services;

import org.springframework.transaction.annotation.Transactional;

import com.serpics.base.data.model.Media;
import com.serpics.core.data.Repository;

@Transactional(readOnly = true)
public abstract class AbstractMediaService<T extends Media>  implements MediaService<T> {

	public abstract Repository<T, Long> getRepository();

	@Override
	@Transactional
	public T create(T media) {
		return getRepository().saveAndFlush(media);
	}

	
	
	

}
