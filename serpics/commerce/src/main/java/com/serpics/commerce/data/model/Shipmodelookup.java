package com.serpics.commerce.data.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.serpics.base.data.model.Country;
import com.serpics.base.data.model.Geocode;
import com.serpics.base.data.model.Region;
import com.serpics.base.data.model.Store;


/**
 * The persistent class for the shipmodelookup database table.
 * 
 */
@Entity
@Table(name="shipmodelookup" )
public class Shipmodelookup extends com.serpics.core.data.jpa.AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="shipmodelookup_id", unique=true, nullable=false)
	private Long id;

	
	@ManyToOne(optional=true)
	@JoinColumn(name="country_id", nullable=true)
	private Country country;

	@ManyToOne(optional=true)
	@JoinColumn(name="geocode_id", nullable=true)
	private Geocode geocode;

	@ManyToOne(optional=true)
	@JoinColumn(name="regions_id" , nullable=true)
	private Region region;

	@ManyToOne
	@JoinColumn(name="store_id", nullable=false)
	private Store store;

	@Column(length=30)
	private String zipcode;

	//bi-directional many-to-one association to Shipmode
    @ManyToOne
	@JoinColumn(name="shipmode_id", nullable=false)
	private Shipmode shipmode;

    public Shipmodelookup() {
    }

	public Long getId() {
		return this.id;
	}

	public void setShipmodelookupId(Long id) {
		this.id = id;
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

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public Geocode getGeocode() {
		return geocode;
	}

	public void setGeocode(Geocode geocode) {
		this.geocode = geocode;
	}

	public Region getRegion() {
		return region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}
	
}