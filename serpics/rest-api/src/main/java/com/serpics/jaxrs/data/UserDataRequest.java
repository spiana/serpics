package com.serpics.jaxrs.data;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonPropertyOrder;

import com.serpics.membership.facade.data.UserData;

@JsonIgnoreProperties({ "updated","created","uuid","id"})
@JsonPropertyOrder({"firstname","lastname","email","logonid","password" })
public class UserDataRequest  extends UserData{
	{


	}
}
