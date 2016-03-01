package com.serpics.scheduler.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.serpics.base.data.model.Store;
import com.serpics.catalog.data.model.Catalog;
import com.serpics.core.data.jpa.AbstractEntity;

/**
 * Entity show details of job which can be reschedule in various type.
 * 
 * @author alessandro.marasco@tinvention.net
 *
 */
@Entity
@Table(name = "job_details")
public class JobDetails extends AbstractEntity {

	private static final long serialVersionUID = 7129859920615052949L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "jobDetail_id", unique = true, nullable = false)
    protected Long id;
	
	@Column(name="name_class")
	private String nameClassJob;
	
	@ManyToOne
	@JoinColumn(name="store_id")
	private Store store;
	
	@ManyToOne
	@JoinColumn(name="catalog_id",nullable=true)
	private Catalog catalog;
	
	@Column(name="last_run_date")
	private Date lastRun;
	
	@Column(name="state_of_job")
	private JobDetailState stateOfJob;
	
	@Column(name="stop_on_fail")
	private boolean stopOnFail;
	
	@OneToMany(fetch=FetchType.LAZY,orphanRemoval=true,mappedBy="jobRunned")
	private List<JobLog> logs;

	@OneToMany(fetch=FetchType.LAZY,orphanRemoval=true,mappedBy="jobDetail")
	private List<AbstractSchedulerJob> schedulers;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNameClassJob() {
		return nameClassJob;
	}

	public void setNameClassJob(String nameClassJob) {
		this.nameClassJob = nameClassJob;
	}

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	public Catalog getCatalog() {
		return catalog;
	}

	public void setCatalog(Catalog catalog) {
		this.catalog = catalog;
	}

	public Date getLastRun() {
		return lastRun;
	}

	public void setLastRun(Date lastRun) {
		this.lastRun = lastRun;
	}

	public JobDetailState getStateOfJob() {
		return stateOfJob;
	}

	public void setStateOfJob(JobDetailState stateOfJob) {
		this.stateOfJob = stateOfJob;
	}

	public boolean isStopOnFail() {
		return stopOnFail;
	}

	public void setStopOnFail(boolean stopOnFail) {
		this.stopOnFail = stopOnFail;
	}

	public List<JobLog> getLogs() {
		return logs;
	}

	public void setLogs(List<JobLog> logs) {
		this.logs = logs;
	}

	public List<AbstractSchedulerJob> getSchedulers() {
		return schedulers;
	}

	public void setSchedulers(List<AbstractSchedulerJob> schedulers) {
		this.schedulers = schedulers;
	}
	
}
