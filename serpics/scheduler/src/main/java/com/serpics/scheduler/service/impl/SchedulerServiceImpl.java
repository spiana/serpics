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
package com.serpics.scheduler.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.serpics.base.commerce.session.CommerceSessionContext;
import com.serpics.core.service.AbstractService;
import com.serpics.scheduler.model.AbstractSchedulerJob;
import com.serpics.scheduler.repositories.AbstractSchedulerJobRepository;
import com.serpics.scheduler.service.SchedulerService;

@Service("schedulerService")
public class SchedulerServiceImpl extends AbstractService<CommerceSessionContext> implements SchedulerService{
	
	@Resource
	private AbstractSchedulerJobRepository abstractSchedulerJobRepository;
	
	@Override
	public AbstractSchedulerJob getSchedulerJob(String uuid){
		Assert.notNull(uuid);
		return abstractSchedulerJobRepository.findByUUID(uuid);
	}
	
	@Override
	public AbstractSchedulerJob getSchedulerJob(Long id){
		
		Assert.notNull(id);
		
		return abstractSchedulerJobRepository.findOne(id);
		
	}
	@Override
	@Transactional
	public void save(AbstractSchedulerJob scheduler){
		abstractSchedulerJobRepository.save(scheduler);
	}
}
