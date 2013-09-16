package com.serpics.catalog.services;

import static com.serpics.catalog.repositories.CategorySpecification.isCategoryInCatalog;
import static org.springframework.data.jpa.domain.Specifications.where;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.serpics.catalog.persistence.AbstractProduct;
import com.serpics.catalog.persistence.Bundle;
import com.serpics.catalog.persistence.Catalog;
import com.serpics.catalog.persistence.Category;
import com.serpics.catalog.persistence.CategoryRelation;
import com.serpics.catalog.persistence.Ctentry;
import com.serpics.catalog.persistence.CtentryRelationPK;
import com.serpics.catalog.persistence.Product;
import com.serpics.catalog.repositories.AbstractProductRepository;
import com.serpics.catalog.repositories.BundleRepository;
import com.serpics.catalog.repositories.CatalogEntryRepository;
import com.serpics.catalog.repositories.CatalogRepository;
import com.serpics.catalog.repositories.CategoryRelationRepository;
import com.serpics.catalog.repositories.CategoryRepository;
import com.serpics.catalog.repositories.ProductRepository;
import com.serpics.core.service.AbstractService;
import com.serpics.core.service.EntityService;

@Service("catalogService")
@Scope("store")
public class CatalogServiceImpl extends AbstractService implements CatalogService, EntityService<Category, Long> {

	private static final Logger logger = LoggerFactory.getLogger(CatalogServiceImpl.class);
	
	@Resource
	CatalogEntryRepository catalogEntryRepository;
	
	@Resource
	CatalogRepository catalogRepository;

	@Resource
	CategoryRepository categoryRepository;

	@Resource
	CategoryRelationRepository categoryRelationRepository;

	@Resource
	ProductRepository productRepository;

	@Resource
	BundleRepository bundleRepository;
	
	@Resource
	AbstractProductRepository abstractProductRepository;

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public Category createCategory(Category category, Category parent) {
		Catalog catalog = (Catalog) getCurrentContext().getCatalog();
		category.setCatalog(catalog);
		category = categoryRepository.saveAndFlush(category);

		if (parent != null) {
			CtentryRelationPK ctpk = new CtentryRelationPK(parent.getCtentryId(), category.getCtentryId());
			CategoryRelation cgrel = new CategoryRelation();
			cgrel.setId(ctpk);
			categoryRelationRepository.save(cgrel);
		}

		return category;
	}

	
	@Override
	public List<Category> getChildCategories(Category parent) {
		List<Category> res = new ArrayList<>(5);

		try {
			List<CategoryRelation> findByCategory_parent = categoryRelationRepository
					.findByParentCategory(parent);
			for (CategoryRelation rel : findByCategory_parent) {
				res.add(rel.getChildCategory());
			}
		} catch (Exception e) {
			logger.error("", e);
		} finally {
			return res;
		}
	}
	
	
	@Override
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public Catalog getCatalog(String code) {
		return catalogRepository.findByCode(code);
	}
	
	
	@Override
	public List<Category> findRootCategory() {
		return categoryRepository.findRootCategory((Catalog) getCurrentContext().getCatalog());
	}

	@Override
	@Transactional
	public Catalog createCatalog(Catalog catalog) {
		return catalogRepository.saveAndFlush(catalog);

	}

	@Override
	@Transactional
	public Product createproduct(Product product) {
		return productRepository.saveAndFlush(product);
	}

	@Override
	public Bundle createproduct(Bundle b) {
		return bundleRepository.saveAndFlush(b);
	}

	@Override
	@Transactional
	public void deleteCatalog(Catalog catalog) {
			catalogRepository.delete(catalog);
	}

	@Override
	public void deleteProduct(AbstractProduct product) {
		abstractProductRepository.delete(product);
		
	}

	@Override
	public void deleteCatalogEntry(Ctentry ctentry) {
		catalogEntryRepository.delete(ctentry);
		
	}

	
	
	
	

	@Override
	public Category create(Category entity) {
		return createCategory(entity, null);
	}


	@Override
	public void delete(Category entity) {
		categoryRepository.delete(entity);		
	}

	private Specification currentCatalogCategory(){
		return isCategoryInCatalog((Catalog) getCurrentContext().getCatalog());
	}

	@Override
	public Page<Category> findAll(Pageable page) {
		return categoryRepository.findAll(currentCatalogCategory(), page);
	}


	@Override
	public List<Category> findAll() {
		return categoryRepository.findAll(currentCatalogCategory());
	}

	@Override
	public List<Category> findAll(Specification<Category> spec, Pageable page) {
		if (spec == null)
			return findAll(page).getContent();
		
		Page<Category> res = categoryRepository.findAll( where(spec).and(currentCatalogCategory()), page);
		return res.getContent();
	}
	
	@Override
	public List<Category> findAll(Specification<Category> spec, Sort sort) {
		if (spec == null && sort == null) return findAll();
		if (sort == null)
			return categoryRepository.findAll( where(spec).and(currentCatalogCategory()));
		return categoryRepository.findAll( where(spec).and(currentCatalogCategory()), sort);
	}
	
	
	@Override
	public Category update(Category entity) {
		return categoryRepository.save(entity);
	}


	@Override
	public List<Category> findByexample(Category example) {
		return categoryRepository.findAll(where(categoryRepository
				.makeSpecification(example)));
	}


	@Override
	public Category findOne(Long id) {		
		return categoryRepository.findOne(id);
	}



	@Override
	public Category findOne(Specification<Category> spec, Sort sort, int index) {
		final PageRequest singleResultPage = new PageRequest(index, 1, sort);
		List<Category> l = findAll(spec, singleResultPage);
		if (!l.isEmpty())
			return l.get(0);
		else return null;
	}






}
