package com.serpics.base.persistence;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the paymethlookup database table.
 * 
 */
@Entity
@Table(name="paymethlookup" )
public class Paymethlookup implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PaymethlookupPK id;

	@Column(nullable=false)
	private short active;

	private Timestamp updated;

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

	public Timestamp getUpdated() {
		return this.updated;
	}

	public void setUpdated(Timestamp updated) {
		this.updated = updated;
	}

	public Paymethod getPaymethod() {
		return this.paymethod;
	}

	public void setPaymethod(Paymethod paymethod) {
		this.paymethod = paymethod;
	}
	
}