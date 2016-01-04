package com.serpics.catalog.services;

import javax.annotation.Resource;

import com.serpics.catalog.data.model.BaseProduct;
import com.serpics.catalog.data.repositories.BaseProductRepository;
import com.serpics.core.data.Repository;

public class BaseProductServiceImpl extends AbstractProductServiceImpl<BaseProduct> implements BaseProductService{

	@Resource
	BaseProductRepository repository;
	
	@Override
	public Repository<BaseProduct, Long> getEntityRepository() {
		return repository;
	}

}
