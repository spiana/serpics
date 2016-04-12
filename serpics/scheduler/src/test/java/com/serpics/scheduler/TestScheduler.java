package com.serpics.scheduler;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.junit.Assert;
import org.junit.Before;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.serpics.base.data.model.Store;
import com.serpics.base.data.repositories.LocaleRepository;
import com.serpics.base.data.repositories.StoreRepository;
import com.serpics.catalog.services.CatalogService;
import com.serpics.commerce.core.CommerceEngine;
import com.serpics.commerce.session.CommerceSessionContext;
import com.serpics.core.SerpicsException;
import com.serpics.membership.services.BaseService;
import com.serpics.scheduler.exception.JobSchedulerException;
import com.serpics.scheduler.job.TestJob;
import com.serpics.scheduler.job.TestJobInError;
import com.serpics.scheduler.model.AbstractSchedulerJob;
import com.serpics.scheduler.model.JobDetailState;
import com.serpics.scheduler.model.JobDetails;
import com.serpics.scheduler.model.JobLog;
import com.serpics.scheduler.model.JobLogState;
import com.serpics.scheduler.model.TriggerJob;
import com.serpics.scheduler.service.JobLogService;
import com.serpics.scheduler.service.JobService;
import com.serpics.scheduler.service.SchedulerService;

@ContextConfiguration({ "classpath:META-INF/applicationContext.xml", "classpath:META-INF/core-serpics.xml","classpath:META-INF/base-serpics.xml", "classpath:META-INF/membership-serpics.xml",
		"classpath:META-INF/catalog-serpics.xml", "classpath:META-INF/warehouse-serpics.xml",
		"classpath:META-INF/importexport-serpics.xml", "classpath:META-INF/scheduler-serpics.xml" })
@TransactionConfiguration(defaultRollback = true )
public class TestScheduler extends AbstractJUnit4SpringContextTests {
	
	@Autowired
	BaseService baseService;

	@Autowired
	CommerceEngine commerceEngine;

	@Autowired
	CatalogService catalogService;

	CommerceSessionContext context;
	
	@Autowired
	StoreRepository storeRepository;
	
	@Autowired
	LocaleRepository localeRepository;
	
	@Autowired
	JobService jobService;
	
	@Autowired
	SchedulerService schedulerService;
	
	@Autowired
	JobLogService jobLogService;
	
	@Autowired
	private Scheduler scheduler;
	
	
	@Before
	public void setUp() throws SerpicsException, SchedulerException {
		if (!baseService.isInitialized()) {
			baseService.initIstance();
		}
		context = commerceEngine.connect("default-store", "superuser", "admin".toCharArray());
		context.setLocale(localeRepository.findByLanguage("en"));
		catalogService.initialize();

		commerceEngine.disconnect(context);
		scheduler.start();
	}
	
	@org.junit.Test
	public void testJobSuccesful() throws InterruptedException, JobSchedulerException{
		String uuidTrigger = scheduleJobTest();
		Thread.sleep(10000);
		AbstractSchedulerJob abstractScheduler = schedulerService.getSchedulerJob(uuidTrigger);
		Assert.assertEquals("Number of iteration",3,((TriggerJob)abstractScheduler).getItereted().intValue());
		
		List<JobLog> logs = jobLogService.getLogForJobDetail(abstractScheduler.getJobDetail());
		
		Assert.assertEquals("Number of job logs", 3, logs.size());
		
		int numberOfSuccess = CollectionUtils.countMatches(logs, new Predicate() {
			
			@Override
			public boolean evaluate(Object arg0) {
				if(arg0 instanceof JobLog){
					return JobLogState.FINISHED.equals(((JobLog)arg0).getState());
				}
				return false;
			}
		});
		
		Assert.assertEquals("Number of success", 3, numberOfSuccess);
	}
	
	@org.junit.Test
	public void testError() throws InterruptedException, JobSchedulerException{
		String uuidTrigger = scheduleJobTestInError();
		Thread.sleep(10000);
		AbstractSchedulerJob abstractScheduler = schedulerService.getSchedulerJob(uuidTrigger);
		Assert.assertEquals("Number of iteration",1,((TriggerJob)abstractScheduler).getItereted().intValue());
		
		List<JobLog> logs = jobLogService.getLogForJobDetail(abstractScheduler.getJobDetail());
		
		Assert.assertEquals("Number of job logs", 2, logs.size());
		
		int numberOfexception = CollectionUtils.countMatches(logs, new Predicate() {
			
			@Override
			public boolean evaluate(Object arg0) {
				if(arg0 instanceof JobLog){
					return JobLogState.EXCEPTION.equals(((JobLog)arg0).getState());
				}
				return false;
			}
		});
		
		Assert.assertEquals("Number of success", 1, numberOfexception);
		
		Assert.assertEquals("State of Job must be paused",JobDetailState.PAUSED,abstractScheduler.getJobDetail().getStateOfJob());
	}
	
	public String scheduleJobTest() throws JobSchedulerException{
		
		Store store = storeRepository.findByname("default-store");
		JobDetails jobdetails = jobService.createJobDetail(TestJob.class, store, null);
		
		TriggerJob trigger = new TriggerJob();
		trigger.setJobDetail(jobdetails);
		trigger.setNumberOfIteration(2);
		trigger.setSecondsInterval(2);
		
		trigger = (TriggerJob)jobService.createTrigger(trigger, jobdetails);
		return trigger.getUuid();
	}
	
	public String scheduleJobTestInError() throws JobSchedulerException{
		
		Store store = storeRepository.findByname("default-store");
		
		JobDetails jobDetails = new JobDetails();
		jobDetails.setStore(store);
		jobDetails.setCatalog(null);
		jobDetails.setNameClassJob(TestJobInError.class.getCanonicalName());
		jobDetails.setStopOnFail(true);
		
		JobDetails jobdetails = jobService.createJobDetail(TestJobInError.class, jobDetails);
		
		TriggerJob trigger = new TriggerJob();
		trigger.setJobDetail(jobdetails);
		trigger.setNumberOfIteration(0);
		trigger.setSecondsInterval(2);
		
		trigger = (TriggerJob)jobService.createTrigger(trigger, jobdetails);
		return trigger.getUuid();
	}
}
