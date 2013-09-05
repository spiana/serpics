package com.serpics.catalog.services;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
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

@Service("catalogService")
@Scope("store")
public class CatalogServiceImpl extends AbstractService implements CatalogService {

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
	public List<Category> getChildCategories(Category parent) {
		List<CategoryRelation> findByCategory_parent = categoryRelationRepository.findByParentCategory(parent);
		for (CategoryRelation rel : findByCategory_parent){
			System.out.println(rel.getChildCategory().toString());
		}
		return null;
	}
}
