package com.serpics.catalog.data.interceptors;

import com.serpics.catalog.data.model.CategoryProductRelation;
import com.serpics.catalog.data.model.CtentryRelationPK;
import com.serpics.core.data.SaveInterceptor;

public class Category2productSaveInterceptor  implements SaveInterceptor<CategoryProductRelation>{

	@Override
	public void beforeSave(CategoryProductRelation entity) {
		if (entity.getId() == null) {
            final CtentryRelationPK pk = new CtentryRelationPK();
            pk.setCtentryIdParent(entity.getParentCategory().getId());
            pk.setCtentryIdChild(entity.getChildProduct().getId());
            entity.setId(pk);
        }
		
	}

	@Override
	public void afterSave(CategoryProductRelation entity) {
		// TODO Auto-generated method stub
		
	}

}
