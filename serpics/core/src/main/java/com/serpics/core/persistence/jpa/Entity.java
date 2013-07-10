package com.serpics.core.persistence.jpa;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@MappedSuperclass
public abstract class Entity {

	@Column(name = "update")
	@Temporal(TemporalType.TIMESTAMP)
	protected Date updated;

	@PrePersist
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
}
