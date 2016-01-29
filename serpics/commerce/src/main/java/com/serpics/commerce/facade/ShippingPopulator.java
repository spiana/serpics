package com.serpics.commerce.facade;

import com.serpics.commerce.data.model.Shipping;
import com.serpics.commerce.facade.data.ShippingData;
import com.serpics.core.facade.Populator;

public class ShippingPopulator implements Populator<Shipping, ShippingData>{
	
	@Override 
	public void populate(Shipping source, ShippingData target) {		
	
		target.setId(source.getId());
		target.setCreated(source.getCreated());
		target.setUuid(source.getUuid());
		target.setUpdated(source.getUpdated());
		target.setRagestart(source.getRangestart());
		target.setValue(source.getValue());
		
	}

	
}
