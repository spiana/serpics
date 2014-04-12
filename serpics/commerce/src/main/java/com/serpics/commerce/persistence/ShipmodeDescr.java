package com.serpics.commerce.persistence;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * The persistent class for the shipmode_descr database table.
 * 
 */
@Entity
@Table(name = "shipmode_descr")
public class ShipmodeDescr extends com.serpics.core.persistence.jpa.AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ShipmodeDescrPK id;

	@Column(length = 1000)
	private String description;

	// bi-directional many-to-one association to Shipmode
	@ManyToOne
	@JoinColumn(name = "shipmode_id", nullable = false, insertable = false, updatable = false)
	private Shipmode shipmode;

	public ShipmodeDescr() {
	}

	public ShipmodeDescrPK getId() {
		return this.id;
	}

	public void setId(ShipmodeDescrPK id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Shipmode getShipmode() {
		return this.shipmode;
	}

	public void setShipmode(Shipmode shipmode) {
		this.shipmode = shipmode;
	}

}