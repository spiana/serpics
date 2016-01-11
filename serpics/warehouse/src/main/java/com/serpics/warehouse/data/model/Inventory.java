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
import com.serpics.catalog.data.model.Product;
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
    private Product product ;
    
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

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}
	
	
	
}