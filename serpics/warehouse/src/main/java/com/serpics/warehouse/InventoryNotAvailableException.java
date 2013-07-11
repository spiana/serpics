package com.serpics.warehouse;

import com.serpics.core.SerpicsException;

public class InventoryNotAvailableException extends SerpicsException {
	private static final long serialVersionUID = 1L;

	private final String sku;
	private final double quantity;

	public InventoryNotAvailableException(String sku, double quantity) {
		super();
		this.sku = sku;
		this.quantity = quantity;
	}

	@Override
	public String getMessage() {
		return "no quantity available for product sku [" + sku + "]";
	}

	public String getSku() {
		return sku;
	}

	public double getQuantity() {
		return quantity;
	}

}
