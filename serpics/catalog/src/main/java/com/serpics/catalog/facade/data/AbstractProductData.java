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
package com.serpics.catalog.facade.data;

import java.util.Set;

import com.serpics.base.facade.data.MediaData;
import com.serpics.base.facade.data.TaxCategoryData;

public class AbstractProductData  extends CtentryData{
	protected boolean buyable;
	
	protected boolean dowloadable;
	protected String manufacturSku;
	protected String unitMeas;
	protected Double weight;
	protected String weightMeas;
	protected PriceData price;


	protected Set<MediaData> medias;

	//protected CatalogData catalog;
	
	protected String metaDescription;
	protected String metaKey;
	
	protected TaxCategoryData taxCategory;
	
	protected InventoryData inventoryData;

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
	
	public PriceData getPrice() {
		return price;
	}
	public void setPrice(PriceData price) {
		this.price = price;
	}
	
	public Set<MediaData> getMedias() {
		return medias;
	}
	public void setMedias(Set<MediaData> medias) {
		this.medias = medias;
	}
	public boolean isBuyable() {
		return buyable;
	}
	public void setBuyable(boolean buyable) {
		this.buyable = buyable;
	}
	
	public boolean isDowloadable() {
		return dowloadable;
	}
	public void setDowloadable(boolean dowloadable) {
		this.dowloadable = dowloadable;
	}
	public TaxCategoryData getTaxCategory() {
		return taxCategory;
	}
	public void setTaxCategory(TaxCategoryData taxCategory) {
		this.taxCategory = taxCategory;
	}
	public InventoryData getInventoryData() {
		return inventoryData;
	}
	public void setInventoryData(InventoryData inventoryData) {
		this.inventoryData = inventoryData;
	}
}
