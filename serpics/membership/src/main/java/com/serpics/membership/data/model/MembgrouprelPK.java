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
 * The primary key class for the membgrouprel database table.
 * 
 */
@Embeddable
public class MembgrouprelPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="membergroups_id",  nullable=false)
	private Long membergroupsId;

	@Column(name="member_id", nullable=false)
	private Long memberId;

    public MembgrouprelPK() {
    }
	public Long getMembergroupsId() {
		return this.membergroupsId;
	}
	public void setMembergroupsId(Long membergroupsId) {
		this.membergroupsId = membergroupsId;
	}
	public Long getMemberId() {
		return this.memberId;
	}
	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof MembgrouprelPK)) {
			return false;
		}
		MembgrouprelPK castOther = (MembgrouprelPK)other;
		return 
			this.membergroupsId.equals(castOther.membergroupsId)
			&& this.memberId.equals(castOther.memberId);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.membergroupsId.hashCode();
		hash = hash * prime + this.memberId.hashCode();
		
		return hash;
    }
}