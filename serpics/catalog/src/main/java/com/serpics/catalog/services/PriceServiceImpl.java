package com.serpics.catalog.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.serpics.base.data.model.Currency;
import com.serpics.catalog.PriceNotFoundException;
import com.serpics.catalog.data.model.AbstractProduct;
import com.serpics.catalog.data.model.Catalog;
import com.serpics.catalog.data.model.Price;
import com.serpics.catalog.data.model.Pricelist;
import com.serpics.catalog.data.repositories.AbstractProductRepository;
import com.serpics.catalog.data.repositories.PriceListRepository;
import com.serpics.catalog.data.repositories.PriceRepository;
import com.serpics.commerce.service.AbstractCommerceEntityService;
import com.serpics.core.data.Repository;
import com.serpics.membership.data.model.Store;

@Service("priceService")
@Scope("store")
@Transactional(readOnly = true)
public class PriceServiceImpl extends AbstractCommerceEntityService<Price, Long> implements PriceService {

    @Autowired
    PriceRepository priceRepository;

    @Autowired
    PriceListRepository priceListRepository;
    
    @Autowired
    AbstractProductRepository productRepository;

    @Override
    public Price create(final Price entity) {
        if (entity.getCurrency() == null) {
            final Currency currency = ((Store) getCurrentContext().getStoreRealm()).getCurrency();
            entity.setCurrency(currency);
        }
        if (entity.getPricelist() == null) {
            final List<Pricelist> l = priceListRepository.findDefaultList((Catalog) getCurrentContext().getCatalog());
            assert !l.isEmpty() : "missing default price list !";
            entity.setPricelist(l.get(0));
        }
        return super.create(entity);
    }

    @Override
    public Repository<Price, Long> getEntityRepository() {
        return priceRepository;
    }
  
    @Override
    public List<Price> findValidPricesforProduct(final AbstractProduct product, final Pricelist pricelist , Currency currency) {
        return priceRepository.findValidPricesForProduct(product, pricelist, currency);
    }
    @Override
    public List<Price> findValidPricesforProduct(final AbstractProduct product) {
        final List<Pricelist> defaultPricelist = priceListRepository.findDefaultList((Catalog) getCurrentContext()
                .getCatalog());
        final Currency currency = (Currency) getCurrentContext().getCurrency();
        assert !defaultPricelist.isEmpty() : "missing default price list !";
        return findValidPricesforProduct(product, defaultPricelist.get(0), currency);
    }
	@Override
	public Price findProductPrice(AbstractProduct product, Pricelist priceList , Currency currency) throws PriceNotFoundException{
		List<Price> prices = findValidPricesforProduct(product, priceList, currency);
		if( prices.isEmpty())
			throw  new PriceNotFoundException(product);
		
		return prices.get(0);
	}
	
	@Override
	public Price findProductPrice(AbstractProduct product, Currency currency)
			throws PriceNotFoundException {
		 final List<Pricelist> defaultPricelist = priceListRepository.findDefaultList((Catalog) getCurrentContext()
	                .getCatalog());
	       
	        Assert.isTrue(!defaultPricelist.isEmpty(), "missing default price list !");
	       
	        return findProductPrice(product, defaultPricelist.get(0) , currency);
	}
	@Override
	public Price findProductPrice(AbstractProduct product) throws PriceNotFoundException{
		 final List<Pricelist> defaultPricelist = priceListRepository.findDefaultList((Catalog) getCurrentContext()
	                .getCatalog());
		 final Currency currency = (Currency) getCurrentContext().getCurrency();
		 return findProductPrice(product, currency);
	}

	@Override
	public AbstractProduct addPrice(AbstractProduct product, Price price, Pricelist pricelist) {
		Assert.notNull(product );
		Assert.notNull(price );
		Assert.notNull(pricelist );
		price.setPricelist(pricelist);
		price.setProduct(product);

		priceRepository.create(price);
		return productRepository.findOne(product.getId());
	}

	@Override
	public AbstractProduct addPrice(AbstractProduct product, Price price) {
		 final List<Pricelist> defaultPricelist = priceListRepository.findDefaultList((Catalog) getCurrentContext()
	                .getCatalog());
	        assert !defaultPricelist.isEmpty() : "missing default price list !";
	        return addPrice(product, price, defaultPricelist.get(0));
	}
}
