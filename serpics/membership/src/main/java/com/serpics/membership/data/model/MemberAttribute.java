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
package com.serpics.membership.data.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.serpics.base.data.model.BaseAttribute;
import com.serpics.base.data.model.MultiValueAttribute;
import com.serpics.core.data.jpa.AbstractEntity;


/**
 * The persistent class for the member_attribute database table.
 * 
 */
@XmlRootElement
@Entity
@Table(name = "member_attribute")
public class MemberAttribute extends AbstractEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "attribute_id", unique = true, nullable = false)
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "base_attributes_id", nullable=false)
    BaseAttribute baseAttribute;

    private double sequence;

    // bi-directional many-to-one association to Member
    @ManyToOne
    @XmlTransient
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    // bi-directional one-to-one association to MemberAttributeValueDatetime
    @Embedded
    private MultiValueAttribute value;

    public MemberAttribute() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(final Long attributeId) {
        this.id = attributeId;
    }


    public double getSequence() {
        return this.sequence;
    }

    public void setSequence(final double sequence) {
        this.sequence = sequence;
    }

    public Member getMember() {
        return this.member;
    }

    public void setMember(final Member member) {
        this.member = member;
    }

	public MultiValueAttribute getValue() {
		return value;
	}

	public void setValue(MultiValueAttribute value) {
		this.value = value;
	}


}