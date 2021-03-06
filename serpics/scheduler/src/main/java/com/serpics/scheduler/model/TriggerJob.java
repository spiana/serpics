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

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@DiscriminatorValue(value = "1")
public class TriggerJob extends AbstractSchedulerJob {

	private static final long serialVersionUID = -6332264961086971500L;
	
	@Column(name="RepeatCount")
	private Integer numberOfIteration;
	
	@Column(name="number_triggered")
	private Integer itereted;
	
	@Column(name="start_at", nullable=true)
	@Temporal(TemporalType.TIMESTAMP)
	private Date whenStart;
	
	@Column(name="end_to", nullable=true)
	@Temporal(TemporalType.TIMESTAMP)
	private Date whenEnd;

	@Column(name="seconds_interval", nullable=true)
	private Integer secondsInterval;
	
	public Integer getNumberOfIteration() {
		return numberOfIteration;
	}

	public void setNumberOfIteration(Integer numberOfIteration) {
		this.numberOfIteration = numberOfIteration;
	}

	public Integer getItereted() {
		return itereted;
	}

	public void setItereted(Integer itereted) {
		this.itereted = itereted;
	}

	public Date getWhenStart() {
		return whenStart;
	}

	public void setWhenStart(Date whenStart) {
		this.whenStart = whenStart;
	}

	public Date getWhenEnd() {
		return whenEnd;
	}

	public void setWhenEnd(Date whenEnd) {
		this.whenEnd = whenEnd;
	}

	
	public Integer getSecondsInterval() {
		return secondsInterval;
	}

	public void setSecondsInterval(Integer secondsInterval) {
		this.secondsInterval = secondsInterval;
	}

	@Transient
	public String getStringDetail() {
		StringBuilder builder = new StringBuilder();
		builder.append("TriggerJob [numberOfIteration=");
		builder.append(numberOfIteration);
		builder.append(", itereted=");
		builder.append(itereted);
		builder.append(", whenStart=");
		builder.append(whenStart);
		builder.append(", whenEnd=");
		builder.append(whenEnd);
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
