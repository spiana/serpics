package com.serpics.catalog.data.interceptors;

import com.serpics.catalog.data.model.CategoryProductRelation;
import com.serpics.catalog.data.model.CtentryRelationPK;
import com.serpics.core.data.CreateInterceptor;

public class Category2productCreateInterceptor  implements CreateInterceptor<CategoryProductRelation>{

	@Override
	public void beforeCreate(CategoryProductRelation entity) {
		if (entity.getId() == null) {
            final CtentryRelationPK pk = new CtentryRelationPK();
            pk.setCtentryIdParent(entity.getParentCategory().getId());
            pk.setCtentryIdChild(entity.getChildProduct().getId());
            entity.setId(pk);
        }
		
	}

	@Override
	public void afterCreate(CategoryProductRelation entity) {
		// TODO Auto-generated method stub
		
	}

}
