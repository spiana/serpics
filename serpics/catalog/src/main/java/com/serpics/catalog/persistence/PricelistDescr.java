package com.serpics.catalog.persistence;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the pricelist_descr database table.
 * 
 */
@Entity
@Table(name="pricelist_descr" )
public class PricelistDescr implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PricelistDescrPK id;

	private String descriprion;

	private Timestamp updated;

	//bi-directional many-to-one association to Pricelist
    @ManyToOne
	@JoinColumn(name="pricelist_id" , insertable=false, updatable=false)
	private Pricelist pricelist;

    public PricelistDescr() {
    }

	public PricelistDescrPK getId() {
		return this.id;
	}

	public void setId(PricelistDescrPK id) {
		this.id = id;
	}
	
	public String getDescriprion() {
		return this.descriprion;
	}

	public void setDescriprion(String descriprion) {
		this.descriprion = descriprion;
	}

	public Timestamp getUpdated() {
		return this.updated;
	}

	public void setUpdated(Timestamp updated) {
		this.updated = updated;
	}

	public Pricelist getPricelist() {
		return this.pricelist;
	}

	public void setPricelist(Pricelist pricelist) {
		this.pricelist = pricelist;
	}
	
}