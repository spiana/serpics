package com.serpics.membership.facade.data;

import java.util.Date;
import java.util.List;

import org.codehaus.jackson.annotate.JsonPropertyOrder;

import com.serpics.core.facade.AbstractData;
import com.serpics.membership.UserType;

@JsonPropertyOrder({"id","uuid","created","firstname","lastname","logonid","password",
	"phone","email","alternateEmail","userType","lastVisit","lastLogin","changeanswer",
	"changequestion","dn","passwordChange","contactAddress","billingAddress","destinationAddress" })
public class UserData extends AbstractData{
	protected String firstname;
	protected String lastname;
	protected String phone;
	protected String email;
	protected UserType userType;
	protected String field1;
	protected String field2;
	protected Double field3;
	protected Integer field4;
	
	protected Date lastVisit;
	protected String logonid;
	protected String password;
	
	protected String alternateEmail;
	protected String changeanswer;
	protected String changequestion;
	protected String dn;
	protected Date lastLogin;
	protected Date passwordChange;
	
	protected AddressData contactAddress;
	protected AddressData billingAddress;
	
	protected List<AddressData> destinationAddress;

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

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
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

	public Date getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	public Date getPasswordChange() {
		return passwordChange;
	}

	public void setPasswordChange(Date passwordChange) {
		this.passwordChange = passwordChange;
	}
	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}
	public AddressData getContactAddress() {
		return contactAddress;
	}

	public void setContactAddress(AddressData contactAddress) {
		this.contactAddress = contactAddress;
	}

	public AddressData getBillingAddress() {
		return billingAddress;
	}

	public void setBillingAddress(AddressData billingAddress) {
		this.billingAddress = billingAddress;
	}

	public List<AddressData> getDestinationAddress() {
		return destinationAddress;
	}

	public void setDestinationAddress(List<AddressData> destinationAddress) {
		this.destinationAddress = destinationAddress;
	}
	
	
}
