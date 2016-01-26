package com.serpics.scheduler;

import java.util.List;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.serpics.test.AbstractTransactionalJunit4SerpicTest;

@ContextConfiguration({ "classpath:META-INF/base-serpics.xml", "classpath:META-INF/membership-serpics.xml",
		"classpath:META-INF/catalog-serpics.xml", "classpath:META-INF/warehouse-serpics.xml",
		"classpath:META-INF/importexport-serpics.xml", "classpath:META-INF/scheduler-serpics.xml" })
@TransactionConfiguration(defaultRollback = false )
public class TestScheduler extends AbstractTransactionalJunit4SerpicTest {
	

    @Autowired
    private SchedulerFactoryBean quartzScheduler;
	
	
	@org.junit.Test
	public void UNO() throws InterruptedException, SchedulerException{
		
			Scheduler scheduler = quartzScheduler.getScheduler();
			scheduler.start();
			JobDetail jobdetail = scheduler.getJobDetail(new JobKey("JOBTEST"));
			if(jobdetail==null){
				jobdetail = JobBuilder.newJob(com.serpics.scheduler.job.JobTest.class).withIdentity("JOBTEST").build();
			}
			scheduler.scheduleJob(
					jobdetail, 
				TriggerBuilder.newTrigger().startNow().withSchedule(SimpleScheduleBuilder.simpleSchedule()
				.withIntervalInSeconds(1).withRepeatCount(5))
				.build());
			
			scheduler.start();
			
		Thread.sleep(30000);
	}
}
