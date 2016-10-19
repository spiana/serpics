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
package com.serpics.catalog.facade.data;

public class CtentryAttributesData {
	protected Long attributeId;
	protected Long baseAttributeId;
	protected Long ctentryId;
	protected int isLogical;
	protected int sequence;
	public Long getAttributeId() {
		return attributeId;
	}
	public void setAttributeId(Long attributeId) {
		this.attributeId = attributeId;
	}
	public Long getBaseAttributeId() {
		return baseAttributeId;
	}
	public void setBaseAttributeId(Long baseAttributeId) {
		this.baseAttributeId = baseAttributeId;
	}
	public Long getCtentryId() {
		return ctentryId;
	}
	public void setCtentryId(Long ctentryId) {
		this.ctentryId = ctentryId;
	}
	public int getIsLogical() {
		return isLogical;
	}
	public void setIsLogical(int isLogical) {
		this.isLogical = isLogical;
	}
	public int getSequence() {
		return sequence;
	}
	public void setSequence(int sequence) {
		this.sequence = sequence;
	}
	
}
