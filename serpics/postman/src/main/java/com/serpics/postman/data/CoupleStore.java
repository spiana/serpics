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
