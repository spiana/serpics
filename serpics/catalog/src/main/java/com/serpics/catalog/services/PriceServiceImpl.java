package com.serpics.catalog.services;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.serpics.base.data.model.Currency;
import com.serpics.base.data.model.Store;
import com.serpics.catalog.PriceNotFoundException;
import com.serpics.catalog.data.model.Price;
import com.serpics.catalog.data.model.Pricelist;
import com.serpics.catalog.data.model.Product;
import com.serpics.catalog.data.repositories.PriceRepository;
import com.serpics.catalog.data.repositories.ProductRepository;
import com.serpics.commerce.service.AbstractCommerceEntityService;
import com.serpics.core.data.Repository;

@Service("priceService")
@Scope("store")
@Transactional(readOnly = true)
public class PriceServiceImpl extends AbstractCommerceEntityService<Price, Long> implements PriceService {

    @Autowired
    PriceRepository priceRepository;

    @Resource
    PriceListService priceListService;
    
    @Autowired
    ProductRepository productRepository;

    @Override
    public Price create(final Price entity) {
        if (entity.getCurrency() == null) {
            final Currency currency = ((Store) getCurrentContext().getStoreRealm()).getCurrency();
            entity.setCurrency(currency);
        }
        if (entity.getPricelist() == null) {
            entity.setPricelist(priceListService.getCurrentPriceList());
        }
        return super.create(entity);
    }

    @Override
    public Repository<Price, Long> getEntityRepository() {
        return priceRepository;
    }
  
    @Override
    public List<Price> findValidPricesforProduct(final Product product, final Pricelist pricelist , Currency currency) {
        return priceRepository.findValidPricesForProduct(product, pricelist, currency);
    }
    @Override
    public List<Price> findValidPricesforProduct(final Product product) {
        final Pricelist defaultPricelist = priceListService.getCurrentPriceList();
        final Currency currency = (Currency) getCurrentContext().getCurrency();
        return findValidPricesforProduct(product, defaultPricelist, currency);
    }
	@Override
	public Price findProductPrice(Product product, Pricelist priceList , Currency currency) throws PriceNotFoundException{
		List<Price> prices = findValidPricesforProduct(product, priceList, currency);
		if( prices.isEmpty())
			throw  new PriceNotFoundException(product);
		
		return prices.get(0);
	}
	
	@Override
	public Price findProductPrice(Product product, Currency currency)
			throws PriceNotFoundException {
		 final Pricelist defaultPricelist = priceListService.getCurrentPriceList();
	     return findProductPrice(product, defaultPricelist, currency);
	}
	@Override
	public Price findProductPrice(Product product) throws PriceNotFoundException{
//		 final List<Pricelist> defaultPricelist = priceListRepository.findDefaultList((Catalog) getCurrentContext()
//	                .getCatalog());
		 final Currency currency = (Currency) getCurrentContext().getCurrency();
		 return findProductPrice(product, currency);
	}

	@Override
	public Product addPrice(Product product, Price price, Pricelist pricelist) {
		Assert.notNull(product );
		Assert.notNull(price );
		Assert.notNull(pricelist );
		price.setPricelist(pricelist);
		price.setProduct(product);

		price = priceRepository.saveAndFlush(price);
		 productRepository.detach(product);
		 return productRepository.findOne(product.getId());
	}

	@Override
	public Product addPrice(Product product, Price price) {
		 final Pricelist defaultPricelist = priceListService.getCurrentPriceList();
	     
	        return addPrice(product, price, defaultPricelist);
	}
	
	
}
