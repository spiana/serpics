package com.serpics.scheduler.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.serpics.core.data.jpa.AbstractEntity;

@Entity(name="JobLog")
@Table(name="job_scheduler_log")
public class JobLog extends AbstractEntity {

	private static final long serialVersionUID = -5408373595049057734L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "joblog_id", unique = true, nullable = false)
    protected Long id;
	
	@Column(name="state")
	private JobLogState state;
	
	@Column(name="message")
	private String message;
	
	@Column(name="date_log")
	private Date dateLog;
	
	@ManyToOne
	@JoinColumn(name="job_id",nullable=false)
	private JobDetails jobRunned;

	@ManyToOne
	@JoinColumn(name="scheduler_id",nullable=true)
	private AbstractSchedulerJob schedulerJob;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public JobLogState getState() {
		return state;
	}

	public void setState(JobLogState state) {
		this.state = state;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public JobDetails getJobRunned() {
		return jobRunned;
	}

	public void setJobRunned(JobDetails jobRunned) {
		this.jobRunned = jobRunned;
	}

	public AbstractSchedulerJob getSchedulerJob() {
		return schedulerJob;
	}

	public void setSchedulerJob(AbstractSchedulerJob schedulerJob) {
		this.schedulerJob = schedulerJob;
	}

	public Date getDateLog() {
		return dateLog;
	}

	public void setDateLog(Date dateLog) {
		this.dateLog = dateLog;
	} 
	
	
}