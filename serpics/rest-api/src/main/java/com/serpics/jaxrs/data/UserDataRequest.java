package com.serpics.jaxrs.data;

public class UserDataRequest  {
	
	private String firstname;
	private String lastname;
	private String email;
	private String logonid;
	private String password;
	
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
}
