package com.serpics.scheduler.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.serpics.commerce.session.CommerceSessionContext;
import com.serpics.core.service.AbstractService;
import com.serpics.scheduler.model.AbstractSchedulerSerpicsJob;
import com.serpics.scheduler.repositories.AbstractSchedulerSerpicsJobRepository;
import com.serpics.scheduler.service.SchedulerSerpicsService;

@Service("schedulerSerpicsService")
public class SchedulerSerpicsServiceImpl extends AbstractService<CommerceSessionContext> implements SchedulerSerpicsService{
	
	@Resource
	private AbstractSchedulerSerpicsJobRepository abstractSchedulerSerpicsJobRepository;
	
	@Override
	public AbstractSchedulerSerpicsJob getSchedulerSerpicsJob(String uuid){
		Assert.notNull(uuid);
		return abstractSchedulerSerpicsJobRepository.findByUUID(uuid);
	}
	
	@Override
	public AbstractSchedulerSerpicsJob getSchedulerSerpicsJob(Long id){
		
		Assert.notNull(id);
		
		return abstractSchedulerSerpicsJobRepository.findOne(id);
		
	}
	@Override
	@Transactional
	public void save(AbstractSchedulerSerpicsJob scheduler){
		abstractSchedulerSerpicsJobRepository.save(scheduler);
	}
}
