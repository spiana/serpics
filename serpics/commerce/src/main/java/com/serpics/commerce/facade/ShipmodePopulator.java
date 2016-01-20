package com.serpics.commerce.facade;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.serpics.catalog.data.model.Category;
import com.serpics.catalog.facade.data.CategoryData;
import com.serpics.commerce.core.CommerceEngine;
import com.serpics.commerce.data.model.Shipmode;
import com.serpics.commerce.data.model.Shipping;
import com.serpics.commerce.facade.data.ShipmodeData;
import com.serpics.commerce.facade.data.ShippingData;
import com.serpics.core.facade.AbstractPopulatingConverter;
import com.serpics.core.facade.Populator;

public class ShipmodePopulator implements Populator<Shipmode, ShipmodeData>{
	
	@Autowired
	CommerceEngine commerceEngine;
	
	private AbstractPopulatingConverter<Shipping, ShippingData> shippingConverter;
	
	@Override 
	public void populate(Shipmode source, ShipmodeData target) {		
	
		target.setId(source.getId());
		target.setCreated(source.getCreated());
		target.setDescription(source.getDescription().getText(commerceEngine.getCurrentContext().getLocale().getLanguage()));
		target.setName(source.getName());
		
		if (source.getShippings() != null){
			Set<ShippingData> shippingData = new HashSet<ShippingData>();
			for (Shipping shipping : source.getShippings()){
				shippingData.add(shippingConverter.convert(shipping));
			}
			target.setShippings(shippingData);
		}
		
		target.setUuid(source.getUuid());
		target.setUpdated(source.getUpdated());
		
	}

	public void setShippingConverter(AbstractPopulatingConverter<Shipping, ShippingData> shippingConverter) {
		this.shippingConverter = shippingConverter;
	}

	
}
