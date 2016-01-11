package com.serpics.warehouse.data.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.serpics.membership.data.model.AbstractStoreEntity;

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
