package com.serpics.commerce.data.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.serpics.membership.data.model.Store;



/**
 * The persistent class for the paymethlookup database table.
 * 
 */
@Entity
@Table(name="paymethodlookup" )
public class Paymethodlookup extends com.serpics.core.data.jpa.AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PaymethodlookupPK id;

	@Column(nullable=false)
	private boolean active;


	//bi-directional many-to-one association to Store
    @ManyToOne
	@JoinColumn(name="store_id", insertable=false, updatable=false)
	private Store store;

	//bi-directional many-to-one association to Paymethod
    @ManyToOne
	@JoinColumn(name="paymethod_id", insertable=false, updatable=false)
	private Paymethod paymethod;

    public Paymethodlookup() {
    }

	public PaymethodlookupPK getId() {
		return this.id;
	}

	public void setId(PaymethodlookupPK id) {
		this.id = id;
	}
	
	public boolean getActive() {
		return this.active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Paymethod getPaymethod() {
		return this.paymethod;
	}

	public void setPaymethod(Paymethod paymethod) {
		this.paymethod = paymethod;
	}

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}
	
}