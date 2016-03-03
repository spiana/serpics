package com.serpics.commerce.services;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.serpics.commerce.ShipmodeException;
import com.serpics.commerce.data.model.AbstractOrder;
import com.serpics.commerce.data.model.Shipmode;
import com.serpics.commerce.service.AbstractCommerceService;
import com.serpics.commerce.strategies.ShipmodeStrategy;

@Service("shipmodeService")
@Scope("store")
@Transactional
public class ShipmodeServiceImpl extends AbstractCommerceService implements ShipmodeService{
	
	@Override
	public void calculateShippingCost(AbstractOrder order) throws ShipmodeException{
		Assert.notNull(order);
		
		ShipmodeStrategy shipmodeStrategy = getShipmodeStrategy(order);
		if (shipmodeStrategy != null){
			shipmodeStrategy.calculateShippingCost(order);
		}
	}
	
	private ShipmodeStrategy getShipmodeStrategy(AbstractOrder order )  throws ShipmodeException{
		Assert.notNull(order);
		Shipmode shipmode = order.getShipmode();
		
		if (shipmode != null && shipmode.getShipmodeStrategy() != null){
			ShipmodeStrategy paymentStrategy = (ShipmodeStrategy) getEngine().getApplicationContext().getBean(shipmode.getShipmodeStrategy());
			if (paymentStrategy != null)
				return paymentStrategy;
			else
				throw new ShipmodeException(String.format("Shipmode stategy %s not found for order %d",
						shipmode != null ? shipmode.getShipmodeStrategy() : null , order.getId()));
		}else if (shipmode != null){
			throw new ShipmodeException(String.format("Shipmode method %s not found for order %d",
					shipmode != null ? shipmode.getName() : null , order.getId()));
		}
		return null;
	
	}

}
