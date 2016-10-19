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

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.serpics.base.data.model.Store;
import com.serpics.catalog.data.model.AbstractProduct;
import com.serpics.core.data.jpa.AbstractEntity;


/**
 * The persistent class for the inventory database table.
 * 
 */
@Entity
@Table(name="inventory")
public class Inventory extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="inventory_id")
	private Long id;
	
	private Double available;
	private Double reserved;
	
	//bi-directional many-to-one association to Warehouse
	@NotNull
	@ManyToOne
	@JoinColumn(name="warehouse_id" , insertable=true , updatable=true , nullable=false )
	private Warehouse warehouse;

    @NotNull
    @ManyToOne
	@JoinColumn(name="product_id" , insertable=true , updatable=true , nullable=false)
    private AbstractProduct product ;
    
    @ManyToOne
	@JoinColumn(name ="store_id" , insertable=true , updatable=false , nullable=false)
    private Store store ;
    
    public Inventory() {
    	this.available = 0D;
    	this.reserved = 0D;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getAvailable() {
		return available;
	}

	public void setAvailable(Double available) {
		this.available = available;
	}

	public Double getReserved() {
		return reserved;
	}

	public void setReserved(Double reserved) {
		this.reserved = reserved;
	}

	public Warehouse getWarehouse() {
		return warehouse;
	}

	public void setWarehouse(Warehouse warehouse) {
		this.warehouse = warehouse;
	}

	public AbstractProduct getProduct() {
		return product;
	}

	public void setProduct(AbstractProduct product) {
		this.product = product;
	}

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}
	
	
	
}