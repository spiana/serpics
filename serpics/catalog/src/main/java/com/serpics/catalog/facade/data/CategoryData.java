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

