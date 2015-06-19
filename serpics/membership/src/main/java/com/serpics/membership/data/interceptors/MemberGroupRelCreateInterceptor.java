package com.serpics.membership.data.interceptors;

import org.springframework.util.Assert;

import com.serpics.core.data.CreateInterceptor;
import com.serpics.membership.data.model.Membergrouprel;
import com.serpics.membership.data.model.MembgrouprelPK;

public class MemberGroupRelCreateInterceptor implements CreateInterceptor<Membergrouprel> {

	@Override
	public void beforeCreate(Membergrouprel entity) {
		if (entity.getId() == null){
			Assert.notNull(entity.getMember());
			Assert.notNull(entity.getMembergroup());
			MembgrouprelPK pk = new MembgrouprelPK();
			pk.setMembergroupsId(entity.getMembergroup().getMembergroupsId());
			pk.setMemberId(entity.getMember().getMemberId());
			entity.setId(pk);
		}
		
	}

	@Override
	public void afterCreate(Membergrouprel entity) {
		// TODO Auto-generated method stub
		
	}

}
