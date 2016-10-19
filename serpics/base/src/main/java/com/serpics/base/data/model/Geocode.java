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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * The persistent class for the geocode database table.
 * 
 */
@Entity
@Table(name = "geocode")
public class Geocode extends com.serpics.core.data.jpa.AbstractEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "geocode_id", unique = true, nullable = false)
    private Long geocodeId;

    @Column(nullable = false, length = 100 , name="name" )
    @NotNull(message = "{geocode.code.notnull}")
    @Pattern(regexp="[a-zA-Z0-9]+",message="{geocode.code.pattern}")
    @Size(max = 100, message = "{geocode.code.size}")
    private String code;

    // bi-directional many-to-one association to Country
    @OneToMany(mappedBy = "geocode" , fetch=FetchType.LAZY)
    private Set<Country> countries;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "description_stringid")
    private MultilingualString description;

    public Geocode() {
    }

    public Long getGeocodeId() {
        return this.geocodeId;
    }

    public void setGeocodeId(final Long geocodeId) {
        this.geocodeId = geocodeId;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(final String code) {
        this.code = code;
    }

    public Set<Country> getCountries() {
        return this.countries;
    }

    public void setCountries(final Set<Country> countries) {
        this.countries = countries;
    }

    public MultilingualString getDescription() {
        return description;
    }

    public void setDescription(final MultilingualString description) {
        this.description = description;
    }


}