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
package com.serpics.warehouse.data.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.serpics.base.data.model.AbstractStoreEntity;

@Entity
@Table(name="warehouse_config")
public class WarehouseStoreConfig extends AbstractStoreEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 943288266986001330L;

	@Id @GeneratedValue(strategy =GenerationType.AUTO)
	private Long id;
	
	private Double storeThreshold;
	
	private boolean alwaysInstock = false;

	public WarehouseStoreConfig() {
		super();
		storeThreshold = 0.0D;
		alwaysInstock = true;
	}

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getStoreThreshold() {
		return storeThreshold;
	}

	public void setStoreThreshold(Double storeThreshold) {
		this.storeThreshold = storeThreshold;
	}


	public boolean isAlwaysInstock() {
		return alwaysInstock;
	}


	public void setAlwaysInstock(boolean alwaysInstock) {
		this.alwaysInstock = alwaysInstock;
	}
	

}
