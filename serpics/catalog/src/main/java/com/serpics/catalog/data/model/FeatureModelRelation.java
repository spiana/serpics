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

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name="FearureModelRelation")
public class FeatureModelRelation extends CtentryRelation {
	
	private static final long serialVersionUID = 1L;
	
	
	//bi-directional many-to-one association to Ctentry
    @ManyToOne (fetch=FetchType.LAZY)
	@JoinColumn(name="ctentry_id_parent" , insertable=false, updatable=false)
	private FeatureModel parentSpecification;
	
	//bi-directional many-to-one association to Ctentry
    @ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ctentry_id_child" , insertable=false, updatable=false )
	private FeatureModel childSpecification;

	public FeatureModel getParentSpecification() {
		return parentSpecification;
	}

	public void setParentSpecification(FeatureModel parentSpecification) {
		this.parentSpecification = parentSpecification;
	}

	public FeatureModel getChildSpecification() {
		return childSpecification;
	}

	public void setChildSpecification(FeatureModel childSpecification) {
		this.childSpecification = childSpecification;
	}


}
