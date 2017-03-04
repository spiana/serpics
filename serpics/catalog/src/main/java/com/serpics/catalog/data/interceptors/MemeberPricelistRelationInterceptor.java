package com.serpics.catalog.data.interceptors;

import com.serpics.catalog.data.model.MemberPricelistRelation;
import com.serpics.catalog.data.model.MemberPricelistRelationPK;
import com.serpics.core.data.SaveInterceptor;
import com.serpics.stereotype.ModelInterceptor;

@ModelInterceptor(MemberPricelistRelation.class)
public class MemeberPricelistRelationInterceptor implements SaveInterceptor<MemberPricelistRelation>{

	@Override
	public void beforeSave(MemberPricelistRelation entity) {
		if (entity.getId() == null){
			MemberPricelistRelationPK pk = 
					new MemberPricelistRelationPK(entity.getMember().getId(),
							entity.getPriceList().getId());
			entity.setId(pk);
		}
		
	}

	@Override
	public void afterSave(MemberPricelistRelation entity) {
	
		
	}

}
