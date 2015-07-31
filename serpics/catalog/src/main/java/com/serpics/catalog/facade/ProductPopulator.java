package com.serpics.catalog.facade;

import com.serpics.catalog.data.model.AbstractProduct;
import com.serpics.catalog.facade.data.ProductData;
import com.serpics.core.facade.Populator;

public class ProductPopulator implements Populator<AbstractProduct, ProductData> {

	@Override
	public void populate(AbstractProduct source, ProductData target) {
		target.setCode(source.getCode());
		//target.setDescription(source.getDescription().getText("it"));
		
		
	}

}
