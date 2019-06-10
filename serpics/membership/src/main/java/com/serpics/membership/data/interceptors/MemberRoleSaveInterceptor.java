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


import org.springframework.beans.factory.annotation.Autowired;

import com.serpics.base.commerce.CommerceEngine;
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
