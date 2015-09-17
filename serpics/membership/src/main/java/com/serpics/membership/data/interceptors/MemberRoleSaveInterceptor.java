package com.serpics.membership.data.interceptors;


import org.springframework.beans.factory.annotation.Autowired;

import com.serpics.commerce.core.CommerceEngine;
import com.serpics.core.data.SaveInterceptor;
import com.serpics.membership.data.model.MembersRole;
import com.serpics.membership.data.model.MembersRolePK;

public class MemberRoleSaveInterceptor implements SaveInterceptor<MembersRole>{

	@Autowired
	CommerceEngine engine;
	
	@Override
	public void beforeSave(MembersRole entity) {
		if (entity.getId() == null && entity.getRole() != null && entity.getMember() != null) {
            final MembersRolePK pk = new MembersRolePK(entity.getRole().getId(),
                    entity.getMember().getId(), engine.getCurrentContext().getStoreId());
            entity.setId(pk);
		}
	}

	@Override
	public void afterSave(MembersRole entity) {
		// TODO Auto-generated method stub
		
	}

	
}
