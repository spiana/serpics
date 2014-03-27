package com.serpics.catalog.services;

import com.serpics.catalog.persistence.Catalog;
import com.serpics.core.service.EntityService;

public interface CatalogService extends EntityService<Catalog, Long> {

    public void initialize();

    public Catalog findByCode(String code);
}
