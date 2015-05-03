package com.serpics.membership.data.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.serpics.base.data.model.Locale;
import com.serpics.core.security.UserDetail;
import com.serpics.membership.UserRegStatus;
import com.serpics.membership.UserType;

/**
 * The persistent class for the users_reg database table.
 * 
 */

@Entity
@Table(name = "users_reg")
public class UsersReg extends User implements Serializable, UserDetail {
    private static final long serialVersionUID = 1L;

    @Size(max = 100)
    @Column(name = "alternate_email", length = 100)
    private String alternateEmail;

    @Column(length = 254)
    private String changeanswer;

    @Column(length = 254)
    private String changequestion;

    private Timestamp created;

    @Column(length = 500)
    private String dn;

    @Column(name = "last_login")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastLogin;

    @Column(name = "locale_id")
    private Locale locale;

    @Size(min = 5, max = 100)
    @Column(nullable = true, length = 40, unique = true)
    private String logonid;

    @Column(nullable = true, length = 254)
    private String password;

    @Column(name = "password_change")
    @Temporal(TemporalType.TIMESTAMP)
    private Date passwordChange;

    @NotNull
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRegStatus status;


    public UsersReg() {
        super();
        this.status = UserRegStatus.ACTIVE;
        setUserType(UserType.REGISTERED);
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

   
    public String getDn() {
        return this.dn;
    }

    public void setDn(final String dn) {
        this.dn = dn;
    }


    public Date getLastLogin() {
        return this.lastLogin;
    }

    public void setLastLogin(final Date lastLogin) {
        this.lastLogin = lastLogin;
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

    public Date getPasswordChange() {
        return this.passwordChange;
    }

    public void setPasswordChange(final Date passwordChange) {
        this.passwordChange = passwordChange;
    }

    public UserRegStatus getStatus() {
        return this.status;
    }

    public void setStatus(final UserRegStatus status) {
        this.status = status;
    }

    @Override
    public String getName() {
        return logonid;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(final Locale locale) {
        this.locale = locale;
    }

}