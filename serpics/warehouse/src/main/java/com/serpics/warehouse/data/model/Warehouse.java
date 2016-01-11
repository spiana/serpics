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

import com.serpics.base.data.model.MultilingualString;
import com.serpics.base.data.model.Store;

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

	private String name;
	
	@OneToOne(cascade = { CascadeType.ALL }, orphanRemoval = true)
	@JoinColumn(name = "description_string_id")
	private MultilingualString description;

	@Column(nullable=false)
	private Double precedence;

	// bi-directional many-to-one association to Inventory
	@OneToMany(mappedBy = "warehouse" ,cascade= CascadeType.REMOVE , orphanRemoval=true,fetch=FetchType.LAZY)
	private Set<Inventory> inventories;
	
	private Double inventoryThreshold;
	
	@ManyToOne
	@JoinColumn(name ="store_id" , insertable=true , updatable=false)
	private Store store ;

	public Warehouse() {
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

	
}