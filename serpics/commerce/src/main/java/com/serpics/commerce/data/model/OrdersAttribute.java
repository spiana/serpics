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
package com.serpics.commerce.data.model;

import java.io.Serializable;
import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.serpics.base.data.model.MultiValueAttribute;

/**
 * The persistent class for the orders_attribute database table.
 * 
 */
@Entity
@Table(name = "orders_attribute")
public class OrdersAttribute extends com.serpics.core.data.jpa.AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "attribute_id", unique = true, nullable = false)
	private Long id;

	@Column(name = "base_attributes_id", nullable = false)
	private BigInteger baseAttributesId;

	private double sequence;

	// bi-directional many-to-one association to Order
	@ManyToOne
	@JoinColumn(name = "orders_id", nullable = false)
	private AbstractOrder order;

	@Embedded
	private MultiValueAttribute value;

	public OrdersAttribute() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long attributeId) {
		this.id = attributeId;
	}

	public BigInteger getBaseAttributesId() {
		return this.baseAttributesId;
	}

	public void setBaseAttributesId(BigInteger baseAttributesId) {
		this.baseAttributesId = baseAttributesId;
	}

	public double getSequence() {
		return this.sequence;
	}

	public void setSequence(double sequence) {
		this.sequence = sequence;
	}

	public AbstractOrder getOrder() {
		return this.order;
	}

	public void setOrder(AbstractOrder order) {
		this.order = order;
	}

	public MultiValueAttribute getValue() {
		return value;
	}

	public void setValue(MultiValueAttribute value) {
		this.value = value;
	}

	
}