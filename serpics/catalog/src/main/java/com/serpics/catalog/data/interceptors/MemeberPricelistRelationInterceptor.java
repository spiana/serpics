package com.serpics.catalog.data.interceptors;

import com.serpics.catalog.data.model.MemberPricelistRelation;
import com.serpics.catalog.data.model.MemberPricelistRelationPK;
import com.serpics.catalog.data.model.UserPricelistRelation;
import com.serpics.core.data.SaveInterceptor;
import com.serpics.stereotype.ModelInterceptor;

@ModelInterceptor(MemberPricelistRelation.class)
public class MemeberPricelistRelationInterceptor implements SaveInterceptor<UserPricelistRelation>{

	@Override
	public void beforeSave(UserPricelistRelation entity) {
		if (entity.getId() == null){
			MemberPricelistRelationPK pk = 
					new MemberPricelistRelationPK(entity.getUser().getId(),
							entity.getPriceList().getId());
			entity.setId(pk);
		}
		
	}

	@Override
	public void afterSave(UserPricelistRelation entity) {
	
		
	}

}
