package com.serpics.scheduler.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
@DiscriminatorValue(value = "0")
public class CronJob extends AbstractSchedulerJob {

	private static final long serialVersionUID = -2714360207621812096L;
	
	@Column(name="cronexpression")
	private String cronExpression;
	
	
	public String getCronExpression() {
		return cronExpression;
	}


	public void setCronExpression(String cronExpression) {
		this.cronExpression = cronExpression;
	}

	@Transient
	public String getStringDetail() {
		StringBuilder builder = new StringBuilder();
		builder.append("CronJob [cronExpression=");
		builder.append(cronExpression);
		builder.append(", getId()=");
		builder.append(getId());
		builder.append(", getJobDetail()=");
		if(getJobDetail()!=null){
			builder.append(" [ name: ");
			builder.append(getJobDetail().getNameClassJob());
			builder.append(" , uuid: ");
			builder.append(getJobDetail().getUuid());
			builder.append(" ]");
		}else{
			builder.append(getJobDetail());
		}
		
		builder.append(", getLastRun()=");
		builder.append(getLastRun());
		builder.append(", getNextRun()=");
		builder.append(getNextRun());
		builder.append("]");
		return builder.toString();
	}
	
}
