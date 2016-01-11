package com.serpics.catalog.services;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;

import com.serpics.catalog.data.model.Brand;
import com.serpics.catalog.data.model.Category;
import com.serpics.catalog.data.model.CategoryProductRelation;
import com.serpics.catalog.data.model.CtentryMedia;
import com.serpics.catalog.data.model.Product;
import com.serpics.catalog.data.repositories.BrandRepository;
import com.serpics.catalog.data.repositories.Category2ProductRepository;
import com.serpics.catalog.data.repositories.ProductRepository;
import com.serpics.commerce.service.AbstractCommerceEntityService;
import com.serpics.core.data.Repository;
import com.serpics.stereotype.StoreService;

@StoreService("productService")
public class ProductServiceImpl extends AbstractCommerceEntityService<Product, Long> implements ProductService{

	@Resource
	ProductRepository repository;
	

	public Repository<Product, Long> getEntityRepository() {
		return repository;
	}
	
	private final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);
	 
	@Autowired
    Category2ProductRepository categoryProductRepository;
	
	@Autowired
    BrandRepository brandRepository;
	
	protected Specification<Product> findByNameSpecification(final String name) {
			return new Specification<Product>() {
				@Override
				public Predicate toPredicate(final Root<Product> root, final CriteriaQuery<?> query, 
						final CriteriaBuilder cb) {
							Predicate p = cb.equal(root.get("code"), name);
							return p;
				}
			};
		}
	
	protected Specification<Product> findByBrandSpecification(final Brand brand) {
		return new Specification<Product>() {
			@Override
			public Predicate toPredicate(final Root<Product> root, final CriteriaQuery<?> query, 
					final CriteriaBuilder cb) {
						Predicate p = cb.equal(root.get("brand"), brand);
						return p;
			}
		};
	}
	
	protected Specification<CategoryProductRelation> findByCategorySpecification(final Category category) {
		return new Specification<CategoryProductRelation>() {
			@Override
			public Predicate toPredicate(final Root<CategoryProductRelation> root, final CriteriaQuery<?> query, 
					final CriteriaBuilder cb) {
						Predicate p = cb.equal(root.get("parentCategory"), category);
						return p;
			}
		};
	}
	
	@Override
	@Transactional
	public Product create(Product product, final Category category, final Brand brand) {
	    product = this.create(product);
	    if (category != null)
	    	addCategoryRelation(product, category);
	    if (brand != null)
	    	addBrand(product, brand);
	    return product;
	   
	}

	
	@Transactional
	public Product addParentCategory(Product product,  Category category) {
		addCategoryRelation(product, category);
		return product;
	}
	

	private void addCategoryRelation(Product product, Category category) {
		final CategoryProductRelation ctcgrel = new CategoryProductRelation();
        ctcgrel.setChildProduct((Product) product);
        ctcgrel.setParentCategory(category);
        categoryProductRepository.saveAndFlush(ctcgrel);
        
	}
	

	public Page<Product> findProductByCategory(final Category category,Pageable pagination) {
		Page<? extends Product> l = null;
		try {
			l =  categoryProductRepository.findProductsByCategory(category, pagination);
		} catch(final Exception e) {
			logger.error("", e);
		}
		return (Page<Product>)l;
	}

	@Override
	public Page<Product> findProductByBrand(Brand brand, Pageable pagination){
		Page<Product> page =  getEntityRepository().findAll(findByBrandSpecification(brand), pagination);
		return page;
	}


	@Override
	@Transactional
	public Product addMedia(Product product, CtentryMedia media) {
		Set<CtentryMedia> list = product.getMedias();
		if (list == null)
			list = new HashSet<CtentryMedia>();
		
		list.add(media);
		product.setMedias(list);
		product = getEntityRepository().saveAndFlush(product);
		return product;
	}
	
	@Override
	public Product findByName(String name){
		return getEntityRepository().findOne(findByNameSpecification(name));
	}


	@Override
	@Transactional
	public Product addBrand(Product product, Brand brand) {
		product.setBrand(brand);
		getEntityRepository().saveAndFlush(product);
		return product;
	}
	

}
