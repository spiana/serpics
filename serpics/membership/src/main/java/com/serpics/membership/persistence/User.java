package com.serpics.membership.persistence;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.serpics.core.datatype.MemberType;
import com.serpics.core.datatype.UserType;
import com.serpics.core.security.UserDetail;

/**
 * The persistent class for the users database table.
 * 
 */

@XmlRootElement(name="user")
@Entity
@Table(name = "users")
@DiscriminatorValue("U")
@PrimaryKeyJoinColumn(name="user_id")
public class User extends Member implements Serializable, UserDetail {
    private static final long serialVersionUID = 1L;


    @Column(length = 25)
    private String phone;


    @Column(length = 100)
    private String email;

    private BigInteger field4;

    @Column(length = 200)
    private String firstname;

    @Column(length = 200)
    private String lastname;

    @Column(name = "user_type", nullable = false, length = 1)
    private String userType;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_visit")
    private Date lastVisit;


    @OneToOne(mappedBy = "user", fetch = FetchType.EAGER, optional = true, cascade = CascadeType.ALL, orphanRemoval = true)
    private UsersReg userReg;

    @Column(name = "store_id")
    private Long storeId;

    // bi-directional many-to-one association to MemberRelation
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL)
    protected Set<UserStoreRelation> storeRelation = new HashSet<UserStoreRelation>(0);

    public User() {
        this.userType = UserType.ANONYMOUS;
        this.memberType = MemberType.USER;
    }

    public User(final String userType, final String firstname, final String lastname, final String phone, final String email) {
        super();

        this.userType = userType == null ? UserType.ANONYMOUS : userType;
        this.firstname = firstname;
        this.lastname = lastname;
        this.phone = phone;
        this.email = email;
        this.memberType = MemberType.USER;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(final String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public BigInteger getField4() {
        return this.field4;
    }

    public void setField4(final BigInteger field4) {
        this.field4 = field4;
    }

    public String getFirstname() {
        return this.firstname;
    }

    public void setFirstname(final String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return this.lastname;
    }

    public void setLastname(final String lastname) {
        this.lastname = lastname;
    }

    public void setLastVisit(final Date lastVisit) {
        this.lastVisit = lastVisit;
    }

    public Date getLastVisit() {
        return lastVisit;
    }

    public String getUserType() {
        return this.userType;
    }

    public void setUserType(final String userType) {
        this.userType = userType;
    }

    public boolean hasRole(final String role, final Long storeId) {

        for (final MembersRole mrole : membersRoles) {
            if (mrole.getStore().getStoreId().equals(storeId) && mrole.getRole().getName().equals(role))
                return true;
        }

        return false;
    }


    public UsersReg getUserReg() {
        return userReg;
    }

    public void setUserReg(final UsersReg userReg) {
        this.userReg = userReg;
    }

    @Override
    public String getName() {
        if (userReg != null)
            return userReg.getLogonid();
        else
            return "guest";
    }

    @Override
    public Long getUserId() {

        return getMemberId();
    }

    public void setUserId(final Long userId) {

        setMemberId(userId);
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(final Long storeId) {
        this.storeId = storeId;
    }

    @XmlTransient
    public Set<UserStoreRelation> getStoreRelation() {
        return storeRelation;
    }

    public void setStoreRelation(final Set<UserStoreRelation> storeRelation) {
        this.storeRelation = storeRelation;
    }

}