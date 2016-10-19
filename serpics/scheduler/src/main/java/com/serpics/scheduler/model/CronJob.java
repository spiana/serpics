/*******************************************************************************
 * Copyright 2014-2016  StepFour Srl
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *  
 *      http://www.apache.org/licenses/LICENSE-2.0
 *  
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *  
 *******************************************************************************/
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
