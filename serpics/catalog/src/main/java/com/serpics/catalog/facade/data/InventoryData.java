package com.serpics.catalog.facade.data;

public class InventoryData {

	protected double stockLevelAmount;
	protected String inventoryStatus;
	
	public double getStockLevelAmount() {
		return stockLevelAmount;
	}
	public void setStockLevelAmount(double stockLevelAmount) {
		this.stockLevelAmount = stockLevelAmount;
	}
	public String getInventoryStatus() {
		return inventoryStatus;
	}
	public void setInventoryStatus(String inventoryStatus) {
		this.inventoryStatus = inventoryStatus;
	}
}
