package com.serpics.catalog.services;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.serpics.catalog.persistence.CategoryRelation;
import com.serpics.catalog.persistence.CtentryRelationPK;
import com.serpics.catalog.repositories.CategoryRelationRepository;
import com.serpics.core.data.Repository;
import com.serpics.core.service.AbstractEntityService;

@Service("categoryRelationService")
@Scope("store")
public class CategoryRelationServiceImpl extends AbstractEntityService<CategoryRelation, CtentryRelationPK> implements
CategoryRelationService {

    @Autowired
    CategoryRelationRepository categoryRelationRepository;

    @Override
    public CategoryRelation create(final CategoryRelation entity) {
        if (entity.getId() == null){
            final CtentryRelationPK pk = new CtentryRelationPK(entity.getParentCategory().getCtentryId(), entity
                    .getChildCategory().getCtentryId());
            entity.setId(pk);
        }
        return super.create(entity);
    }

    @Override
    public Repository<CategoryRelation, CtentryRelationPK> getEntityRepository() {
        return categoryRelationRepository;
    }

    @Override
    public Specification<CategoryRelation> getBaseSpec() {
        return new Specification<CategoryRelation>() {
            @Override
            public Predicate toPredicate(final Root<CategoryRelation> arg0, final CriteriaQuery<?> arg1,
                    final CriteriaBuilder arg2) {
                return arg2.isNotNull(arg0.get("uuid"));
            }
        };
    }

}
