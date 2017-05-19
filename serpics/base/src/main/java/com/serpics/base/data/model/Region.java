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
import javax.validation.constraints.Size;

import com.serpics.i18n.data.model.MultilingualString;

/**
 * The persistent class for the regions database table.
 * 
 */
@Entity
@Table(name = "regions")
public class Region extends com.serpics.core.data.jpa.AbstractEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "regions_id", unique = true, nullable = false)
    private Long Id;

    @Column(nullable = false, length = 20 , unique=true)
    @NotNull(message = "{region.isoCode.notnull}")
    @Size(max = 20,  message = "{region.isoCode.size}")
    private String isoCode;

    // bi-directional many-to-one association to Country
    @ManyToOne
    @JoinColumn(name = "countries_id", nullable = false)
    @NotNull(message = "{region.country.notnull}")
    private Country country;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "description_stringid")
    private MultilingualString description = new MultilingualString();
    
    // bi-directional many-to-one association to District
    @OneToMany(mappedBy = "region", fetch=FetchType.LAZY)
    private Set<District> districts;

    public Region() {
    }

    public Long getId() {
        return this.Id;
    }

    public void setId(final Long Id) {
        this.Id =Id;
    }

    public MultilingualString getDescription() {
        return this.description;
    }

    public void setDescription(final MultilingualString description) {
        this.description = description;
    }

   
    public Country getCountry() {
        return this.country;
    }

    public void setCountry(final Country country) {
        this.country = country;
    }

	public String getIsoCode() {
		return isoCode;
	}

	public void setIsoCode(String isoCode) {
		this.isoCode = isoCode;
	}

	public Set<District> getDistricts() {
		return districts;
	}

	public void setDistricts(Set<District> districts) {
		this.districts = districts;
	}



}