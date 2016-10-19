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

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.serpics.base.data.model.Store;
import com.serpics.core.data.jpa.AbstractEntity;

@Entity
public class Catalog2StoreRelation extends AbstractEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2897908727484724284L;

	@EmbeddedId
	private Catalog2StoreRelPK id ;
	
	@ManyToOne
	@JoinColumn(name="store_id" , insertable=false , updatable=false)
	private Store store; 
	
	@ManyToOne
	@JoinColumn(name="catalog_id" ,insertable=false, updatable=false)
	private Catalog catalog;
	
	private boolean selected =false;

	public Catalog2StoreRelPK getId() {
		return id;
	}

	public void setId(Catalog2StoreRelPK id) {
		this.id = id;
	}

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	public Catalog getCatalog() {
		return catalog;
	}

	public void setCatalog(Catalog catalog) {
		this.catalog = catalog;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	
	
}
