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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.serpics.i18n.data.model.Currency;


/**
 * The persistent class for the shipping database table.
 * 
 */
@Entity
@Table(name="shipping" )
public class Shipping extends com.serpics.core.data.jpa.AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="shipping_id", unique=true, nullable=false)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="currency_id", nullable=false)
	@NotNull(message ="{shipping.currency.notnull")
	private Currency currency;

	@Column(precision=10, scale=4)
	private Double rangestart;

	@Column(precision=10, scale=4)
	private Double value;

	//bi-directional many-to-one association to Shipmodelookup
    @ManyToOne
	@JoinColumn(name="shipmodelookup_id", nullable=false)
	private Shipmodelookup shipmodelookup;

    public Shipping() {
    }

	public Long getId() {
		return this.id;
	}

	public void setId(Long shippingId) {
		this.id = shippingId;
	}

	public Currency getCurrency() {
		return this.currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	public Double getRangestart() {
		return this.rangestart;
	}

	public void setRangestart(Double rangestart) {
		this.rangestart = rangestart;
	}

	public Double getValue() {
		return this.value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public Shipmodelookup getShipmodelookup() {
		return this.shipmodelookup;
	}

	public void setShipmodelookup(Shipmodelookup shipmodelookup) {
		this.shipmodelookup = shipmodelookup;
	}
	
}