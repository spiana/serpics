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