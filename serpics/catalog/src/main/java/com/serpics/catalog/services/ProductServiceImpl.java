package com.serpics.catalog.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.serpics.catalog.data.model.AbstractProduct;
import com.serpics.catalog.data.model.Category;
import com.serpics.catalog.data.model.CategoryProductRelation;
import com.serpics.catalog.data.model.Product;
import com.serpics.catalog.data.repositories.Category2ProductRepository;
import com.serpics.catalog.data.repositories.ProductRepository;
import com.serpics.commerce.session.CommerceSessionContext;
import com.serpics.core.data.Repository;
import com.serpics.core.service.AbstractEntityService;

@Service("produtService")
@Scope("store")
public class ProductServiceImpl extends AbstractEntityService<Product, Long, CommerceSessionContext> implements ProductService{

	@Autowired
	ProductRepository productRepository;

	@Autowired
    Category2ProductRepository categoryProductRepository;
	
	@Override
	public Repository<Product, Long> getEntityRepository() {
		return productRepository;
	}

	
	@Override
	@Transactional
	public Product create(Product product, final Category parent) {
	    product = this.create(product);
	    if (parent != null) {
	        final CategoryProductRelation ctcgrel = new CategoryProductRelation();
	        ctcgrel.setChildProduct((AbstractProduct) product);
	        ctcgrel.setParentCategory(parent);
	        categoryProductRepository.create(ctcgrel);
	    }
	
	    return product;
	}
	
}
