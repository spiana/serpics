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
package com.serpics.membership.data.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.serpics.base.data.model.Store;
import com.serpics.core.data.Repository;
import com.serpics.membership.data.model.Member;
import com.serpics.membership.data.model.MembersRole;
import com.serpics.membership.data.model.MembersRolePK;

public interface MembersRoleRepository extends Repository<MembersRole, MembersRolePK> {

    @Query("select mr from MembersRole mr where mr.member= :user and mr.store=:store")
    public List<MembersRole> findUserRoles(@Param("user") Member member, @Param("store") Store store);
}
