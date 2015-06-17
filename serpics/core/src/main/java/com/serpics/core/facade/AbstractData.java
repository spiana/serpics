package com.serpics.core.facade;

import java.util.Date;

public abstract class AbstractData {
	
	protected Date updated;
	
	protected  Date created;
	
	protected String uuid;

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

}
