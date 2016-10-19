/*******************************************************************************
 * Copyright 2014-2016  StepFour Srl
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *  
 *      http://www.apache.org/licenses/LICENSE-2.0
 *  
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *  
 *******************************************************************************/
package com.serpics.catalog.data.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PrimaryKeyJoinColumn;

import com.serpics.catalog.data.CatalogEntryType;

@Entity
@DiscriminatorValue("2")
@PrimaryKeyJoinColumn(name = "specification_id")
public class FeatureModel extends Ctentry{

	public FeatureModel() {
		super();
		ctentryType = CatalogEntryType.FEATUREMODEL;
	}

	private static final long serialVersionUID = 1L;

	// bi-directional many-to-one association to Catalog
	@ManyToOne(optional = false )
	@JoinColumn(name = "catalog_id")
	private Catalog catalog;
	
	@OneToMany(mappedBy="model" ,cascade=CascadeType.REMOVE , orphanRemoval=true)
	private Set<FeatureGroup> featureGroups = new HashSet<FeatureGroup>(0);
	
	@OneToMany(mappedBy="featureModel" ,cascade=CascadeType.REMOVE , orphanRemoval=true)
	private Set<Feature> features = new HashSet<Feature>(0);

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
	
	public Set<Feature> getFeatures() {
		return features;
	}

	public void setFeatures(Set<Feature> features) {
		this.features = features;
	}
	
		@PrePersist
	    @Override
	    public void beforePersist() {
	        if (this.url == null)
	            this.url = "/" + getCatalog().getCode() + "/" + getCode();
	        super.beforePersist();
	    }
}
