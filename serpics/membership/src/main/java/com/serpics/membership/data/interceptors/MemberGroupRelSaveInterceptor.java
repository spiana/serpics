/*******************************************************************************
 * Copyright 2014-2016  StepFour Srl
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *  
 *      http://www.apache.org/licenses/LICENSE-2.0
 *  
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *  
 *******************************************************************************/
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
