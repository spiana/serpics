package com.serpics.catalog.services;

import java.util.HashSet;
import java.util.Set;

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

import com.serpics.catalog.data.model.BaseProduct;
import com.serpics.catalog.data.model.Brand;
import com.serpics.catalog.data.model.Category;
import com.serpics.catalog.data.model.CategoryProductRelation;
import com.serpics.catalog.data.model.CtentryMedia;
import com.serpics.catalog.data.repositories.BrandRepository;
import com.serpics.catalog.data.repositories.Category2ProductRepository;
import com.serpics.commerce.session.CommerceSessionContext;
import com.serpics.core.service.AbstractEntityService;

public abstract class  AbstractProductServiceImpl<T extends BaseProduct> extends AbstractEntityService<T, Long, CommerceSessionContext>
	implements AbstractProductService<T>{
	
	private final Logger logger = LoggerFactory.getLogger(AbstractProductServiceImpl.class);
	 
	@Autowired
    Category2ProductRepository categoryProductRepository;
	
	@Autowired
    BrandRepository brandRepository;
	
	protected Specification<T> findByNameSpecification(final String name) {
			return new Specification<T>() {
				@Override
				public Predicate toPredicate(final Root<T> root, final CriteriaQuery<?> query, 
						final CriteriaBuilder cb) {
							Predicate p = cb.equal(root.get("code"), name);
							return p;
				}
			};
		}
	
	protected Specification<T> findByBrandSpecification(final Brand brand) {
		return new Specification<T>() {
			@Override
			public Predicate toPredicate(final Root<T> root, final CriteriaQuery<?> query, 
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
	public T create(T product, final Category category, final Brand brand) {
	    product = this.create(product);
	    if (category != null)
	    	addCategoryRelation(product, category);
	    if (brand != null)
	    	addBrand(product, brand);
	    return product;
	   
	}

	
	@Transactional
	public T addParentCategory(T product,  Category category) {
		addCategoryRelation(product, category);
		return product;
	}
	

	private void addCategoryRelation(T product, Category category) {
		final CategoryProductRelation ctcgrel = new CategoryProductRelation();
        ctcgrel.setChildProduct((BaseProduct) product);
        ctcgrel.setParentCategory(category);
        categoryProductRepository.saveAndFlush(ctcgrel);
        
	}
	

	public Page<T> findProductByCategory(final Category category,Pageable pagination) {
		Page<? extends BaseProduct> l = null;
		try {
			l =  categoryProductRepository.findProductsByCategory(category, pagination);
		} catch(final Exception e) {
			logger.error("", e);
		}
		return (Page<T>)l;
	}

	@Override
	public Page<T> findProductByBrand(Brand brand, Pageable pagination){
		Page<T> page =  getEntityRepository().findAll(findByBrandSpecification(brand), pagination);
		return page;
	}


	@Override
	@Transactional
	public T addMedia(T product, CtentryMedia media) {
		Set<CtentryMedia> list = product.getMedias();
		if (list == null)
			list = new HashSet<CtentryMedia>();
		
		list.add(media);
		product.setMedias(list);
		product = getEntityRepository().saveAndFlush(product);
		return product;
	}
	
	@Override
	public T findByName(String name){
		return getEntityRepository().findOne(findByNameSpecification(name));
	}


	@Override
	@Transactional
	public T addBrand(T product, Brand brand) {
		product.setBrand(brand);
		getEntityRepository().saveAndFlush(product);
		return product;
	}
	
	
	
}
