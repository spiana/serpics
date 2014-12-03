package com.serpics.catalog.services;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.serpics.catalog.persistence.Catalog;
import com.serpics.catalog.persistence.Category;
import com.serpics.catalog.persistence.CategoryRelation;
import com.serpics.catalog.persistence.CtentryRelationPK;
import com.serpics.catalog.repositories.CategoryRelationRepository;
import com.serpics.catalog.repositories.CategoryRepository;
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

    @Override
    public Repository<Category, Long> getEntityRepository() {
        return categoryRepository;
    }

    @Override
    public Specification<Category> getBaseSpec() {
        return new Specification<Category>() {
            @Override
            public Predicate toPredicate(final Root<Category> root, final CriteriaQuery<?> cq, final CriteriaBuilder cb) {
                return cb.equal(root.get("catalog"), getCurrentContext().getCatalog());
            }
        };
    }

    @Override
    @Transactional
    public Category create(final Category entity) {
        if (entity.getCatalog() == null) {
            entity.setCatalog((Catalog) getCurrentContext().getCatalog());
        }

        return super.create(entity);
    }

    @Override
    @Transactional
    public Category create(Category category, final Category parent) {
        category = this.create(category);
        if (parent != null) {
            final CtentryRelationPK ctpk = new CtentryRelationPK(parent.getCtentryId(), category.getCtentryId());
            final CategoryRelation cgrel = new CategoryRelation();
            cgrel.setId(ctpk);
            categoryRelationRepository.save(cgrel);
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

}
