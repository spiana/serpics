package com.serpics.jaxrs.data;

public class ProductDataRequest {
	
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
	

}