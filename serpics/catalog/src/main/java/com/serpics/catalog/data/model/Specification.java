package com.serpics.catalog.data.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;

import com.serpics.core.datatype.CatalogEntryType;

@Entity
@DiscriminatorValue("2")
@PrimaryKeyJoinColumn(name = "specification_id")
public class Specification extends Ctentry{

	public Specification() {
		super();
		ctentryType = CatalogEntryType.SPECIFICATION;
	}

	private static final long serialVersionUID = 1L;

	// bi-directional many-to-one association to Catalog
	@ManyToOne(optional = false )
	@JoinColumn(name = "catalog_id")
	private Catalog catalog;
	
	@OneToMany(mappedBy="specification" ,cascade=CascadeType.REMOVE , orphanRemoval=true)
	private Set<FeatureGroup> featureGroups = new HashSet<FeatureGroup>(0);
	

	public Set<FeatureGroup> getFeatureGroups() {
		return featureGroups;
	}

	public void setFeatureGroups(Set<FeatureGroup> featureGroups) {
		this.featureGroups = featureGroups;
	}
	
	public Catalog getCatalog() {
		return catalog;
	}

	public void setCatalog(Catalog catalog) {
		this.catalog = catalog;
	}
	
}
