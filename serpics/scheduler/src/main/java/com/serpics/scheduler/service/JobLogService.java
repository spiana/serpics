package com.serpics.scheduler.service;

import com.serpics.scheduler.model.JobLog;

public interface JobLogService {
	
	public void addJobLog(String uuidScheduler,JobLog jLog);
	
}
