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
import javax.validation.constraints.Pattern;

import com.serpics.core.data.jpa.AbstractEntity;
import com.serpics.i18n.data.model.MultilingualString;

/**
 * The persistent class for the catalog database table.
 * 
 */
@Entity
@Table(name = "catalog")
public class Catalog extends AbstractEntity implements
		com.serpics.core.data.model.Catalog, Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "catalog_id")
	private Long id;

	@Pattern(regexp="[a-zA-Z0-9_-]*", message= "{catalog.code.pattern}")
	private String code;

	@OneToOne(cascade = { CascadeType.ALL }, orphanRemoval = true, fetch = FetchType.LAZY)
	@JoinColumn(name = "name_string_id")
	private MultilingualString name;

	private boolean published;

	public Catalog() {
		published = true;
	}

		public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public MultilingualString getName() {
		return name;
	}

	public void setName(MultilingualString name) {
		this.name = name;
	}


	@Override
	public boolean isPublished() {
		return published;
	}

	public void setPublished(boolean published) {
		this.published = published;
	}

}