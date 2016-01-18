package com.serpics.jaxrs.data;

import org.codehaus.jackson.annotate.JsonPropertyOrder;

@JsonPropertyOrder({"status","message","responseObject"})
public class ApiRestResponse<T> {

	private ApiRestResponseStatus status;
	private String message; //If ERROR status message
	//private String responseCode;
	private T responseObject;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

//	public String getResponseCode() {
//		return responseCode;
//	}

//	public void setResponseCode(String responseCode) {
//		this.responseCode = responseCode;
//	}

	public T getResponseObject() {
		return responseObject;
	}

	public void setResponseObject(T responseObject) {
		this.responseObject = responseObject;
	}

	public ApiRestResponseStatus getStatus() {
		return status;
	}

	public void setStatus(ApiRestResponseStatus status) {
		this.status = status;
	}

}
