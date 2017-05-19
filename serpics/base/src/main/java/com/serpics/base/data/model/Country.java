/*******************************************************************************
 * Copyright 2014-2016  StepFour Srl
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *  
 *      http://www.apache.org/licenses/LICENSE-2.0
 *  
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *  
 *******************************************************************************/
package com.serpics.base.data.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.serpics.i18n.data.model.MultilingualString;

/**
 * The persistent class for the countries database table.
 *
 */
@Entity
@Table(name = "countries")
public class Country extends com.serpics.core.data.jpa.AbstractEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "countries_id", unique = true, nullable = false)
    private Long countriesId;

    @Column(name = "iso2_code", nullable = false, length = 2)
 //   @Size(min=2,max=2)
    @Pattern(regexp="[A-Z]{2}")
    @NotNull(message = "{country.iso2Code.notnull}")
    private String iso2Code;

    @Column(name = "iso3_code", nullable = false, length = 3)
 //   @Size(min=3,max=3)
    @Pattern(regexp="[A-Z]{3}", message = "{country.iso3Code.pattern}" )
    @NotNull(message = "{country.iso3Code.notnull}")
    private String iso3Code;

    @Column(name = "iso_num_code", nullable = true)
    private Integer isoNumber;


    // bi-directional many-to-one association to Geocode
    @ManyToOne
    @JoinColumn(name = "geocode_id", nullable = true)
    private Geocode geocode;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "description_stringid")
    private MultilingualString description = new MultilingualString();

    // bi-directional many-to-one association to Region
    @OneToMany(mappedBy = "country", fetch=FetchType.LAZY)
    private Set<Region> regions;

    // bi-directional many-to-one association to District
    @OneToMany(mappedBy = "country", fetch=FetchType.LAZY)
    private Set<District> districts;

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


	public Integer getIsoNumber() {
		return isoNumber;
	}


	public void setIsoNumber(Integer isoNumber) {
		this.isoNumber = isoNumber;
	}


	public Set<District> getDistricts() {
		return districts;
	}


	public void setDistricts(Set<District> districts) {
		this.districts = districts;
	}

}
