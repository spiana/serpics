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
 * The primary key class for the member_relation database table.
 * 
 */
@Embeddable
public class MemberRelationPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="parent_member_id", nullable=false)
	private Long parentMemberId;

	@Column(name="child_member_id", nullable=false)
	private Long childMemberId;

    public MemberRelationPK() {
    }
	public MemberRelationPK(Long parentMemberId, Long childMemberId) {
		super();
		this.parentMemberId = parentMemberId;
		this.childMemberId = childMemberId;
	}
	public Long getParentMemberId() {
		return this.parentMemberId;
	}
	public void setParentMemberId(Long parentMemberId) {
		this.parentMemberId = parentMemberId;
	}
	public Long getChildMemberId() {
		return this.childMemberId;
	}
	public void setChildMemberId(Long childMemberId) {
		this.childMemberId = childMemberId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof MemberRelationPK)) {
			return false;
		}
		MemberRelationPK castOther = (MemberRelationPK)other;
		return 
			this.parentMemberId.equals(castOther.parentMemberId)
			&& this.childMemberId.equals(castOther.childMemberId);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.parentMemberId.hashCode();
		hash = hash * prime + this.childMemberId.hashCode();
		
		return hash;
    }
}