package com.serpics.core.persistence.jpa;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@MappedSuperclass
public abstract class AbstractEntity {

	@Column(name = "updated")
	@Temporal(TemporalType.TIMESTAMP)
	protected Date updated;
	
	@Column(name = "created")
	@Temporal(TemporalType.TIMESTAMP)
	protected  Date created;
	
	@Column(name = "uuid", nullable = false, length = 100 , updatable=false)
	protected String uuid;

	@PrePersist
	public void  beforePersist(){
		setUpdated(new Date());
		setCreated(getUpdated());
		if (this.uuid == null)
			this.uuid = UUID.randomUUID().toString();
	}		
	
	@PreUpdate
	public void beforeUpdate() {
		setUpdated(new Date());
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}
	
	public String getUuid() {
		return this.uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public Date getCreated() {
		return created;
	}


	public void setCreated(Date created) {
		this.created = created;
	}
}
