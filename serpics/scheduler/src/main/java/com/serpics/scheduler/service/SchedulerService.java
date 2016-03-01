package com.serpics.scheduler.service;

import com.serpics.scheduler.model.AbstractSchedulerJob;

public interface SchedulerService {

	AbstractSchedulerJob getSchedulerJob(String uuid);

	AbstractSchedulerJob getSchedulerJob(Long id);

	void save(AbstractSchedulerJob scheduler);

}
