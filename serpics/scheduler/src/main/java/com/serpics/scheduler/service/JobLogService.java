package com.serpics.scheduler.service;

import java.util.List;

import com.serpics.scheduler.model.AbstractSchedulerJob;
import com.serpics.scheduler.model.JobDetails;
import com.serpics.scheduler.model.JobLog;

public interface JobLogService {
	
	public void addJobLog(String uuidScheduler,JobLog jLog);

	List<JobLog> getLogForJobDetail(JobDetails jobDetail);
	
	public JobLog findPendingJobLogForJob(AbstractSchedulerJob job);
}
