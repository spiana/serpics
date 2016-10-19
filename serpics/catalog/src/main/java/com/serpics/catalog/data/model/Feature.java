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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Pattern;

import com.serpics.base.AttributeType;
import com.serpics.base.data.model.MultilingualString;
@Entity
@Table( uniqueConstraints= @UniqueConstraint(columnNames= {"catalog_id", "name"}))
public class Feature  extends AbstractCatalogEntry{

	private static final long serialVersionUID = -1111581062897750056L;

	@Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="feature_id")
    private Long id;

	@Pattern(regexp="[a-zA-Z0-9_-]*", message= "{feature.name.pattern}")
    private String name;

    private AttributeType type;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "description_stringid")
    private MultilingualString description;

    @Column(name="sequence")
    private double sequence;

    @ManyToOne
    @JoinColumn(name="featureModel_id" , nullable=false)
    private FeatureModel featureModel;
    
    @ManyToOne
    @JoinColumn(name="featureGroup_id" ,nullable=false)
    private FeatureGroup featureGroup;

    @OneToMany(mappedBy="feature" ,fetch= FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<FeatureValues> featureValues = new HashSet<FeatureValues>(0);

    public Long getId() {
        return id;
    }

    public void setId(final Long featureId) {
        this.id = featureId;
    }


    public double isSequence() {
        return sequence;
    }

    public void setSequence(final double sequence) {
        this.sequence = sequence;
    }

    public Set<FeatureValues> getFeatureValues() {
        return featureValues;
    }

    public void setFeatureValues(final Set<FeatureValues> featureValues) {
        this.featureValues = featureValues;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public AttributeType getType() {
        return type;
    }

    public void setType(final AttributeType type) {
        this.type = type;
    }


    public FeatureGroup getFeatureGroup() {
        return featureGroup;
    }

    public void setFeatureGroup(final FeatureGroup featureGroup) {
        this.featureGroup = featureGroup;
    }

    public MultilingualString getDescription() {
        return description;
    }

    public void setDescription(final MultilingualString description) {
        this.description = description;
    }

	public FeatureModel getFeatureModel() {
		return featureModel;
	}

	public void setFeatureModel(FeatureModel featureModel) {
		this.featureModel = featureModel;
	}

	

}
