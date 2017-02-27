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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.serpics.base.data.model.Media;

@Entity
@Table(name="ctentry_media" , uniqueConstraints= @UniqueConstraint(columnNames= {"catalog_id","content_type", "name"}))
public class CtentryMedia extends Media{

	@ManyToOne
	@JoinColumn(name="ctentry_id")
	private Ctentry ctentry;

	@ManyToOne(optional = false  )
    @JoinColumn(name = "catalog_id" )
    protected Catalog catalog;

	public Catalog getCatalog() {
		return catalog;
	}
	public void setCatalog(Catalog catalog) {
		this.catalog = catalog;
	}
	
	public Ctentry getCtentry() {
		return ctentry;
	}

	public void setCtentry(Ctentry ctentry) {
		this.ctentry = ctentry;
	}
	

}
