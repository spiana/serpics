package com.serpics.jaxrs.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class UserDataRequest {
	
	private String firstname;
	private String lastname;
	private String phone;
	private String email;
	private String field1;
	private String field2;
	private Double field3;
	private Integer field4;
	
	private String logonid;
	private String password;
	
	private String alternateEmail;
	private String changeanswer;
	private String changequestion;
	private String dn;
	
	
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
	public Double getField3() {
		return field3;
	}
	public void setField3(Double field3) {
		this.field3 = field3;
	}
	public Integer getField4() {
		return field4;
	}
	public void setField4(Integer field4) {
		this.field4 = field4;
	}
	public String getLogonid() {
		return logonid;
	}
	public void setLogonid(String logonid) {
		this.logonid = logonid;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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

	
	

}
