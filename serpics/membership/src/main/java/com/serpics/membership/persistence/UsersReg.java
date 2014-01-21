package com.serpics.membership.persistence;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.hibernate.validator.constraints.NotEmpty;

import com.serpics.membership.UserRegStatus;

/**
 * The persistent class for the users_reg database table.
 * 
 */
@XmlRootElement(name="usersreg")
@Entity
@Table(name = "users_reg")
public class UsersReg extends com.serpics.core.persistence.jpa.Entity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "user_id", unique = true, nullable = false)
    private Long userId;

    @Column(name = "alternate_email", length = 100)
    private String alternateEmail;

    @Column(length = 254)
    private String changeanswer;

    @Column(length = 254)
    private String changequestion;

    private Timestamp created;

    @Column(length = 500)
    private String dn;

    @Column(length = 254)
    private String field1;

    @Column(length = 254)
    private String field2;

    @Column(name = "last_login")
    private Timestamp lastLogin;

    @Column(name = "locale_id")
    private BigInteger localeId;

    @NotNull
    @NotEmpty
    @Size(min = 5, max = 100)
    @Column(nullable = false, length = 40 , unique=true)
    private String logonid;

    @NotNull()
    @NotEmpty
    @Column(nullable = false, length = 254)
    private String password;

    @Column(name = "password_change")
    private Timestamp passwordChange;

    @NotNull
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRegStatus status;


    @OneToOne( fetch = FetchType.EAGER, optional = false )
    @JoinColumn(name = "member_id", nullable = false , insertable = true, updatable = false )
    private User user;

    public UsersReg() {
        this.status = UserRegStatus.ACTIVE;
    }

    public Long getUserId() {
        return this.userId;
    }

    public void setUserId(final Long userId) {
        this.userId = userId;
    }

    public String getAlternateEmail() {
        return this.alternateEmail;
    }

    public void setAlternateEmail(final String alternateEmail) {
        this.alternateEmail = alternateEmail;
    }

    public String getChangeanswer() {
        return this.changeanswer;
    }

    public void setChangeanswer(final String changeanswer) {
        this.changeanswer = changeanswer;
    }

    public String getChangequestion() {
        return this.changequestion;
    }

    public void setChangequestion(final String changequestion) {
        this.changequestion = changequestion;
    }

    public Timestamp getCreated() {
        return this.created;
    }

    public void setCreated(final Timestamp created) {
        this.created = created;
    }

    public String getDn() {
        return this.dn;
    }

    public void setDn(final String dn) {
        this.dn = dn;
    }

    public String getField1() {
        return this.field1;
    }

    public void setField1(final String field1) {
        this.field1 = field1;
    }

    public String getField2() {
        return this.field2;
    }

    public void setField2(final String field2) {
        this.field2 = field2;
    }

    public Timestamp getLastLogin() {
        return this.lastLogin;
    }

    public void setLastLogin(final Timestamp lastLogin) {
        this.lastLogin = lastLogin;
    }

    public BigInteger getLocaleId() {
        return this.localeId;
    }

    public void setLocaleId(final BigInteger localeId) {
        this.localeId = localeId;
    }

    public String getLogonid() {
        return this.logonid;
    }

    public void setLogonid(final String logonid) {
        this.logonid = logonid;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public Timestamp getPasswordChange() {
        return this.passwordChange;
    }

    public void setPasswordChange(final Timestamp passwordChange) {
        this.passwordChange = passwordChange;
    }

    public UserRegStatus getStatus() {
        return this.status;
    }

    public void setStatus(final UserRegStatus status) {
        this.status = status;
    }

    @XmlTransient
    public User getUser() {
        return user;
    }

    public void setUser(final User user) {
        this.user = user;
    }

}