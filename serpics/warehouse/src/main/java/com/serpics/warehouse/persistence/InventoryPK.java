package com.serpics.warehouse.persistence;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the inventory database table.
 * 
 */
@Embeddable
public class InventoryPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="warehouse_id")
	private String warehouseId;

	@Column(name="ctentry_id")
	private String ctentryId;

    public InventoryPK() {
    }
	public String getWarehouseId() {
		return this.warehouseId;
	}
	public void setWarehouseId(String warehouseId) {
		this.warehouseId = warehouseId;
	}
	public String getCtentryId() {
		return this.ctentryId;
	}
	public void setCtentryId(String ctentryId) {
		this.ctentryId = ctentryId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof InventoryPK)) {
			return false;
		}
		InventoryPK castOther = (InventoryPK)other;
		return 
			this.warehouseId.equals(castOther.warehouseId)
			&& this.ctentryId.equals(castOther.ctentryId);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.warehouseId.hashCode();
		hash = hash * prime + this.ctentryId.hashCode();
		
		return hash;
    }
}