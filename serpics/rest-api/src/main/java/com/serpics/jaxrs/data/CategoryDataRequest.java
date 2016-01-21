package com.serpics.jaxrs.data;

public class CategoryDataRequest {
	
	private String code;
	private String Description;
	private Boolean published;
	
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public Boolean isPublished() {
		return published;
	}
	public void setPublished(Boolean published) {
		this.published = published;
	}

}
