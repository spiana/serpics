/*******************************************************************************
 * Copyright 2014-2016  StepFour Srl
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *  
 *      http://www.apache.org/licenses/LICENSE-2.0
 *  
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *  
 *******************************************************************************/
package com.serpics.catalog.services;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import com.serpics.base.data.model.MultilingualString;
import com.serpics.base.data.model.Store;
import com.serpics.catalog.data.model.Catalog;
import com.serpics.catalog.data.model.Catalog2StoreRelation;
import com.serpics.catalog.data.model.Pricelist;
import com.serpics.catalog.data.repositories.Catalog2StoreRelationRepository;
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

    @Resource
    PriceListRepository priceListRepository;
    
    @Resource
    Catalog2StoreRelationRepository catalog2StoreRepository;

    @Override
    public Repository<Catalog, Long> getEntityRepository() {
        return catalogRepository;
    }

   

    @Override
    public Catalog findByCode(final String code) {
        return catalogRepository.findByCode(code);
    }

    /* (non-Javadoc)
     * @see com.serpics.core.service.AbstractEntityService#create(java.lang.Object)
     */
    @Override
    @Transactional
    public Catalog create(Catalog entity) {
    	entity =  super.create(entity);
        
        Catalog2StoreRelation rel = new Catalog2StoreRelation();
        rel.setCatalog(entity);
        rel.setStore((Store)getCurrentContext().getStoreRealm());
        rel.setSelected(false);
        catalog2StoreRepository.saveAndFlush(rel);
    	
    	return entity;
    }
    
    @Override
    @Transactional
    public void initialize(){
    	 initialize("default-catalog");
    }
    
    @Override
    @Transactional
    public void initialize(String catalogName) {
        List<Catalog> defautCatalog = catalog2StoreRepository.findPrimaryCatalog((Store) getCurrentContext().getStoreRealm());

        
        if (defautCatalog.isEmpty()) {
            Catalog catalog = new Catalog();
            catalog.setCode(catalogName);
            catalog = catalogRepository.saveAndFlush(catalog);
            
            Catalog2StoreRelation rel = new Catalog2StoreRelation();
            rel.setCatalog(catalog);
            rel.setStore((Store)getCurrentContext().getStoreRealm());
            rel.setSelected(true);
            catalog2StoreRepository.saveAndFlush(rel);
            
            getCurrentContext().setCatalog(catalog);
            initializeCatalog(catalog);
            defautCatalog.add(catalog);
        }
        getCurrentContext().setCatalog(defautCatalog.get(0));
    }

    private void initializeCatalog(final Catalog catalog) {
        final Pricelist pricelist = new Pricelist();
        pricelist.setName("default-list");
        pricelist.setDefaultList(true);
        pricelist.setDescription(new MultilingualString());
        pricelist.getDescription().addText("en", "public list");
        pricelist.getDescription().addText("it", "listino al publico");
        priceListRepository.saveAndFlush(pricelist);

    }



	@Override
	public Catalog getDefaultCatalog() {
		List<Catalog> catalogs = catalog2StoreRepository.findPrimaryCatalog((Store) getCurrentContext().getStoreRealm());
		if (catalogs.isEmpty())
			return null;
		else
			return catalogs.get(0);
	}



	@Override
	public void setDefaultCatalog(String catalog_code) {
		if (StringUtils.isEmpty(catalog_code)){
			Catalog catalog = getDefaultCatalog();
			setDefaultCatalog(catalog);
		}else{
			Catalog catalog = findByCode(catalog_code);
			setDefaultCatalog(catalog);
		}
	}

	@Override
	public void setDefaultCatalog(Catalog catalog) {
		Assert.notNull(catalog, "catalog can not be null !");
		getCurrentContext().setCatalog(catalog);
	}
}
