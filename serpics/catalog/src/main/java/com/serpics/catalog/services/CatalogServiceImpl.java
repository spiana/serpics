package com.serpics.catalog.services;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.serpics.base.data.model.MultilingualString;
import com.serpics.catalog.data.model.Catalog;
import com.serpics.catalog.data.model.Pricelist;
import com.serpics.catalog.data.repositories.CatalogRepository;
import com.serpics.catalog.data.repositories.PriceListRepository;
import com.serpics.commerce.service.AbstractCommerceEntityService;
import com.serpics.core.data.Repository;

@Service("catalogService")
@Scope("store")
@Transactional(readOnly = true)
public class CatalogServiceImpl extends AbstractCommerceEntityService<Catalog, Long> implements CatalogService {

    @Resource
    CatalogRepository catalogRepository;

    @Autowired
    PriceListRepository priceListRepository;

    @Override
    public Repository<Catalog, Long> getEntityRepository() {
        return catalogRepository;
    }

   

    @Override
    public Catalog findByCode(final String code) {
        return catalogRepository.findByCode(code);
    }

    @Override
    public void initialize() {
        Catalog catalog = catalogRepository.findByCode("default-catalog");

        if (catalog == null) {
            catalog = new Catalog();
            catalog.setCode("default-catalog");
            catalog = catalogRepository.saveAndFlush(catalog);
            initializeCatalog(catalog);
        }
        getCurrentContext().setCatalog(catalog);
    }

    private void initializeCatalog(final Catalog catalog) {
        final Pricelist pricelist = new Pricelist();
        pricelist.setName("default-list");
        pricelist.setCatalog(catalog);
        pricelist.setDefaultList(true);
        pricelist.setDescription(new MultilingualString());
        pricelist.getDescription().addText("en", "public list");
        pricelist.getDescription().addText("it", "listino al publico");
        priceListRepository.saveAndFlush(pricelist);

    }
}
