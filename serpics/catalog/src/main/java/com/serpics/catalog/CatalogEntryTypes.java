package com.serpics.catalog;

public enum CatalogEntryTypes {
	CATALOG ,CATEGORY, SPECIFICATION , PRODUCT ;
	
	public String get(){
		return this.toString();
	}
}
