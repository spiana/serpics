package com.serpics.catalog.facade;

import com.serpics.catalog.PriceNotFoundException;
import com.serpics.catalog.facade.data.PriceData;

public interface PriceFacade {
	//public PriceData addPrice(PriceData price, String prouctUiid);
	public PriceData findPriceByProduct(Long productId) throws PriceNotFoundException;
	
}
