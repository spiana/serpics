package com.serpics.catalog.services;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.serpics.catalog.persistence.Catalog;
import com.serpics.catalog.persistence.Category;
import com.serpics.catalog.persistence.CategoryRelation;
import com.serpics.catalog.persistence.CtentryRelationPK;
import com.serpics.catalog.repositories.CatalogRepository;
import com.serpics.catalog.repositories.CategoryRelationRepository;
import com.serpics.catalog.repositories.CategoryRepository;
import com.serpics.core.service.AbstractService;

@Service("catalogService")
@Scope("store")
public class CatalogServiceImpl extends AbstractService implements CatalogService {

	@Resource
	CatalogRepository catalogRepository;

	@Resource
	CategoryRepository categoryRepository;

	@Resource
	CategoryRelationRepository categoryRelationRepository;

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public Category createCategory(Category category, Category parent) {
		Catalog catalog = catalogRepository.findOne(getCurrentContext().getCatalogId());
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
		return categoryRepository.findRootCategory(getCurrentContext().getCatalogId());
	}

	@Override
	@Transactional
	public Catalog createCatalog(Catalog catalog) {
		return catalogRepository.saveAndFlush(catalog);

	}
}