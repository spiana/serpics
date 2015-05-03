package com.serpics.warehouse.data.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the inventory database table.
 * 
 */
@Entity
@Table(name="inventory")
public class Inventory extends com.serpics.core.data.jpa.AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private InventoryPK id;

	private double available;

	//bi-directional many-to-one association to Warehouse
    @ManyToOne
	@JoinColumn(name="warehouse_id" , insertable=false , updatable=false)
	private Warehouse warehouse;

    public Inventory() {
    }

	public InventoryPK getId() {
		return this.id;
	}

	public void setId(InventoryPK id) {
		this.id = id;
	}
	
	public double getAvailable() {
		return this.available;
	}

	public void setAvailable(double available) {
		this.available = available;
	}

	public Warehouse getWarehouse() {
		return this.warehouse;
	}

	public void setWarehouse(Warehouse warehouse) {
		this.warehouse = warehouse;
	}
	
}