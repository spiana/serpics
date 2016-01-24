package com.serpics.jaxrs.data;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"name","logo","published"})
public class BrandDataRequest  {
	 private String name;
	 private String logo;
	 private Boolean published;
	 
	 
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public Boolean isPublished() {
		return published;
	}
	public void setPublished(Boolean published) {
		this.published = published;
	}

}
