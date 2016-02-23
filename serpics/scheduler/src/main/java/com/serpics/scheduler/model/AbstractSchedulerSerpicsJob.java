package com.serpics.scheduler.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.serpics.core.data.jpa.AbstractEntity;

@Entity
@Table(name="scheduler_serpics_job")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="type_of_scheduler",discriminatorType = DiscriminatorType.INTEGER)
public class AbstractSchedulerSerpicsJob extends AbstractEntity{
	
	private static final long serialVersionUID = -5324712912301319990L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "jobscheduler_id", unique = true, nullable = false)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="jobdetail_id")
	private SerpicsJobDetails jobDetail;
	
	@Column(name="last_run_date")
	private Date lastRun;
	
	@Column(name="next_run_date")
	private Date nextRun;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public SerpicsJobDetails getJobDetail() {
		return jobDetail;
	}

	public void setJobDetail(SerpicsJobDetails jobDetail) {
		this.jobDetail = jobDetail;
	}

	public Date getLastRun() {
		return lastRun;
	}

	public void setLastRun(Date lastRun) {
		this.lastRun = lastRun;
	}

	public Date getNextRun() {
		return nextRun;
	}

	public void setNextRun(Date nextRun) {
		this.nextRun = nextRun;
	}
	
}
