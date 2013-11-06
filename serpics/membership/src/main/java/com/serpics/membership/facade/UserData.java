package com.serpics.membership.facade;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;

public class UserData {
	private String firstname;
	private String lastname;
	private String phone;
	private String email;
	private String field1;
	private String field2;
	private BigDecimal field3;
	private BigInteger field4;
	private Date lastVisit;

	private String logonid;
	private String alternateEmail;
	private String changeanswer;
	private String changequestion;
	private String dn;
	private Timestamp lastLogin;
	private BigInteger localeId;
	private Timestamp passwordChange;
	
	
	
	
	
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getField1() {
		return field1;
	}
	public void setField1(String field1) {
		this.field1 = field1;
	}
	public String getField2() {
		return field2;
	}
	public void setField2(String field2) {
		this.field2 = field2;
	}
	public BigDecimal getField3() {
		return field3;
	}
	public void setField3(BigDecimal field3) {
		this.field3 = field3;
	}
	public BigInteger getField4() {
		return field4;
	}
	public void setField4(BigInteger field4) {
		this.field4 = field4;
	}
	public Date getLastVisit() {
		return lastVisit;
	}
	public void setLastVisit(Date lastVisit) {
		this.lastVisit = lastVisit;
	}
	public String getLogonid() {
		return logonid;
	}
	public void setLogonid(String logonid) {
		this.logonid = logonid;
	}
	public String getAlternateEmail() {
		return alternateEmail;
	}
	public void setAlternateEmail(String alternateEmail) {
		this.alternateEmail = alternateEmail;
	}
	public String getChangeanswer() {
		return changeanswer;
	}
	public void setChangeanswer(String changeanswer) {
		this.changeanswer = changeanswer;
	}
	public String getChangequestion() {
		return changequestion;
	}
	public void setChangequestion(String changequestion) {
		this.changequestion = changequestion;
	}
	public String getDn() {
		return dn;
	}
	public void setDn(String dn) {
		this.dn = dn;
	}
	public Timestamp getLastLogin() {
		return lastLogin;
	}
	public void setLastLogin(Timestamp lastLogin) {
		this.lastLogin = lastLogin;
	}
	public BigInteger getLocaleId() {
		return localeId;
	}
	public void setLocaleId(BigInteger localeId) {
		this.localeId = localeId;
	}
	public Timestamp getPasswordChange() {
		return passwordChange;
	}
	public void setPasswordChange(Timestamp passwordChange) {
		this.passwordChange = passwordChange;
	}

	
}
