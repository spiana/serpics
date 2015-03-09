package com.serpics.catalog.repositories.interceptors;

import com.serpics.catalog.persistence.CategoryProductRelation;
import com.serpics.catalog.persistence.CtentryRelationPK;
import com.serpics.core.data.CreateInterceptor;

public class Category2productCreateInterceptor  implements CreateInterceptor<CategoryProductRelation>{

	@Override
	public void beforeCreate(CategoryProductRelation entity) {
		if (entity.getId() == null) {
            final CtentryRelationPK pk = new CtentryRelationPK();
            pk.setCtentryIdParent(entity.getParentCategory().getCtentryId());
            pk.setCtentryIdChild(entity.getChildProduct().getCtentryId());
            entity.setId(pk);
        }
		
	}

	@Override
	public void afterCreate(CategoryProductRelation entity) {
		// TODO Auto-generated method stub
		
	}

}
