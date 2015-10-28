package com.serpics.catalog.data.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.serpics.core.datatype.ProductType;

@Entity
@DiscriminatorValue("3")
public class Bundle extends AbstractProduct {

	private static final long serialVersionUID = -8602548735468668811L;

	public Bundle() {
		super();
		this.productType = ProductType.BUNDLE;
	}

	public Bundle(Integer buyable, String sku) {
		super(buyable, sku);

	}

}
