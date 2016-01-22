package com.serpics.jaxrs.data;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.serpics.membership.facade.data.UserData;
@JsonIgnoreProperties({"updated"})
public class UserDataUpdateRequest extends UserData{

}
