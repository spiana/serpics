package com.serpics.scheduler.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.serpics.scheduler.model.AbstractSchedulerJob;
import com.serpics.scheduler.model.JobDetails;
import com.serpics.scheduler.model.JobLog;
import com.serpics.scheduler.model.JobLogState;
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
		if(jLog.getState()!=JobLogState.EXCEPTION && schedulerJob.getJobDetail().isLogOnlyError()){
			return;
		}
		jobLogRepository.saveAndFlush(jLog);
	}

	@Override
	public List<JobLog> getLogForJobDetail(JobDetails jobDetail){
		
		return jobLogRepository.findLogFroJobDetails(jobDetail);
	}

	/* (non-Javadoc)
	 * @see com.serpics.scheduler.service.JobLogService#findPendingJobLogForJob(com.serpics.scheduler.model.AbstractSchedulerJob)
	 */
	@Override
	public JobLog findPendingJobLogForJob(final AbstractSchedulerJob job) {
		
		return jobLogRepository.findOne(new Specification<JobLog>() {
			
			@Override
			public Predicate toPredicate(Root<JobLog> arg0, CriteriaQuery<?> arg1,
					CriteriaBuilder arg2) {
				
				return  arg2.and(arg2.equal(arg0.get("schedulerJob"), job),arg2.equal(arg0.get("state"), JobLogState.STARTING));
			}
		});
	}
}
