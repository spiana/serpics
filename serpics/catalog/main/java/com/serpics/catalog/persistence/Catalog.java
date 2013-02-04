package com.serpics.catalog.persistence;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * The persistent class for the catalog database table.
 * 
 */
@Entity
@Table(name = "catalog")
public class Catalog extends com.serpics.core.persistence.jpa.Entity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "catalog_id")
	private Long catalogId;

	private String name;

	private short published;

	// bi-directional many-to-one association to Pricelist
	@OneToMany(mappedBy = "catalog", fetch = FetchType.LAZY)
	private Set<Pricelist> pricelists;

	// bi-directional many-to-one association to Ctentry
	@OneToMany(mappedBy = "catalog", fetch = FetchType.LAZY)
	private Set<Ctentry> ctentries;

	public Catalog() {
	}

	public Long getCatalogId() {
		return this.catalogId;
	}

	public void setCatalogId(Long catalogId) {
		this.catalogId = catalogId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public short getPublished() {
		return this.published;
	}

	public void setPublished(short published) {
		this.published = published;
	}

	public Set<Pricelist> getPricelists() {
		return this.pricelists;
	}

	public void setPricelists(Set<Pricelist> pricelists) {
		this.pricelists = pricelists;
	}

	public Set<Ctentry> getCtentries() {
		return this.ctentries;
	}

	public void setCtentries(Set<Ctentry> ctentries) {
		this.ctentries = ctentries;
	}

}