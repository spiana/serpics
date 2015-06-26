package com.serpics.catalog.data.model;

import java.io.Serializable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrePersist;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.serpics.core.datatype.CatalogEntryType;

/**
 * The persistent class for the catalog database table.
 * 
 */
@Entity
@Table(name = "catalog")
@DiscriminatorValue("0")
@PrimaryKeyJoinColumn(name = "catalog_id", referencedColumnName = "ctentry_id")
public class Catalog extends Ctentry implements com.serpics.core.data.model.Catalog,
		Serializable {
	private static final long serialVersionUID = 1L;

	private short published;

	
	public Catalog() {
		this.published=(short) 1;
		this.ctentryType = CatalogEntryType.CATALOG;
	}

	@Override
	public short getPublished() {
		return this.published;
	}

	public void setPublished(short published) {
		this.published = published;
	}
	
	
	@PrePersist
	public void prepersist(){
		if (this.url == null)
			this.url = "/" + getCode();
	}

	

}