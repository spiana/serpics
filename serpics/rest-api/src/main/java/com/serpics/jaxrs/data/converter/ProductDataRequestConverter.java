package com.serpics.jaxrs.data.converter;


import com.serpics.catalog.facade.data.ProductData;
import com.serpics.core.facade.Populator;
import com.serpics.jaxrs.data.ProductDataRequest;

public class ProductDataRequestConverter implements Populator<ProductDataRequest, ProductData>{

	@Override
	public void populate(ProductDataRequest source, ProductData target) {
		
		target.setBuyable(source.isBuyable());
		target.setDowloadable(source.isDownloadable());
		
	}
}
