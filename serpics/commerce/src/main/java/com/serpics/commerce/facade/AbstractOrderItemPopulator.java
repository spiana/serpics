package com.serpics.commerce.facade;


import javax.annotation.Resource;

import com.serpics.catalog.data.model.Product;
import com.serpics.catalog.facade.data.ProductData;
import com.serpics.commerce.data.model.AbstractOrderitem;
import com.serpics.commerce.facade.data.AbstractOrderItemData;
import com.serpics.core.facade.AbstractPopulatingConverter;

public class AbstractOrderItemPopulator{

	
	@Resource(name="productConverter")
	private AbstractPopulatingConverter<Product, ProductData> productConverter;
	
	public void populate(AbstractOrderitem source, AbstractOrderItemData target) {
		//target.setBillingAddress(source.getBillingAddress());
		target.setId(source.getId());
		target.setUuid(source.getUuid());
		target.setCreated(source.getCreated());
		target.setUpdated(source.getUpdated());
		
		target.setQuantity(source.getQuantity());
		
		//target.setProduct((AbstractProductData)productConverter.convert((Product)source.getProduct()));
		target.setSku(source.getSku());
		target.setSkuCost(source.getSkuCost());
		target.setSkuDescription(source.getSkuDescription());
		target.setSkuNetPrice(source.getSkuNetPrice());
		target.setSkuPrice(source.getSkuPrice());
		
		target.setDiscountAmount(source.getDiscountAmount());
		target.setDiscountPerc(source.getDiscountPerc());
		
		if(source.getShippingAddressId() != null) target.setShippingAddressId(source.getShippingAddressId());
		target.setShippingCost(source.getShippingCost());
		
	}
	
	
	

}
