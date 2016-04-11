package com.serpics.commerce.data.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.serpics.base.data.model.AbstractStoreEntity;
import com.serpics.base.data.model.Country;
import com.serpics.base.data.model.District;
import com.serpics.base.data.model.Geocode;
import com.serpics.base.data.model.Region;


/**
 * The persistent class for the shipmodelookup database table.
 * 
 */
@Entity
@Table(name="shipmodelookup" )
public class Shipmodelookup extends AbstractStoreEntity implements Serializable {
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
	
	@ManyToOne(optional=true)
	@JoinColumn(name="districts_id" , nullable=true)
	private District district;

	@Column(length=30)
	@Size(max=30, message = "{shipmodelookup.zipcode.size}")
	private String zipcode;

	//bi-directional many-to-one association to Shipmode
    @ManyToOne
	@JoinColumn(name="shipmode_id", nullable=false)
	private Shipmode shipmode;
    
    // bi-directional many-to-one association to Shipping
    @OneToMany(mappedBy = "shipmodelookup" , fetch=FetchType.LAZY)
    private Set<Shipping> shippings;

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

	public District getDistrict() {
		return district;
	}

	public void setDistrict(District district) {
		this.district = district;
	}

	public Set<Shipping> getShippings() {
		return shippings;
	}

	public void setShippings(Set<Shipping> shippings) {
		this.shippings = shippings;
	}
	
}