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

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.serpics.base.data.model.MultiValueAttribute;


@Entity
public class FeatureValues extends AbstractCatalogEntry{

	private static final long serialVersionUID = 5102019790904200289L;

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="featurevalue_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "feature_id" , nullable=false)
    private Feature feature;

    @ManyToOne
    @JoinColumn(name = "product_id" , nullable=false)
    private Product product;

    @Embedded
    MultiValueAttribute value = new MultiValueAttribute();

    public Long getId() {
        return id;
    }
    public void setId(final Long id) {
        this.id = id;
    }
    public Feature getFeature() {
        return feature;
    }
    public void setFeature(final Feature feature) {
        this.feature = feature;
    }
    public Product getProduct() {
        return product;
    }
    public void setProduct(final Product product) {
        this.product = product;
    }
	public MultiValueAttribute getValue() {
		return value;
	}
	public void setValue(MultiValueAttribute value) {
		this.value = value;
	}
	
}
