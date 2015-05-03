package com.serpics.catalog.data.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.serpics.core.datatype.ProductType;

@Entity
@DiscriminatorValue("2")
public class Product extends AbstractProduct {

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
