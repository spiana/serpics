package com.serpics.catalog;

import com.serpics.catalog.data.model.AbstractProduct;

public class PriceNotFoundException extends ProductNotFoundException {
	private static final long serialVersionUID = -6351739784145669673L;

	public PriceNotFoundException(AbstractProduct product) {
		super(String.format("Price not found for product %s", product.getCode()));
	}

	

}
