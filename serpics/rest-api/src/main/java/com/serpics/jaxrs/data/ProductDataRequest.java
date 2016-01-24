package com.serpics.jaxrs.data;

public class ProductDataRequest {
	
	
	private String code;
	private String description;
	private Boolean buyable;
	private Boolean downloadable;
	
	
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	

}