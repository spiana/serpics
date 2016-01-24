package com.serpics.jaxrs.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.serpics.membership.facade.data.UserData;
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDataUpdateRequest extends UserData{
	
	@JsonProperty("firstname")
	private String firstname;

	
	public String getFirstname() {
		return firstname;
	}
	

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	


	
}