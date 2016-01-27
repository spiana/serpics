package com.serpics.jaxrs.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class UserDataRequest {
	
	
	private String firstname;
	private String lastname;
	private String phone;
	private String email;
	
	private String logonid;
	private String password;

	private String changeanswer;
	private String changequestion;
	
	
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

}
