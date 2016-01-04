package com.serpics.catalog.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.serpics.catalog.data.model.Product;
import com.serpics.catalog.data.repositories.ProductRepository;
import com.serpics.core.data.Repository;

@Service("produtService")
@Scope("store")
@Transactional(readOnly=true)
public class ProductServiceImpl extends AbstractProductServiceImpl<Product> implements ProductService{
	 private final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);
	 
	@Autowired
	ProductRepository productRepository;
	
	@Override
	public Repository<Product, Long> getEntityRepository() {
		return productRepository;
	}

	
	
}
