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
import javax.persistence.Entity;
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

import com.serpics.i18n.data.model.MultilingualString;

@Entity
@Table( uniqueConstraints= @UniqueConstraint(columnNames= {"catalog_id", "code"}))
public class FeatureGroup extends AbstractCatalogEntry {
   
	private static final long serialVersionUID = -7524207490009840876L;

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

	
	@Pattern(regexp="[a-zA-Z0-9_-]*", message= "{featureGroup.code.pattern}")
    private String code;

    private String name;

    private double sequence;

    @ManyToOne
    @JoinColumn(name="model_id" , nullable=false)
    FeatureModel model;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "description_string_id")
    private MultilingualString description;

    @OneToMany(mappedBy="featureGroup")
    Set<Feature> features = new HashSet<Feature>(0);

    public Long getId() {
        return id;
    }

    public void setFeatureGroupId(final Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(final String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public double getSequence() {
        return sequence;
    }

    public void setSequence(final double sequence) {
        this.sequence = sequence;
    }

    

    public Set<Feature> getFeatures() {
        return features;
    }

    public void setFeatures(final Set<Feature> features) {
        this.features = features;
    }

    public MultilingualString getDescription() {
        return description;
    }

    public void setDescription(final MultilingualString description) {
        this.description = description;
    }

	public FeatureModel getModel() {
		return model;
	}

	public void setModel(FeatureModel model) {
		this.model = model;
	}
}
