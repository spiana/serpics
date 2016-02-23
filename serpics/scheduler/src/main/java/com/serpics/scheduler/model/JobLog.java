package com.serpics.scheduler.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.serpics.core.data.jpa.AbstractEntity;

@Entity(name="JobLog")
public class JobLog extends AbstractEntity {

	private static final long serialVersionUID = -5408373595049057734L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "joblog_id", unique = true, nullable = false)
    protected Long id;
	
	@Column(name="state")
	private String state;
	
	@Column(name="message")
	private String message;
	
	@Column(name="date_log")
	private Date dateLog;
	
	@ManyToOne
	@JoinColumn(name="job_id",nullable=false)
	private SerpicsJobDetails jobRunned;

	@ManyToOne
	@JoinColumn(name="scheduler_id",nullable=true)
	private AbstractSchedulerSerpicsJob schedulerSerpicsJob;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public SerpicsJobDetails getJobRunned() {
		return jobRunned;
	}

	public void setJobRunned(SerpicsJobDetails jobRunned) {
		this.jobRunned = jobRunned;
	}

	public AbstractSchedulerSerpicsJob getSchedulerSerpicsJob() {
		return schedulerSerpicsJob;
	}

	public void setSchedulerSerpicsJob(AbstractSchedulerSerpicsJob schedulerSerpicsJob) {
		this.schedulerSerpicsJob = schedulerSerpicsJob;
	}

	public Date getDateLog() {
		return dateLog;
	}

	public void setDateLog(Date dateLog) {
		this.dateLog = dateLog;
	} 
	
	
}
