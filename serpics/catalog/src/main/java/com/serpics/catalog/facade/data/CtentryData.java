package com.serpics.catalog.facade.data;

import com.serpics.base.facade.data.MediaData;
import com.serpics.core.facade.AbstractData;

public class CtentryData extends AbstractData{
	protected String code;
	protected String url;
	protected String name;
	protected String description;
	protected int published;
	protected String metaDescription;
	protected String metaKeyword;
	protected MediaData primaryImage;
	
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPublished() {
		return published;
	}
	public void setPublished(int published) {
		this.published = published;
	}
	public String getMetaDescription() {
		return metaDescription;
	}
	public void setMetaDescription(String metaDescription) {
		this.metaDescription = metaDescription;
	}
	public String getMetaKeyword() {
		return metaKeyword;
	}
	public void setMetaKeyword(String metaKeyword) {
		this.metaKeyword = metaKeyword;
	}
	public MediaData getPrimaryImage() {
		return primaryImage;
	}
	public void setPrimaryImage(MediaData primaryImage) {
		this.primaryImage = primaryImage;
	}
	
}
