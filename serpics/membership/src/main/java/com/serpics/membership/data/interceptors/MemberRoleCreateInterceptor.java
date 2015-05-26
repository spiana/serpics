package com.serpics.membership.data.interceptors;


import org.springframework.beans.factory.annotation.Autowired;

import com.serpics.commerce.core.CommerceEngine;
import com.serpics.core.data.CreateInterceptor;
import com.serpics.membership.data.model.MembersRole;
import com.serpics.membership.data.model.MembersRolePK;

public class MemberRoleCreateInterceptor implements CreateInterceptor<MembersRole>{

	@Autowired
	CommerceEngine engine;
	
	@Override
	public void beforeCreate(MembersRole entity) {
		if (entity.getId() == null && entity.getRole() != null && entity.getMember() != null) {
            final MembersRolePK pk = new MembersRolePK(entity.getRole().getRoleId(),
                    entity.getMember().getMemberId(), engine.getCurrentContext().getStoreId());
            entity.setId(pk);
		}
	}

	@Override
	public void afterCreate(MembersRole entity) {
		// TODO Auto-generated method stub
		
	}

	
}
