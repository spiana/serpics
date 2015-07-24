package com.serpics.core.facade;

import java.util.Date;

import javax.xml.bind.annotation.XmlSchemaType;

public abstract class AbstractData {
	
	@XmlSchemaType(name = "dateTime")
	protected Date updated;
	
	@XmlSchemaType(name = "dateTime")
	protected  Date created;
	
	protected String uuid;


	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	@XmlSchemaType(name = "dateTime")
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
