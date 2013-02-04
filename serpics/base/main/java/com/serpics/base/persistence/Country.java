package com.serpics.base.persistence;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the countries database table.
 * 
 */
@Entity
@Table(name="countries" )
public class Country implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="countries_id", unique=true, nullable=false)
	private Long countriesId;

	@Column(nullable=false, length=100)
	private String description;

	@Column(name="iso2_code", nullable=false, length=2)
	private String iso2Code;

	@Column(name="iso3_code", nullable=false, length=3)
	private String iso3Code;

	//bi-directional many-to-one association to Geocode
    @ManyToOne
	@JoinColumn(name="geocode_id", nullable=false)
	private Geocode geocode;

	//bi-directional many-to-one association to CountryDescr
	@OneToMany(mappedBy="country")
	private Set<CountryDescr> countryDescrs;

	//bi-directional many-to-one association to Region
	@OneToMany(mappedBy="country")
	private Set<Region> regions;

    public Country() {
    }

	public Long getCountriesId() {
		return this.countriesId;
	}

	public void setCountriesId(Long countriesId) {
		this.countriesId = countriesId;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getIso2Code() {
		return this.iso2Code;
	}

	public void setIso2Code(String iso2Code) {
		this.iso2Code = iso2Code;
	}

	public String getIso3Code() {
		return this.iso3Code;
	}

	public void setIso3Code(String iso3Code) {
		this.iso3Code = iso3Code;
	}

	public Geocode getGeocode() {
		return this.geocode;
	}

	public void setGeocode(Geocode geocode) {
		this.geocode = geocode;
	}
	
	public Set<CountryDescr> getCountryDescrs() {
		return this.countryDescrs;
	}

	public void setCountryDescrs(Set<CountryDescr> countryDescrs) {
		this.countryDescrs = countryDescrs;
	}
	
	public Set<Region> getRegions() {
		return this.regions;
	}

	public void setRegions(Set<Region> regions) {
		this.regions = regions;
	}
	
}