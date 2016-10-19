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
package com.serpics.jaxrs.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductDataRequest {


	// private Set<CategoryDataRequest> categories;
	private String code;
	private String name;
	private String description;
	private String url;
	private Boolean buyable;
	private Boolean downloadable;
	private String manufacturSku;
	private Boolean published;
	private String unitMeas;
	private Double weight;
	private String weightMeas;
	private String metaDescription;
	private String metaKey;

	public Boolean getPublished() {
		return published;
	}

	public void setPublished(Boolean published) {
		this.published = published;
	}

	public String getManufacturSku() {
		return manufacturSku;
	}

	public void setManufacturSku(String manufacturSku) {
		this.manufacturSku = manufacturSku;
	}

	public String getUnitMeas() {
		return unitMeas;
	}

	public void setUnitMeas(String unitMeas) {
		this.unitMeas = unitMeas;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public String getWeightMeas() {
		return weightMeas;
	}

	public void setWeightMeas(String weightMeas) {
		this.weightMeas = weightMeas;
	}

	// public Set<CategoryDataRequest> getCategories() {
	// return categories;
	// }
	//
	// public void setCategories(Set<CategoryDataRequest> categories) {
	// this.categories = categories;
	// }

	public String getMetaDescription() {
		return metaDescription;
	}

	public void setMetaDescription(String metaDescription) {
		this.metaDescription = metaDescription;
	}

	public String getMetaKey() {
		return metaKey;
	}

	public void setMetaKey(String metaKey) {
		this.metaKey = metaKey;
	}

	public Boolean getBuyable() {
		return buyable;
	}

	public Boolean getDownloadable() {
		return downloadable;
	}

	public Boolean isBuyable() {
		return buyable;
	}

	public void setBuyable(Boolean buyable) {
		this.buyable = buyable;
	}

	public Boolean isDownloadable() {
		return downloadable;
	}

	public void setDownloadable(Boolean downloadable) {
		this.downloadable = downloadable;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}