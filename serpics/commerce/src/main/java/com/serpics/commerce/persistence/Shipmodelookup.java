package com.serpics.commerce.persistence;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;


/**
 * The persistent class for the shipmodelookup database table.
 * 
 */
@Entity
@Table(name="shipmodelookup" )
public class Shipmodelookup extends com.serpics.core.persistence.jpa.AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="shipmodelookup_id", unique=true, nullable=false)
	private Long shipmodelookupId;

	@Column(name="countries_id")
	private BigInteger countriesId;

	@Column(name="geocode_id")
	private BigInteger geocodeId;

	@Column(name="regions_id")
	private BigInteger regionsId;

	@Column(name="store_id", nullable=false)
	private BigInteger storeId;

	@Column(length=30)
	private String zipcode;

	//bi-directional many-to-one association to Shipmode
    @ManyToOne
	@JoinColumn(name="shipmode_id", nullable=false)
	private Shipmode shipmode;

    public Shipmodelookup() {
    }

	public Long getShipmodelookupId() {
		return this.shipmodelookupId;
	}

	public void setShipmodelookupId(Long shipmodelookupId) {
		this.shipmodelookupId = shipmodelookupId;
	}

	public BigInteger getCountriesId() {
		return this.countriesId;
	}

	public void setCountriesId(BigInteger countriesId) {
		this.countriesId = countriesId;
	}

	public BigInteger getGeocodeId() {
		return this.geocodeId;
	}

	public void setGeocodeId(BigInteger geocodeId) {
		this.geocodeId = geocodeId;
	}

	public BigInteger getRegionsId() {
		return this.regionsId;
	}

	public void setRegionsId(BigInteger regionsId) {
		this.regionsId = regionsId;
	}

	public BigInteger getStoreId() {
		return this.storeId;
	}

	public void setStoreId(BigInteger storeId) {
		this.storeId = storeId;
	}

	public String getZipcode() {
		return this.zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public Shipmode getShipmode() {
		return this.shipmode;
	}

	public void setShipmode(Shipmode shipmode) {
		this.shipmode = shipmode;
	}
	
}