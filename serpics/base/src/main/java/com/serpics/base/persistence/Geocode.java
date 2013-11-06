package com.serpics.base.persistence;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * The persistent class for the geocode database table.
 * 
 */
@Entity
@Table(name = "geocode")
public class Geocode extends com.serpics.core.persistence.jpa.Entity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "geocode_id", unique = true, nullable = false)
	private Long geocodeId;

	@Column(nullable = false, length = 100)
	private String name;

	// bi-directional many-to-one association to Country
	@OneToMany(mappedBy = "geocode")
	private Set<Country> countries;

	// bi-directional many-to-one association to GeocodeDescr
	@OneToMany(mappedBy = "geocode")
	private Set<GeocodeDescr> geocodeDescrs;

	public Geocode() {
	}

	public Long getGeocodeId() {
		return this.geocodeId;
	}

	public void setGeocodeId(Long geocodeId) {
		this.geocodeId = geocodeId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Country> getCountries() {
		return this.countries;
	}

	public void setCountries(Set<Country> countries) {
		this.countries = countries;
	}

	public Set<GeocodeDescr> getGeocodeDescrs() {
		return this.geocodeDescrs;
	}

	public void setGeocodeDescrs(Set<GeocodeDescr> geocodeDescrs) {
		this.geocodeDescrs = geocodeDescrs;
	}

}