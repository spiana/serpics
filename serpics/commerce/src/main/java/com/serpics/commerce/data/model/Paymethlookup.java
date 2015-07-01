package com.serpics.commerce.data.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;



/**
 * The persistent class for the paymethlookup database table.
 * 
 */
@Entity
@Table(name="paymethlookup" )
public class Paymethlookup extends com.serpics.core.data.jpa.AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PaymethlookupPK id;

	@Column(nullable=false)
	private short active;




	//bi-directional many-to-one association to Paymethod
    @ManyToOne
	@JoinColumn(name="paymethod_id", nullable=false, insertable=false, updatable=false)
	private Paymethod paymethod;

    public Paymethlookup() {
    }

	public PaymethlookupPK getId() {
		return this.id;
	}

	public void setId(PaymethlookupPK id) {
		this.id = id;
	}
	
	public short getActive() {
		return this.active;
	}

	public void setActive(short active) {
		this.active = active;
	}

	public Paymethod getPaymethod() {
		return this.paymethod;
	}

	public void setPaymethod(Paymethod paymethod) {
		this.paymethod = paymethod;
	}
	
}