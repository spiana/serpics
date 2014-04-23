package com.serpics.catalog.services;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.serpics.base.persistence.MultilingualString;
import com.serpics.catalog.persistence.Catalog;
import com.serpics.catalog.persistence.Pricelist;
import com.serpics.catalog.repositories.CatalogRepository;
import com.serpics.catalog.repositories.PriceListRepository;
import com.serpics.core.data.Repository;
import com.serpics.core.service.AbstractEntityService;

@Service("catalogService")
@Scope("store")
@Transactional(readOnly = true)
public class CatalogServiceImpl extends AbstractEntityService<Catalog, Long> implements CatalogService {

    @Resource
    CatalogRepository catalogRepository;

    @Autowired
    PriceListRepository priceListRepository;

    @Override
    public Repository<Catalog, Long> getEntityRepository() {
        return catalogRepository;
    }

    @Override
    public Specification<Catalog> getBaseSpec() {
        return new Specification<Catalog>() {
            @Override
            public Predicate toPredicate(final Root<Catalog> arg0, final CriteriaQuery<?> arg1,
                    final CriteriaBuilder arg2) {

                return arg2.isNotNull(arg0.get("uuid"));
            }
        };
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
        pricelist.getDescription().addText("it", "listno al publico");
        priceListRepository.saveAndFlush(pricelist);

    }
}
