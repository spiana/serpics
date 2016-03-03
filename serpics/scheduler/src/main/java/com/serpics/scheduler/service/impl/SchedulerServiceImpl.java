package com.serpics.scheduler.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.serpics.commerce.session.CommerceSessionContext;
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
