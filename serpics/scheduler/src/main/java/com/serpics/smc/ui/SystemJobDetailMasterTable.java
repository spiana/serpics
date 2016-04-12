package com.serpics.smc.ui;

import com.serpics.scheduler.exception.JobSchedulerException;
import com.serpics.scheduler.job.AbstractJob;
import com.serpics.scheduler.model.SystemJobDetails;
import com.serpics.stereotype.VaadinComponent;

@VaadinComponent("systemJobDetailTable")
public class SystemJobDetailMasterTable extends AbstractJobDetailMasterTable<SystemJobDetails> {

	private static final long serialVersionUID = -5987698922480175886L;

	public SystemJobDetailMasterTable() {
		super(SystemJobDetails.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void execSave(SystemJobDetails entity,boolean create) throws ClassNotFoundException, JobSchedulerException {
		if (create){
				jobService.createJobDetail((Class<? extends AbstractJob>) Class.forName(entity.getNameClassJob()), entity);
		}else {
				jobService.modifyJobDetail((Class<? extends AbstractJob>) Class.forName(entity.getNameClassJob()), entity);
		}
		
	}

}
