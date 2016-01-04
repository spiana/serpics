package com.serpics.catalog.data.model;

import javax.persistence.Entity;

import com.serpics.core.datatype.ProductType;

@Entity
public class Product extends BaseProduct {

	private static final long serialVersionUID = 1L;

	public Product() {
		super();
		this.productType = ProductType.PRODUCT;
	}

	public Product(Integer buyable, String sku) {
		super(buyable, sku);
		this.productType = ProductType.PRODUCT;
	}

}