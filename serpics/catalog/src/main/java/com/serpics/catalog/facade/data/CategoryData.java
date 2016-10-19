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

import com.fasterxml.jackson.annotation.JsonPropertyOrder;



@JsonPropertyOrder({"id","catalogId","code","name","url","description","childCategoryNumber","childProductNumber","published","metaDescription","metaKey",})
public class CategoryData  extends CtentryData{

	protected String catalogId;
	protected int childCategoryNumber;
	protected int childProductNumber;
	protected Set<CategoryData> parentCategories;
	protected boolean published;

	public String getCatalogId() {
		return catalogId;
	}
	public void setCatalogId(String catalogId) {
		this.catalogId = catalogId;
	}
	public int getChildCategoryNumber() {
		return childCategoryNumber;
	}
	public void setChildCategoryNumber(int childCategoryNumber) {
		this.childCategoryNumber = childCategoryNumber;
	}
	public int getChildProductNumber() {
		return childProductNumber;
	}
	public void setChildProductNumber(int childProductNumber) {
		this.childProductNumber = childProductNumber;
	}
	public boolean isPublished() {
		return published;
	}
	public void setPublished(boolean published) {
		this.published = published;
	}
	public Set<CategoryData> getParentCategories() {
		return parentCategories;
	}
	public void setParentCategories(Set<CategoryData> parentCategories) {
		this.parentCategories = parentCategories;
	}
	
}

