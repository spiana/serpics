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
public class PricelistDescr extends com.serpics.core.persistence.jpa.AbstractEntity {

	@EmbeddedId
	private PricelistDescrPK id;

	private String description;


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
	
	public Pricelist getPricelist() {
		return this.pricelist;
	}

	public void setPricelist(Pricelist pricelist) {
		this.pricelist = pricelist;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}