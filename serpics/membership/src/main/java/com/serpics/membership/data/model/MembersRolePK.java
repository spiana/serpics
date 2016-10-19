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
package com.serpics.membership.data.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the members_role database table.
 * 
 */
@Embeddable
public class MembersRolePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="role_id", nullable=false)
	private Long roleId;

	@Column(name="member_id",nullable=false)
	private Long memberId;

	@Column(name="store_id", nullable=false)
	private Long storeId;

    public MembersRolePK() {
    }
	public MembersRolePK(Long roleId, Long memberId, Long storeId) {
		super();
		this.roleId = roleId;
		this.memberId = memberId;
		this.storeId = storeId;
	}
	public Long getRoleId() {
		return this.roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	public Long getMemberId() {
		return this.memberId;
	}
	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}
	public Long getStoreId() {
		return this.storeId;
	}
	public void setStoresStoreId(Long storeId) {
		this.storeId = storeId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof MembersRolePK)) {
			return false;
		}
		MembersRolePK castOther = (MembersRolePK)other;
		return 
			this.roleId.equals(castOther.roleId)
			&& this.memberId.equals(castOther.memberId)
			&& this.storeId.equals(castOther.storeId);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.roleId.hashCode();
		hash = hash * prime + this.memberId.hashCode();
		hash = hash * prime + this.storeId.hashCode();
		
		return hash;
    }
}