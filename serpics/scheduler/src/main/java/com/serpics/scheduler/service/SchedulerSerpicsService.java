package com.serpics.scheduler.service;

import com.serpics.scheduler.model.AbstractSchedulerSerpicsJob;

public interface SchedulerSerpicsService {

	AbstractSchedulerSerpicsJob getSchedulerSerpicsJob(String uuid);

	AbstractSchedulerSerpicsJob getSchedulerSerpicsJob(Long id);

	void save(AbstractSchedulerSerpicsJob scheduler);

}
