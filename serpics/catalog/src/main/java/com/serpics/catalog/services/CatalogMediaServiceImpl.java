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
