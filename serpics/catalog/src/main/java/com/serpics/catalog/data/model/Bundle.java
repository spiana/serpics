package com.serpics.catalog.data.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.serpics.core.datatype.ProductType;

@Entity
@DiscriminatorValue("3")
public class Bundle extends AbstractProduct {

	public Bundle() {
		super();
		this.productType = ProductType.BUNDLE;
	}

	public Bundle(Integer buyable, String sku) {
		super(buyable, sku);

	}

}
