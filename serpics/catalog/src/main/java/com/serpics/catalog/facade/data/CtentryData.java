package com.serpics.catalog.facade.data;

import com.serpics.core.facade.AbstractData;

public class CtentryData extends AbstractData{
	protected String code;
	protected String url;
	protected String description;
	protected int published;
	protected String meta_description;
	protected String meta_keyword;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
