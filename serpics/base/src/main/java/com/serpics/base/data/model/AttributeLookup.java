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
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.serpics.core.data.jpa.AbstractEntity;



/**
 * The persistent class for the attribute_lookup database table.
 * 
 */
@Entity
@Table(name="attribute_lookup" )
public class AttributeLookup extends AbstractEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private AttributeLookupPK id;

    @Column(nullable=false)
    private double sequence;

    //bi-directional many-to-one association to BaseAttribute
    @ManyToOne
    @JoinColumn(name="base_attributes_id", nullable=false, insertable=false, updatable=false)
    private BaseAttribute baseAttribute;


    //    @JoinColumn(name="store_id" , nullable=false , insertable=false , updatable=false )
    //    StoreRealm store;

    public AttributeLookup() {
    }

    public AttributeLookupPK getId() {
        return this.id;
    }

    public void setId(final AttributeLookupPK id) {
        this.id = id;
    }

    public double getSequence() {
        return this.sequence;
    }

    public void setSequence(final double sequence) {
        this.sequence = sequence;
    }

    public BaseAttribute getBaseAttribute() {
        return this.baseAttribute;
    }

    public void setBaseAttribute(final BaseAttribute baseAttribute) {
        this.baseAttribute = baseAttribute;
    }



}