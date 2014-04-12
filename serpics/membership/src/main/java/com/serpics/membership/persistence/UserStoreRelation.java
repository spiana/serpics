package com.serpics.membership.persistence;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity(name = "UserStoreRelation")
@Table(name = "User2StoreRel")
public class UserStoreRelation extends MemberRelation {
    private static final long serialVersionUID = 1L;


    public UserStoreRelation() {
        super();
    }

    public UserStoreRelation(final Store store , final User user) {
        super();
        final MemberRelationPK k = new MemberRelationPK();
        k.setParentMemberId(store.getStoreId());
        k.setChildMemberId(user.getUserId());
        this.setId(k);
    }


    //bi-directional many-to-one association to Member
    @ManyToOne( fetch=FetchType.LAZY)
    @JoinColumn(name="parent_member_id", nullable=false, insertable=false, updatable=false )
    private Store store;

    //bi-directional many-to-one association to Member
    @ManyToOne(fetch=FetchType.LAZY )
    @JoinColumn(name="child_member_id", nullable=false, insertable=false, updatable=false)
    private User user;


    public Store getStore() {
        return store;
    }

    public void setStore(final Store store) {
        this.store = store;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        user = user;
    }



}
