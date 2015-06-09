package com.serpics.catalog.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.serpics.catalog.data.model.Product;
import com.serpics.catalog.data.repositories.ProductRepository;
import com.serpics.commerce.session.CommerceSessionContext;
import com.serpics.core.data.Repository;
import com.serpics.core.service.AbstractEntityService;

@Service("produtService")
@Scope("store")
public class ProductServiceImpl extends AbstractEntityService<Product, Long, CommerceSessionContext> implements ProductService{

	@Autowired
	ProductRepository productRepository;

	@Override
	public Repository<Product, Long> getEntityRepository() {
		return productRepository;
	}

	
}
