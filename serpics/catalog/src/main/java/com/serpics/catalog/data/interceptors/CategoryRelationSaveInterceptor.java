package com.serpics.catalog.data.interceptors;

import org.springframework.util.Assert;

import com.serpics.catalog.data.model.CategoryRelation;
import com.serpics.catalog.data.model.CtentryRelationPK;
import com.serpics.core.data.SaveInterceptor;

public class CategoryRelationSaveInterceptor implements SaveInterceptor<CategoryRelation> {

	@Override
	public void beforeSave(CategoryRelation entity) {
	    if (entity.getId() == null){
	    	Assert.notNull(entity.getParentCategory());
	    	Assert.notNull(entity.getChildCategory());
	    	
            final CtentryRelationPK pk = new CtentryRelationPK(entity.getParentCategory().getId(), entity
                    .getChildCategory().getId());
            entity.setId(pk);
        }
	}

	@Override
	public void afterSave(CategoryRelation entity) {
		// TODO Auto-generated method stub
		
	}

}
