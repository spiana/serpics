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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.serpics.core.data.jpa.AbstractEntity;
import com.serpics.core.security.StoreRealm;
import com.serpics.i18n.data.model.Currency;

/**
 * The persistent class for the stores database table.
 * 
 */
@Entity
@Table(name = "stores")
public class Store extends AbstractEntity implements Serializable, StoreRealm {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "store_id", unique = true, nullable = false)
    protected Long id;
    
    @Column(nullable = false, length = 250, unique = false)
    @NotNull(message="{store.name.notnull}")
    @Size(max=250, message="{store.name.size}")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "currency_id")
    @NotNull(message="{store.currency.notnull}")
    private Currency currency;
    
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "taxcategory_id")
    private TaxCategory taxcategory;
    

    public Store() {
     
    }

    public void setName(final String name) {
        this.name = name;
    }


    @Override
    public String getName() {
        return name;
    }
   
    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(final Currency currency) {
        this.currency = currency;
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final Store other = (Store) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TaxCategory getTaxcategory() {
		return taxcategory;
	}

	public void setTaxcategory(TaxCategory taxcategory) {
		this.taxcategory = taxcategory;
	}

}