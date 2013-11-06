package com.serpics.admin.ui;

import javax.annotation.Resource;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.serpics.catalog.persistence.Catalog;
import com.serpics.catalog.persistence.Category;
import com.serpics.catalog.repositories.CategoryRepository;
import com.serpics.catalog.repositories.CategorySpecification;
import com.serpics.core.data.Repository;
import com.serpics.core.service.AbstractEntityService;

@Service("categoryEntityService")
public class CategoryEntityServiceImpl extends AbstractEntityService<Category, Long> {

	@Resource
	CategoryRepository categoryRepository;


	@Override
	public Category create(Category entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Repository<Category, Long> getEntityRepository() {
		return categoryRepository;
	}

	@Override
	public Specification<Category> getBaseSpec() {
		return CategorySpecification.isCategoryInCatalog(getCurrentCatalog());
	}


	private Catalog getCurrentCatalog(){
		return (Catalog) getCurrentContext().getCatalog();
	}




}
