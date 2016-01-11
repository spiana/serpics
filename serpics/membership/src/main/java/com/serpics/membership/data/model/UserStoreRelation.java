package com.serpics.membership.data.model;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlRootElement;

import com.serpics.base.data.model.Store;

@XmlRootElement
 //@Entity(name = "userStoreRelation")
 //@Table(name = "user2StoreRel")
public class UserStoreRelation extends MemberRelation {
    private static final long serialVersionUID = 1L;


    public UserStoreRelation() {
        super();
    }

    public UserStoreRelation(final Store store , final User user) {
        super();
        final MemberRelationPK k = new MemberRelationPK();
        k.setParentMemberId(store.getId());
        k.setChildMemberId(user.getId());
        this.setId(k);
    }


    //bi-directional many-to-one association to Member
    @ManyToOne( fetch=FetchType.LAZY)
    @JoinColumn(name="store_id", nullable=false, insertable=false, updatable=false )
    private Store store;

    //bi-directional many-to-one association to Member
    @ManyToOne(fetch=FetchType.LAZY )
    @JoinColumn(name="user_id", nullable=false, insertable=false, updatable=false)
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
        this.user = user;
    }



}
