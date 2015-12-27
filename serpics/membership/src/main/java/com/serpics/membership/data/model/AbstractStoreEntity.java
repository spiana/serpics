package com.serpics.membership.data.model;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import com.serpics.core.data.jpa.AbstractEntity;

@MappedSuperclass
public class AbstractStoreEntity extends AbstractEntity{
	
	@ManyToOne(optional=false)
	@JoinColumn(name="store_id", insertable=true, updatable=true)
	protected Store store;

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

}
