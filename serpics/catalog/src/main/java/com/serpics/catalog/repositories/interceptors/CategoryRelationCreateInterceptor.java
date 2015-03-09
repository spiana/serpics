package com.serpics.catalog.repositories.interceptors;

import com.serpics.catalog.persistence.CategoryRelation;
import com.serpics.catalog.persistence.CtentryRelationPK;
import com.serpics.core.data.CreateInterceptor;

public class CategoryRelationCreateInterceptor implements CreateInterceptor<CategoryRelation> {

	@Override
	public void beforeCreate(CategoryRelation entity) {
	    if (entity.getId() == null){
            final CtentryRelationPK pk = new CtentryRelationPK(entity.getParentCategory().getCtentryId(), entity
                    .getChildCategory().getCtentryId());
            entity.setId(pk);
        }
	}

	@Override
	public void afterCreate(CategoryRelation entity) {
		// TODO Auto-generated method stub
		
	}

}
