package com.serpics.membership.persistence;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.serpics.core.datatype.UserRegisterType;
import com.serpics.util.gson.GsonTransient;

import java.math.BigInteger;
import java.sql.Timestamp;

/**
 * The persistent class for the users_reg database table.
 * 
 */
@XmlRootElement(name="usersreg")
@Entity
@Table(name = "users_reg")
public class UsersReg implements Serializable {
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

	@Column(nullable = false, length = 40 , unique=true)
	private String logonid;

	@Column(nullable = false, length = 254)
	private String password;

	@Column(name = "password_change")
	private Timestamp passwordChange;

	@Column(nullable = false, length = 1)
	private String status;

	private Timestamp updated;
	
	
	@OneToOne( fetch = FetchType.EAGER, optional = false )
	@JoinColumn(name = "user_id", nullable = false, insertable = false, updatable = false )
	private User user;

	public UsersReg() {
		this.status = UserRegisterType.ACTIVE;
	}

	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getAlternateEmail() {
		return this.alternateEmail;
	}

	public void setAlternateEmail(String alternateEmail) {
		this.alternateEmail = alternateEmail;
	}

	public String getChangeanswer() {
		return this.changeanswer;
	}

	public void setChangeanswer(String changeanswer) {
		this.changeanswer = changeanswer;
	}

	public String getChangequestion() {
		return this.changequestion;
	}

	public void setChangequestion(String changequestion) {
		this.changequestion = changequestion;
	}

	public Timestamp getCreated() {
		return this.created;
	}

	public void setCreated(Timestamp created) {
		this.created = created;
	}

	public String getDn() {
		return this.dn;
	}

	public void setDn(String dn) {
		this.dn = dn;
	}

	public String getField1() {
		return this.field1;
	}

	public void setField1(String field1) {
		this.field1 = field1;
	}

	public String getField2() {
		return this.field2;
	}

	public void setField2(String field2) {
		this.field2 = field2;
	}

	public Timestamp getLastLogin() {
		return this.lastLogin;
	}

	public void setLastLogin(Timestamp lastLogin) {
		this.lastLogin = lastLogin;
	}

	public BigInteger getLocaleId() {
		return this.localeId;
	}

	public void setLocaleId(BigInteger localeId) {
		this.localeId = localeId;
	}

	public String getLogonid() {
		return this.logonid;
	}

	public void setLogonid(String logonid) {
		this.logonid = logonid;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Timestamp getPasswordChange() {
		return this.passwordChange;
	}

	public void setPasswordChange(Timestamp passwordChange) {
		this.passwordChange = passwordChange;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Timestamp getUpdated() {
		return this.updated;
	}

	public void setUpdated(Timestamp updated) {
		this.updated = updated;
	}

	@XmlTransient
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}