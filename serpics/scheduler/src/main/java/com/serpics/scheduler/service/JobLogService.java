package com.serpics.scheduler.service;

import java.util.List;

import com.serpics.scheduler.model.JobLog;
import com.serpics.scheduler.model.JobDetails;

public interface JobLogService {
	
	public void addJobLog(String uuidScheduler,JobLog jLog);

	List<JobLog> getLogForJobDetail(JobDetails jobDetail);
	
}