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

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.MappedSuperclass;

import com.serpics.core.data.jpa.AbstractEntity;


/**
 * The persistent class for the ctentry_relation database table.
 * 
 */
@MappedSuperclass
public abstract class CtentryRelation extends AbstractEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private CtentryRelationPK id;

    @Column(name="fiedl2")
    private Double field2;

    private String field1;

    @Column(name="relation_type" , nullable=false)
    protected Integer relationType;

    private double sequence;



    public CtentryRelation() {
    }
   
    public CtentryRelationPK getId() {
        return this.id;
    }

    public void setId(final CtentryRelationPK id) {
        this.id = id;
    }

    public Double getField2() {
        return this.field2;
    }

    public void setField2(final Double field2) {
        this.field2 = field2;
    }

    public String getField1() {
        return this.field1;
    }

    public void setField1(final String field1) {
        this.field1 = field1;
    }

    public Integer getRelationType() {
        return this.relationType;
    }

    public void setRelationType(final Integer relationType) {
        this.relationType = relationType;
    }

    public double getSequence() {
        return this.sequence;
    }

    public void setSequence(final double sequence) {
        this.sequence = sequence;
    }

}