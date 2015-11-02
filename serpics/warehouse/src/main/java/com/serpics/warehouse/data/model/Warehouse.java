package com.serpics.warehouse.data.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

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
	private String warehouseId;

	private String description;

	private String name;

	private double precedence;

	// bi-directional many-to-one association to Inventory
	@OneToMany(mappedBy = "warehouse",cascade = CascadeType.REMOVE)
	private Set<Inventory> inventories;

	public Warehouse() {
	}

	public String getWarehouseId() {
		return this.warehouseId;
	}

	public void setWarehouseId(String warehouseId) {
		this.warehouseId = warehouseId;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrecedence() {
		return this.precedence;
	}

	public void setPrecedence(double precedence) {
		this.precedence = precedence;
	}

	public Set<Inventory> getInventories() {
		return this.inventories;
	}

	public void setInventories(Set<Inventory> inventories) {
		this.inventories = inventories;
	}

}