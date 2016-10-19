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

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;




/**
 * The persistent class for the shipping database table.
 * 
 */
@Entity
@Table(name="taxes" , uniqueConstraints=@UniqueConstraint(columnNames={"store_id" , "name"}))

public class TaxCategory extends AbstractStoreEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="taxes_id", unique=true, nullable=false)
	private Long id;

	@Pattern(regexp="[A-Za-z0-9-_]+" , message="{taxCategory.name.pattern}")
	@NotNull(message="{taxCategory.name.notnull}")
	@Size(min=3 , max=50, message ="{taxCategory.name.size}")
	@Column(name="name" , length=100 )
	private String name;
	
	@OneToOne(cascade = { CascadeType.ALL }, orphanRemoval = true , fetch= FetchType.EAGER)
	@JoinColumn(name = "name_string_id")
	private MultilingualString description ;   
	
	private Double rate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}

	public MultilingualString getDescription() {
		return description;
	}

	public void setDescription(MultilingualString description) {
		this.description = description;
	}
}