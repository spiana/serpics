package com.serpics.scheduler.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.serpics.commerce.session.CommerceSessionContext;
import com.serpics.core.service.AbstractService;
import com.serpics.scheduler.model.AbstractSchedulerJob;
import com.serpics.scheduler.model.JobLog;
import com.serpics.scheduler.model.JobDetails;
import com.serpics.scheduler.repositories.JobLogRepository;
import com.serpics.scheduler.service.JobLogService;
import com.serpics.scheduler.service.SchedulerService;

@Service("jobLogService")
public class JobLogServiceImpl implements JobLogService {

	@Resource
	private SchedulerService schedulerService;
	
	@Resource
	private JobLogRepository jobLogRepository;
	
	@Override
	@Transactional
	public void addJobLog(String uuidScheduler, JobLog jLog) {
		
		AbstractSchedulerJob schedulerJob = schedulerService.getSchedulerJob(uuidScheduler);
		
		jLog.setSchedulerJob(schedulerJob);
		jLog.setJobRunned(schedulerJob.getJobDetail());
		
		jobLogRepository.saveAndFlush(jLog);
	}

	@Override
	public List<JobLog> getLogForJobDetail(JobDetails jobDetail){
		
		return jobLogRepository.findLogFroJobDetails(jobDetail);
	}
}
