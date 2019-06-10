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
package com.serpics.base.services;

import java.io.IOException;

import javax.annotation.Resource;

import org.springframework.transaction.annotation.Transactional;

import com.serpics.base.commerce.CommerceEngine;
import com.serpics.base.data.model.Media;
import com.serpics.base.strategies.MediaStrategy;
import com.serpics.core.data.Repository;

@Transactional(readOnly = true)
public abstract class AbstractMediaService<T extends Media>  implements MediaService<T> {
	@Resource
	private CommerceEngine engine;
	
	@Resource
	MediaStrategy<Media> mediaStrategy;
	
	public abstract Repository<T, Long> getRepository();
	
	@Override
	@Transactional
	public T create(T media  ) throws IOException{
		 mediaStrategy.create(media);
		return getRepository().saveAndFlush(media);
	}

	@Override
	public T update(T media) throws IOException {
		 mediaStrategy.update(media);
		return getRepository().saveAndFlush(media);
	}
	
	@Override
	public String getMediaUrl(T media) {
		return mediaStrategy.getMediaUrl(media);
	}
}
