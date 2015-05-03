package com.serpics.catalog.repositories.interceptors;

import org.springframework.util.Assert;

import com.serpics.catalog.data.model.CategoryRelation;
import com.serpics.catalog.data.model.CtentryRelationPK;
import com.serpics.core.data.CreateInterceptor;

public class CategoryRelationCreateInterceptor implements CreateInterceptor<CategoryRelation> {

	@Override
	public void beforeCreate(CategoryRelation entity) {
	    if (entity.getId() == null){
	    	Assert.notNull(entity.getParentCategory());
	    	Assert.notNull(entity.getChildCategory());
	    	
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
