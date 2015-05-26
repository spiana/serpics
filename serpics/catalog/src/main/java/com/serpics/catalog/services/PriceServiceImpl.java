package com.serpics.catalog.services;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.serpics.base.data.model.Currency;
import com.serpics.catalog.data.model.AbstractProduct;
import com.serpics.catalog.data.model.Catalog;
import com.serpics.catalog.data.model.Price;
import com.serpics.catalog.data.model.Pricelist;
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
    public Specification<Price> getBaseSpec() {
        return new Specification<Price>() {
            @Override
            public Predicate toPredicate(final Root<Price> arg0, final CriteriaQuery<?> arg1, final CriteriaBuilder arg2) {
                return arg2.isNotNull(arg0.get("uuid"));
            }
        };
    }

    @Override
    public List<Price> findValidPricesforProduct(final AbstractProduct product, final Pricelist pricelist) {
        return priceRepository.findValidPricesForProduct(product, pricelist);
    }

    @Override
    public List<Price> findValidPricesforProduct(final AbstractProduct product) {
        final List<Pricelist> defaultPricelist = priceListRepository.findDefaultList((Catalog) getCurrentContext()
                .getCatalog());
        assert !defaultPricelist.isEmpty() : "missing default price list !";
        return findValidPricesforProduct(product, defaultPricelist.get(0));
    }
}
