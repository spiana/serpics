package com.serpics.commerce.facade;


import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.springframework.beans.factory.annotation.Required;

import com.serpics.commerce.data.model.AbstractOrder;
import com.serpics.commerce.data.model.AbstractOrderitem;
import com.serpics.commerce.facade.data.AbstractOrderItemData;
import com.serpics.commerce.facade.data.AbstractOrdersData;
import com.serpics.core.facade.AbstractPopulatingConverter;
import com.serpics.core.facade.Populator;

public  class AbstractOrderPopulator implements Populator<AbstractOrder,  AbstractOrdersData>{
	private AbstractPopulatingConverter<AbstractOrderitem, AbstractOrderItemData> abstractOrderItemConverter;
	
	@Override
	public void populate(AbstractOrder source, AbstractOrdersData target) {
		target.setBillingAddress(source.getBillingAddress());
		target.setId(source.getId());
		
		target.setTotalProduct(source.getTotalProduct());
		target.setTotalService(source.getTotalService());
		target.setTotalShipping(source.getTotalShipping());
		target.setTotalTax(source.getTotalTax());
		Set<AbstractOrderItemData> items = new HashSet<AbstractOrderItemData>();
		
		if(source.getOrderitems() != null) {
		
			for (AbstractOrderitem abstractOrderitem : source.getOrderitems() ){
				Assert.assertNotNull(abstractOrderitem.getId());
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

}
