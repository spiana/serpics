package com.serpics.base.persistence;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 * The persistent class for the countries database table.
 * 
 */
@Entity
@Table(name = "countries")
public class Country extends com.serpics.core.persistence.jpa.AbstractEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "countries_id", unique = true, nullable = false)
    private Long countriesId;

    @Column(name = "iso2_code", nullable = false, length = 2)
    private String iso2Code;

    @Column(name = "iso3_code", nullable = false, length = 3)
    @Size(min=1,max=3)
    private String iso3Code;

    // bi-directional many-to-one association to Geocode
    @ManyToOne
    @JoinColumn(name = "geocode_id", nullable = false)
    private Geocode geocode;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "description_stringid")
    private MultilingualString description = new MultilingualString();

    // bi-directional many-to-one association to Region
    @OneToMany(mappedBy = "country")
    private Set<Region> regions;

    public Country() {
    }

    public Long getCountriesId() {
        return this.countriesId;
    }

    public void setCountriesId(final Long countriesId) {
        this.countriesId = countriesId;
    }

    public MultilingualString getDescription() {
        return this.description;
    }

    public void setDescription(final MultilingualString description) {
        this.description = description;
    }

    public String getIso2Code() {
        return this.iso2Code;
    }

    public void setIso2Code(final String iso2Code) {
        this.iso2Code = iso2Code;
    }

    public String getIso3Code() {
        return this.iso3Code;
    }

    public void setIso3Code(final String iso3Code) {
        this.iso3Code = iso3Code;
    }

    public Geocode getGeocode() {
        return this.geocode;
    }

    public void setGeocode(final Geocode geocode) {
        this.geocode = geocode;
    }

    public Set<Region> getRegions() {
        return this.regions;
    }

    public void setRegions(final Set<Region> regions) {
        this.regions = regions;
    }

}