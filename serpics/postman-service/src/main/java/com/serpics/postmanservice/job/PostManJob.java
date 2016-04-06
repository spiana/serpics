package com.serpics.postmanservice.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import com.serpics.postman.model.MetaDataMail;
import com.serpics.postmanservice.service.SendEmailService;
import com.serpics.scheduler.job.AbstractJob;

public class PostManJob extends AbstractJob {

	private int numberOfMail;
	
	@Autowired
	private SendEmailService sendEmailService;
	
	@Override
	protected void executeJob(JobExecutionContext jobcontext) throws JobExecutionException {
		
		if(numberOfMail<=0){
			numberOfMail=10;
		}
		
		logger.info("Retrieve the first {} mail in state NEW and RETRY",numberOfMail);
		
		Page<MetaDataMail> listOfMailToSend = sendEmailService.getListOfMailToProcess(numberOfMail);
		
		logger.info("Find {} mail to send",listOfMailToSend.getSize());
		if(!listOfMailToSend.getContent().isEmpty()){
			for(MetaDataMail metaDataMail : listOfMailToSend.getContent()){
				logger.debug("Try to send mail with {} ",metaDataMail.toString());
				sendEmailService.sendMetaDataMail(metaDataMail);
			}
		}
		
	}

	public int getNumberOfMail() {
		return numberOfMail;
	}

	public void setNumberOfMail(int numberOfMail) {
		this.numberOfMail = numberOfMail;
	}
	
	

}
