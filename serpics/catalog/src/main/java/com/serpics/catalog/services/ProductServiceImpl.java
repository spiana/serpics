package com.serpics.catalog.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.serpics.catalog.data.model.AbstractProduct;
import com.serpics.catalog.data.model.Category;
import com.serpics.catalog.data.model.CategoryProductRelation;
import com.serpics.catalog.data.model.Media;
import com.serpics.catalog.data.model.Product;
import com.serpics.catalog.data.repositories.BrandRepository;
import com.serpics.catalog.data.repositories.Category2ProductRepository;
import com.serpics.catalog.data.repositories.ProductRepository;
import com.serpics.catalog.data.specification.ProductSpecification;
import com.serpics.commerce.session.CommerceSessionContext;
import com.serpics.core.data.Repository;
import com.serpics.core.service.AbstractEntityService;

@Service("produtService")
@Scope("store")
@Transactional(readOnly=true)
public class ProductServiceImpl extends AbstractEntityService<Product, Long, CommerceSessionContext> implements ProductService{
	 private final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);
	@Autowired
	ProductRepository productRepository;

	@Autowired
    Category2ProductRepository categoryProductRepository;
	
	
	
	@Autowired
    BrandRepository brandRepository;
	
	@Override
	public Repository<Product, Long> getEntityRepository() {
		return productRepository;
	}

	
	@Override
	@Transactional
	public Product create(Product product, final Category category) {
	    product = this.create(product);
	    if (category != null)
	    	addCategoryRelation(product, category);
	    return product;
	   
	}
	
	@Transactional
	public Product addParentCategory(Product product,  Category category) {
		addCategoryRelation(product, category);
		return product;
	}
	

	private void addCategoryRelation(Product product, Category category) {
		final CategoryProductRelation ctcgrel = new CategoryProductRelation();
        ctcgrel.setChildProduct((AbstractProduct) product);
        ctcgrel.setParentCategory(category);
        categoryProductRepository.saveAndFlush(ctcgrel);
        
	}
	

	public Page<Product> findProductByCategory(final Category category,Pageable pagination) {
		Page<Product> l = null;
		try {
			l = categoryProductRepository.findProductsByCategory(category, pagination);
		} catch(final Exception e) {
			logger.error("", e);
		}
		return l;
	}


	@Override
	@Transactional
	public Product addMedia(Product product, Media media) {
		Set<Media> list = product.getMedias();
		if (list == null)
			list = new HashSet<Media>();
		
		list.add(media);
		product.setMedias(list);
		product = productRepository.update(product);
		return product;
	}
	
}
