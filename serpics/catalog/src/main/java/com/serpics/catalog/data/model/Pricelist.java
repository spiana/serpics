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
package com.serpics.catalog.data.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.serpics.base.data.model.AbstractStoreEntity;
import com.serpics.i18n.data.model.MultilingualString;

/**
 * The persistent class for the pricelist database table.
 * 
 */
@Entity
@Table(name="pricelist" )
public class Pricelist extends AbstractStoreEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="pricelist_id")
    private Long id;

    @NotNull
    @Size(max = 100)
    @Column(nullable = false, length = 100)
    private String name;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "description_string_id")
    private MultilingualString description;

       //bi-directional many-to-one association to Price
    @OneToMany(mappedBy="pricelist")
    private Set<Price> prices;

    //bi-directional many-to-one association to Productffmt
    @OneToMany(mappedBy="pricelist")
    private Set<Productffmt> productffmts;

    boolean defaultList;

    public Pricelist() {
        super();
    }

    public Pricelist(final String name) {
        super();
        this.name = name;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(final Long pricelistId) {
        this.id = pricelistId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Set<Price> getPrices() {
        return this.prices;
    }

    public void setPrices(final Set<Price> prices) {
        this.prices = prices;
    }

    public Set<Productffmt> getProductffmts() {
        return this.productffmts;
    }

    public void setProductffmts(final Set<Productffmt> productffmts) {
        this.productffmts = productffmts;
    }

    public MultilingualString getDescription() {
        return description;
    }

    public void setDescription(final MultilingualString description) {
        this.description = description;
    }

    public boolean isDefaultList() {
        return defaultList;
    }

    public void setDefaultList(final boolean defaultList) {
        this.defaultList = defaultList;
    }

}