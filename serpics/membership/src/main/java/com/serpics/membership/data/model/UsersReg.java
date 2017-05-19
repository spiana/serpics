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


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.serpics.core.security.UserDetail;
import com.serpics.i18n.data.model.Locale;
import com.serpics.membership.UserRegStatus;
import com.serpics.membership.UserType;

/**
 * The persistent class for the users_reg database table.
 * 
 */

@Entity
@Table(name = "users_reg")
public class UsersReg extends User implements UserDetail {
  

private static final long serialVersionUID = 9178702090616745340L;

	@Size(max = 100, message = "{usersReg.alternateEmail.size}")
    @Column(name = "alternate_email", length = 100)
    private String alternateEmail;

	@Size(max = 254, message = "{usersReg.alternateEmail.changeanswer}")
    @Column(length = 254)
    private String changeanswer;

	@Size(max = 254, message = "{usersReg.alternateEmail.changequestion}")
    @Column(length = 254)
    private String changequestion;

	@Size(max = 254, message = "{usersReg.dn.size}")
    @Column(length = 254)
    private String dn;

    @Column(name = "last_login")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastLogin;

    @ManyToOne
    @JoinColumn(name="locale_id" , nullable=true)
    private Locale locale;

    @Size(min = 5, max = 100, message = "{usersReg.logonid.size}")
    @Column(nullable = false, length = 100, unique = true)
    @NotNull(message = "{usersReg.logonid.notnull}")
    private String logonid;

    @Size(max = 100, message = "{usersReg.password.size}")
    @Column(nullable = true, length = 100)
    private String password;

    @Column(name = "password_change")
    @Temporal(TemporalType.TIMESTAMP)
    private Date passwordChange;

    @NotNull(message ="usersReg.status.notnull")
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRegStatus status;


    public UsersReg() {
        super();
        this.status = UserRegStatus.ACTIVE;
        setUserType(UserType.REGISTERED);
    }


    public UsersReg(UserType userType, String firstname, String lastname,
			String phone, String email) {
		super(userType, firstname, lastname, phone, email);
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