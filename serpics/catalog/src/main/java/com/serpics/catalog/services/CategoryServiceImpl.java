package com.serpics.catalog.services;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.serpics.catalog.data.model.Catalog;
import com.serpics.catalog.data.model.Category;
import com.serpics.catalog.data.model.CategoryProductRelation;
import com.serpics.catalog.data.model.CategoryRelation;
import com.serpics.catalog.data.model.Product;
import com.serpics.catalog.data.repositories.Category2ProductRepository;
import com.serpics.catalog.data.repositories.CategoryRelationRepository;
import com.serpics.catalog.data.repositories.CategoryRepository;
import com.serpics.commerce.service.AbstractCommerceEntityService;
import com.serpics.core.data.Repository;

@Service("categoryService")
@Scope("store")
@Transactional(readOnly = true)
public class CategoryServiceImpl extends AbstractCommerceEntityService<Category, Long> implements CategoryService, Serializable {

    private static final long serialVersionUID = -7873992332620766909L;

    private final Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    CategoryRelationRepository categoryRelationRepository;
    
    @Autowired
    Category2ProductRepository category2ProductRepository;

    @Override
    public Repository<Category, Long> getEntityRepository() {
        return categoryRepository;
    }

    @Override
    @Transactional
    public Category create(final Category entity) {
            return super.create(entity);
    }

    @Transactional
    public void addRelationCategory(final Category childCategory, final Category parentCategory) {
    	/*final CtentryRelationPK ctpk = new CtentryRelationPK(parentCategory.getId(), childCategory.getId());
        final CategoryRelation cgrel = new CategoryRelation();
        cgrel.setId(ctpk);
        categoryRelationRepository.save(cgrel);*/
    	final CategoryRelation cr = new CategoryRelation();
    	cr.setChildCategory(childCategory);
    	cr.setParentCategory(parentCategory);
    	categoryRelationRepository.saveAndFlush(cr);
    }
    
    @Override
    @Transactional
    public Category create(Category category, final Category parentCategory) {
        category = this.create(category);
        if (parentCategory != null) {
        	final CategoryRelation cr = new CategoryRelation();
        	cr.setChildCategory(category);
        	cr.setParentCategory(parentCategory);
        	categoryRelationRepository.saveAndFlush(cr);
        	/*
            final CtentryRelationPK ctpk = new CtentryRelationPK(parent.getId(), category.getId());
            final CategoryRelation cgrel = new CategoryRelation();
            cgrel.setId(ctpk);
            categoryRelationRepository.save(cgrel);*/
        }

        return category;
    }

    @Override
    public List<Category> getChildCategories(final Category parent) {
        final List<Category> res = new ArrayList<>(5);

        try {
            final List<CategoryRelation> findByCategory_parent = categoryRelationRepository
                    .findByParentCategory(parent);
            for (final CategoryRelation rel : findByCategory_parent) {
                res.add(rel.getChildCategory());
            }
        } catch (final Exception e) {
            logger.error("", e);
        }
        return res;
    }
    @Override
    public List<Category> findRootCategory() {
        return categoryRepository.findRootCategory((Catalog) getCurrentContext().getCatalog());
    }

    
    @Transactional
    public List<Category> getCategoriesByProduct(final Product product){
    	final List<Category> res = new ArrayList<>();
    	
    	try {
            final List<CategoryProductRelation> findCategoryParent = category2ProductRepository.findByChildProduct(product);
            for (final CategoryProductRelation rel : findCategoryParent) {
                res.add(rel.getParentCategory());
            }
        } catch (final Exception e) {
            logger.error("", e);
        }
        return res;
    }

	@Override
	public int getCountChildCategory(Category category) {
		return categoryRelationRepository.getCountCategoryChild(category);
	}
	
	@Override
	public Set<Category> getParentCategories(Category category) {
		return categoryRelationRepository.getParentCategories(category);
	}

	@Override
	public int getCountChildProduct(Category category) {
		return category2ProductRepository.getCountChildProduct(category);
	}

	@Override
	public Category findByCode(final String code) {
		return categoryRepository.findOne(new Specification<Category>() {
            @Override
            public Predicate toPredicate(final Root<Category> root, final CriteriaQuery<?> query,
                    final CriteriaBuilder cb) {
            	Expression<String> e = root.get("code");
            		
                return cb.equal(e, code);
            }
		});
	}
    
}
