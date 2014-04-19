package com.serpics.catalog.services;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.serpics.catalog.persistence.CategoryProductRelation;
import com.serpics.catalog.persistence.CtentryRelationPK;
import com.serpics.catalog.repositories.Category2ProductRepository;
import com.serpics.core.data.Repository;
import com.serpics.core.service.AbstractEntityService;

@Service("category2ProductServiceImpl")
@Scope("store")
public class Category2ProductServiceImpl extends AbstractEntityService<CategoryProductRelation, Long> implements
Category2ProductService {

    @Autowired
    Category2ProductRepository category2ProductRepository;

    @Override
    public CategoryProductRelation create(final CategoryProductRelation entity) {
        if (entity.getId() == null) {
            final CtentryRelationPK pk = new CtentryRelationPK();
            pk.setCtentryIdParent(entity.getParentCategory().getCtentryId());
            pk.setCtentryIdChild(entity.getChildProduct().getCtentryId());
            entity.setId(pk);

        }
        return super.create(entity);
    }

    @Override
    public Repository<CategoryProductRelation, Long> getEntityRepository() {
        return category2ProductRepository;
    }

    @Override
    public Specification<CategoryProductRelation> getBaseSpec() {
        return new Specification<CategoryProductRelation>() {
            @Override
            public Predicate toPredicate(final Root<CategoryProductRelation> arg0, final CriteriaQuery<?> arg1,
                    final CriteriaBuilder arg2) {
                return arg2.isNotNull(arg0.get("uuid"));
            }
        };
    }

}
