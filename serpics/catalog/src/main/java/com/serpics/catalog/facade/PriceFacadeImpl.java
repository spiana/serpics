package com.serpics.catalog.facade;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.serpics.catalog.PriceNotFoundException;
import com.serpics.catalog.data.model.AbstractProduct;
import com.serpics.catalog.data.model.Price;
import com.serpics.catalog.facade.data.PriceData;
import com.serpics.catalog.services.PriceService;
import com.serpics.catalog.services.ProductService;
import com.serpics.core.facade.AbstractPopulatingConverter;
import com.serpics.stereotype.StoreFacade;

@StoreFacade("priceFacade")
@Transactional(readOnly=true)
public class PriceFacadeImpl implements PriceFacade {
	@Autowired 
	ProductService productService;
	
	@Autowired 
	PriceService priceService;
	
	@Resource(name="priceConverter")
	AbstractPopulatingConverter<Price,	PriceData> priceConverter;
	
	/*@Override
	public AbstractProduct addPrice(PriceData price, String prouctUuid) {
		AbstractProduct product = (AbstractProduct) productService.findByUUID(prouctUuid);
		
		Price _price = new Price();
		_price.setCurrentPrice(price.getCurrentPrice());
		_price.setProductCost(price.getProductCost());
		_price.setProductPrice(price.getCurrentPrice());
		_price.setProduct(product);
		_price = priceService.addPrice(product , _price);
		
		//product.getPrices().add(_p);
		//product = productService.update((Product)product);
		price = priceConverter.convert(_price);
		return price;
	}*/
	
	public PriceData findPriceByProduct(String prouctUuid) throws PriceNotFoundException {
		AbstractProduct product = (AbstractProduct) productService.findByUUID(prouctUuid);
		Price _p = priceService.findProductPrice(product);
		PriceData price = priceConverter.convert(_p);
		return price;
	}
	
	
	
	
}