package com.serpics.membership.persistence;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the members_role database table.
 * 
 */
@Entity
@Table(name="members_role" )
public class MembersRole  extends com.serpics.core.persistence.jpa.Entity implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private MembersRolePK id;

	//bi-directional many-to-one association to Member
    @ManyToOne(optional =false)
	@JoinColumn(name="member_id", nullable=false, insertable=false, updatable=false)
	private Member member;

	//bi-directional many-to-one association to Role
     @ManyToOne (optional=false)
	@JoinColumn(name="role_id", nullable=false, insertable=false, updatable=false)
	private Role role;

	//bi-directional many-to-one association to Store
    @ManyToOne (optional= false)
	@JoinColumn(name="store_id", nullable=false, insertable=false, updatable=false)
	private Store store;

    public MembersRole() {
    }

	public MembersRole(Member member, Role role, Store store) {
		super();
		this.member = member;
		this.role = role;
		this.store = store;
		this.id= new MembersRolePK(role.getRoleId() ,member.getMemberId() , store.getStoreId());
	
	}

	public MembersRolePK getId() {
		return this.id;
	}

	public void setId(MembersRolePK id) {
		this.id = id;
	}

	public Member getMember() {
		return this.member;
	}

	public void setMember(Member member) {
		this.member = member;
	}
	
	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
	public Store getStore() {
		return this.store;
	}

	public void setStore(Store store) {
		this.store = store;
	}
	
}