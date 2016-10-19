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
package com.serpics.postman.data;

public class CoupleStore<T, R> {
	
	private T leftValue;
	
	private R rightValue;

	public CoupleStore() {
		
		leftValue = null;
	}
	
	public CoupleStore(T leftValue, R rightValue) {
		this.leftValue = leftValue;
		this.rightValue = rightValue;
	}

	public T getLeftValue() {
		return leftValue;
	}

	public void setLeftValue(T leftValue) {
		this.leftValue = leftValue;
	}

	public R getRightValue() {
		return rightValue;
	}

	public void setRightValue(R rightValue) {
		this.rightValue = rightValue;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CoupleStore [leftValue=");
		builder.append(leftValue);
		builder.append(", rightValue=");
		builder.append(rightValue);
		builder.append("]");
		return builder.toString();
	}
	
	
}
