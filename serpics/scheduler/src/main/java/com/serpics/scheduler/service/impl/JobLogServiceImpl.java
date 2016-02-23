package com.serpics.scheduler.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.serpics.commerce.session.CommerceSessionContext;
import com.serpics.core.service.AbstractService;
import com.serpics.scheduler.model.AbstractSchedulerSerpicsJob;
import com.serpics.scheduler.model.JobLog;
import com.serpics.scheduler.repositories.JobLogRepository;
import com.serpics.scheduler.service.JobLogService;
import com.serpics.scheduler.service.SchedulerSerpicsService;

@Service("jobLogService")
public class JobLogServiceImpl implements JobLogService {

	@Resource
	private SchedulerSerpicsService schedulerSerpicsService;
	
	@Resource
	private JobLogRepository jobLogRepository;
	
	@Override
	@Transactional
	public void addJobLog(String uuidScheduler, JobLog jLog) {
		
		AbstractSchedulerSerpicsJob schedulerJob = schedulerSerpicsService.getSchedulerSerpicsJob(uuidScheduler);
		
		jLog.setSchedulerSerpicsJob(schedulerJob);
		jLog.setJobRunned(schedulerJob.getJobDetail());
		
		jobLogRepository.saveAndFlush(jLog);
	}

}
