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
package com.serpics.i18n.data.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * The persistent class for the currency database table.
 * 
 */
@Entity
@Table(name = "currency")
public class Currency extends com.serpics.core.data.jpa.AbstractEntity implements com.serpics.core.data.model.Currency,
		Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "currency_id", unique = true, nullable = false)
	private Long currencyId;

	@Column(length = 1000)
	@Size(max = 1000,  message = "{currency.description.size}")
	private String description;

	@Column(name = "iso_code", nullable = false, length = 3)
	@NotNull(message = "{currency.isoCode.notnull}")
    @Pattern(regexp="[A-Z]{3}", message = "{currency.isoCode.pattern}")
	private String isoCode;

	public Currency() {
	}

	public Long getCurrencyId() {
		return this.currencyId;
	}

	public void setCurrencyId(Long currencyId) {
		this.currencyId = currencyId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getIsoCode() {
		return this.isoCode;
	}

	public void setIsoCode(String isoCode) {
		this.isoCode = isoCode;
	}

}