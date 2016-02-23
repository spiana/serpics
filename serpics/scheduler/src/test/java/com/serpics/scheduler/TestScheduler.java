package com.serpics.scheduler;

import org.junit.Assert;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.serpics.base.data.model.Store;
import com.serpics.base.data.repositories.LocaleRepository;
import com.serpics.catalog.services.CatalogService;
import com.serpics.commerce.core.CommerceEngine;
import com.serpics.commerce.session.CommerceSessionContext;
import com.serpics.core.SerpicsException;
import com.serpics.membership.data.repositories.StoreRepository;
import com.serpics.membership.services.BaseService;
import com.serpics.scheduler.exception.SerpicsSchedulerException;
import com.serpics.scheduler.job.TestSerpicsJob;
import com.serpics.scheduler.model.AbstractSchedulerSerpicsJob;
import com.serpics.scheduler.model.SerpicsJobDetails;
import com.serpics.scheduler.model.TriggerJob;
import com.serpics.scheduler.service.JobService;
import com.serpics.scheduler.service.SchedulerSerpicsService;

@ContextConfiguration({ "classpath:META-INF/applicationContext.xml", "classpath:META-INF/core-serpics.xml","classpath:META-INF/base-serpics.xml", "classpath:META-INF/membership-serpics.xml",
		"classpath:META-INF/catalog-serpics.xml", "classpath:META-INF/warehouse-serpics.xml",
		"classpath:META-INF/importexport-serpics.xml", "classpath:META-INF/scheduler-serpics.xml" })
@TransactionConfiguration(defaultRollback = false )
public class TestScheduler extends AbstractJUnit4SpringContextTests {
	
	private String uuidTrigger ="";
	
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
	SchedulerSerpicsService schedulerSerpicsService;
	
	@Before
	public void setUp() throws SerpicsException {
		if (!baseService.isInitialized()) {
			baseService.initIstance();
		}
		context = commerceEngine.connect("default-store", "superuser", "admin".toCharArray());
		context.setLocale(localeRepository.findByLanguage("en"));
		catalogService.initialize();

		commerceEngine.disconnect(context);
	}
	
	@org.junit.Test
	public void UNO() throws InterruptedException, SerpicsSchedulerException{
		scheduleJobTest();
		Thread.sleep(10000);
		AbstractSchedulerSerpicsJob abstractScheduler = schedulerSerpicsService.getSchedulerSerpicsJob(uuidTrigger);
		Assert.assertEquals("Number of iteration",3,((TriggerJob)abstractScheduler).getItereted().intValue());
	}
	
	public void scheduleJobTest() throws SerpicsSchedulerException{
		
		Store store = storeRepository.findByname("default-store");
		SerpicsJobDetails jobdetails = jobService.createJobDetail(TestSerpicsJob.class, store, null);
		
		TriggerJob trigger = new TriggerJob();
		trigger.setJobDetail(jobdetails);
		trigger.setNumberOfIteration(2);
		trigger.setSecondsInterval(2);
		
		trigger = (TriggerJob)jobService.createTrigger(trigger, jobdetails);
		uuidTrigger = trigger.getUuid();
	}
}
