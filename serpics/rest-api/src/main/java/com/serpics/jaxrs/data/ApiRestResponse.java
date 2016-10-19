/*******************************************************************************
 * Copyright 2014-2016  StepFour Srl
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *  
 *      http://www.apache.org/licenses/LICENSE-2.0
 *  
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *  
 *******************************************************************************/
package com.serpics.jaxrs.data;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;



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
