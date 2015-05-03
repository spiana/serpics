package com.serpics.membership.data.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity(name = "User2UserRelation")
@Table(name = "user2userRel")
public class User2UserRelation extends MemberRelation {
    private static final long serialVersionUID = 1L;


    @Column(name="relation_type", nullable=false ,insertable=false , updatable=false)
    private int relationType;

    //bi-directional many-to-one association to Member
    @ManyToOne( fetch=FetchType.LAZY)
    @JoinColumn(name="parent_member_id", nullable=false, insertable=false, updatable=false )
    private User user;

    //bi-directional many-to-one association to Member
    @ManyToOne(fetch=FetchType.LAZY )
    @JoinColumn(name="child_member_id", nullable=false, insertable=false, updatable=false)
    private User customer;

    public User2UserRelation() {
        super();
    }

    public User2UserRelation(final User parentUser , final User childUser , final int relationType) {
        super();
        final MemberRelationPK k = new MemberRelationPK();
        k.setParentMemberId(parentUser.getUserId());
        k.setChildMemberId(childUser.getUserId());
        this.setId(k);
        this.setRelationType(relationType);
    }



    public User getCustomer() {
        return customer;
    }

    public User getUser() {
        return user;
    }

    public int getRelationType() {
        return relationType;
    }

    public void setRelationType(final int relationType) {
        this.relationType = relationType;
    }


}
