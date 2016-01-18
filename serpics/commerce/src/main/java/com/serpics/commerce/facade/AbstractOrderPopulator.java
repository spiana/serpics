package com.serpics.commerce.facade;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.util.Assert;

import com.serpics.commerce.data.model.AbstractOrder;
import com.serpics.commerce.data.model.AbstractOrderitem;
import com.serpics.commerce.facade.data.AbstractOrderItemData;
import com.serpics.commerce.facade.data.AbstractOrdersData;
import com.serpics.core.facade.AbstractPopulatingConverter;
import com.serpics.membership.data.model.AbstractAddress;
import com.serpics.membership.facade.data.AddressData;



@SuppressWarnings("rawtypes")
public  class AbstractOrderPopulator {
	private AbstractPopulatingConverter<AbstractOrderitem, AbstractOrderItemData> abstractOrderItemConverter;
	private AbstractPopulatingConverter<AbstractAddress, AddressData> addressConverter;
	
	@SuppressWarnings("unchecked")
	public void populate(AbstractOrder source, AbstractOrdersData target) {
		if (source.getShippingAddress() != null){
			target.setShippingAddress(addressConverter.convert(source.getShippingAddress()));
		}
		if (source.getBillingAddress() != null){
			target.setBillingAddress(addressConverter.convert(source.getBillingAddress()));
		}
		target.setId(source.getId());
		target.setUuid(source.getUuid());
		target.setCreated(source.getCreated());
		target.setUpdated(source.getUpdated());
		
		target.setTotalProduct(source.getTotalProduct());
		target.setTotalService(source.getTotalService());
		target.setTotalShipping(source.getTotalShipping());
		target.setTotalTax(source.getTotalTax());
		Set<AbstractOrderItemData> items = new HashSet<AbstractOrderItemData>();
		
		
			
		if(source.getItems() != null) {
		
			for (AbstractOrderitem abstractOrderitem : source.getItems() ){
				Assert.notNull(abstractOrderitem.getId());
				AbstractOrderItemData itemData = abstractOrderItemConverter.convert(abstractOrderitem);
				items.add(itemData);
			} 
			target.setOrderItems(items);
		}
	}
	
	
	@Required
	public void setAbstractOrderItemConverter(
			AbstractPopulatingConverter<AbstractOrderitem, AbstractOrderItemData> abstractOrderItemConverter) {
		this.abstractOrderItemConverter = abstractOrderItemConverter;
	}

	@Required
	public void setAddressConverter(AbstractPopulatingConverter<AbstractAddress, AddressData> addressConverter) {
		this.addressConverter = addressConverter;
	}

}
