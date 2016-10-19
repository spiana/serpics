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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.serpics.base.data.model.MultilingualString;
import com.serpics.base.data.model.Store;
import com.serpics.membership.data.model.Address;

/**
 * The persistent class for the warehouse database table.
 * 
 */
@Entity
@Table(name = "warehouse")
public class Warehouse extends com.serpics.core.data.jpa.AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "warehouse_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Pattern(regexp="[a-zA-Z0-9_\\-]+", message="only letters and number allowed !")
	private String name;
	
	@OneToOne(cascade = { CascadeType.ALL }, orphanRemoval = true)
	@JoinColumn(name = "description_string_id")
	private MultilingualString description;

	@Column(nullable=false)
	@NotNull
	private Double precedence;

	// bi-directional many-to-one association to Inventory
	@OneToMany(mappedBy = "warehouse" ,cascade= CascadeType.REMOVE , orphanRemoval=true,fetch=FetchType.LAZY)
	private Set<Inventory> inventories;
	
	private Double inventoryThreshold;

	@OneToOne(cascade=CascadeType.ALL , orphanRemoval=true , optional=true , fetch=FetchType.LAZY)
	@JoinColumn(name = "address_id")
	Address address;

	@Column(nullable=false)
	private Boolean forceInStock;
	
	@ManyToOne
	@JoinColumn(name ="store_id" , insertable=true , updatable=false)
	private Store store ;

	public Warehouse() {
		forceInStock = Boolean.FALSE;
		precedence = 0D;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public MultilingualString getDescription() {
		return description;
	}

	public void setDescription(MultilingualString description) {
		this.description = description;
	}

	public Double getPrecedence() {
		return precedence;
	}

	public void setPrecedence(Double precedence) {
		this.precedence = precedence;
	}

	public Set<Inventory> getInventories() {
		return inventories;
	}

	public void setInventories(Set<Inventory> inventories) {
		this.inventories = inventories;
	}

	public Double getInventoryThreshold() {
		return inventoryThreshold;
	}

	public void setInventoryThreshold(Double inventoryThreshold) {
		this.inventoryThreshold = inventoryThreshold;
	}

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	public Boolean getForceInStock() {
		return forceInStock;
	}

	public void setForceInStock(Boolean forceInStock) {
		this.forceInStock = forceInStock;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	
}