package com.serpics.commerce.data.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.serpics.base.data.model.Store;

/**
 * The persistent class for the taxlookup database table.
 * 
 */
@Entity
@Table(name = "taxeslookup")
public class Taxlookup extends com.serpics.core.data.jpa.AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private TaxlookupPK id;

	@Column(nullable = false)
	private boolean active;

	// bi-directional many-to-one association to Store
	@ManyToOne
	@NotNull
	@JoinColumn(name = "store_id", insertable = false, updatable = false)
	private Store store;

	// bi-directional many-to-one association to Tax
	@ManyToOne
	@NotNull
	@JoinColumn(name = "taxes_id", insertable = false, updatable = false)
	private Tax tax;

	public Taxlookup() {
	}

	public TaxlookupPK getId() {
		return this.id;
	}

	public void setId(TaxlookupPK id) {
		this.id = id;
	}

	public boolean getActive() {
		return this.active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	public Tax getTax() {
		return tax;
	}

	public void setTax(Tax tax) {
		this.tax = tax;
	}

}