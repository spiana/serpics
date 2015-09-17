package com.serpics.membership.data.interceptors;

import org.springframework.util.Assert;

import com.serpics.core.data.SaveInterceptor;
import com.serpics.membership.data.model.Membergrouprel;
import com.serpics.membership.data.model.MembgrouprelPK;

public class MemberGroupRelSaveInterceptor implements SaveInterceptor<Membergrouprel> {

	@Override
	public void beforeSave(Membergrouprel entity) {
		if (entity.getId() == null){
			Assert.notNull(entity.getMember());
			Assert.notNull(entity.getMembergroup());
			MembgrouprelPK pk = new MembgrouprelPK();
			pk.setMembergroupsId(entity.getMembergroup().getId());
			pk.setMemberId(entity.getMember().getId());
			entity.setId(pk);
		}
		
	}

	@Override
	public void afterSave(Membergrouprel entity) {
		// TODO Auto-generated method stub
		
	}

}
