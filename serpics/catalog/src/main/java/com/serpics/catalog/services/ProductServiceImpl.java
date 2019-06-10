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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;

import com.serpics.base.commerce.CommerceEngine;
import com.serpics.base.commerce.service.AbstractCommerceEntityService;
import com.serpics.catalog.data.model.Brand;
import com.serpics.catalog.data.model.Category;
import com.serpics.catalog.data.model.CategoryProductRelation;
import com.serpics.catalog.data.model.CategoryRelation;
import com.serpics.catalog.data.model.CtentryMedia;
import com.serpics.catalog.data.model.Product;
import com.serpics.catalog.data.repositories.BrandRepository;
import com.serpics.catalog.data.repositories.Category2ProductRepository;
import com.serpics.catalog.data.repositories.ProductRepository;
import com.serpics.core.data.Repository;
import com.serpics.i18n.data.model.MultilingualString;
import com.serpics.i18n.data.model.MultilingualText;
import com.serpics.stereotype.StoreService;

@StoreService("productService")
@Transactional(readOnly=true)
public class ProductServiceImpl extends AbstractCommerceEntityService<Product, Long> implements ProductService{

	@Resource
	ProductRepository repository;
	
	@Autowired
	CommerceEngine commerceEngine;
	

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
	
	protected Specification<Product> findBySearchSpecification(final String searchText) {
		return new Specification<Product>() {
			@Override
			public Predicate toPredicate(final Root<Product> root, final CriteriaQuery<?> query, 
					final CriteriaBuilder cb) {
						List<Predicate> p = new ArrayList<>();
						
						//Search in product.code
						String searchTextLower = searchText.toLowerCase();
						p.add(cb.like(cb.lower(root.<String>get("code")), "%"+searchTextLower+"%"));
						
						String language = commerceEngine.getCurrentContext().getLocale().getLanguage();
						
						//Search in product.name (MultilingualString)
						Subquery<MultilingualString> subqueryName = query.subquery(MultilingualString.class);
						Root<MultilingualString> subRootName = subqueryName.from(MultilingualString.class);

						subqueryName.select(subRootName);
						Path<String> subPathLanguageName = subRootName.join("map").get("language");
						Path<String> subPathTextName = subRootName.join("map").get("text");
						subqueryName.where(cb.and(cb.equal(subPathLanguageName,language),(cb.like(cb.lower(subPathTextName), "%"+searchTextLower+"%"))));
						
						p.add(root.get("name").in(subqueryName));
						
						//Search in product.description (MultilingualText)
						Subquery<MultilingualText> subqueryDescription = query.subquery(MultilingualText.class);
						Root<MultilingualText> subRootDescription = subqueryDescription.from(MultilingualText.class);

						subqueryDescription.select(subRootDescription);
						Path<String> subPathLanguageDescription = subRootDescription.join("map").get("language");
						Path<String> subPathTextDescription = subRootDescription.join("map").get("text");
						subqueryDescription.where(cb.and(cb.equal(subPathLanguageDescription,language),(cb.like(cb.lower(subPathTextDescription), "%"+searchTextLower+"%"))));
						
						p.add(root.get("description").in(subqueryDescription));
				
						return cb.or(p.toArray(new Predicate[p.size()]));
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
	
	protected Specification<Product> findByCategorySpecification(final Category category) {
		return new Specification<Product>() {
			@Override
			public Predicate toPredicate(final Root<Product> root, final CriteriaQuery<?> query, 
					final CriteriaBuilder cb) {
						
						Subquery<Product> subquery = query.subquery(Product.class);
						Root<CategoryProductRelation> subRoot = subquery.from(CategoryProductRelation.class);
						
						//Select childCategory = category
						Subquery<Category> childSubquery = subquery.subquery(Category.class);
						Root<CategoryRelation> childSubRoot = childSubquery.from(CategoryRelation.class);
						childSubquery.select(childSubRoot.<Category>get("childCategory"));
						childSubquery.where(cb.equal(childSubRoot.get("parentCategory"), category));

						//Select parentCategory = category
						subquery.select(subRoot.<Product>get("childProduct"));
						subquery.where(cb.or(cb.equal(subRoot.get("parentCategory"),category),subRoot.get("parentCategory").in(childSubquery)));
						
						Predicate p = root.in(subquery);
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
		Page<Product> page =  getEntityRepository().findAll(findByCategorySpecification(category), pagination);
		return page;
	}

	@Override
	public Page<Product> findProductsBySearch(final String searchText,Pageable pagination) {
		Page<Product> page =  getEntityRepository().findAll(findBySearchSpecification(searchText), pagination);
		return page;
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
	
	@Override
	public Product findByCode(final String code) {
		return repository.findOne(new Specification<Product>() {
            @Override
            public Predicate toPredicate(final Root<Product> root, final CriteriaQuery<?> query,
                    final CriteriaBuilder cb) {
            	Expression<String> e = root.get("code");
            		
                return cb.equal(e, code);
            }
		});
	}

	@Override
	public List<Product> listProductByCategory(Category category) {
		List<Product> list =  getEntityRepository().findAll(findByCategorySpecification(category));
		return list;
	}

	@Override
	public List<Product> listProductByBrand(Brand brand) {
		List<Product> list =  getEntityRepository().findAll(findByBrandSpecification(brand));
		return list;
	}
}
