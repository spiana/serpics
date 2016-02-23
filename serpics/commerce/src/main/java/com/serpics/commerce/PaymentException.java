package com.serpics.commerce;

import com.serpics.core.SerpicsException;

public class PaymentException extends SerpicsException{

	public PaymentException() {
		super();
	}

	public PaymentException(String message, Throwable cause) {
		super(message, cause);
		
	}

	public PaymentException(String message) {
		super(message);
	}

	public PaymentException(Throwable cause) {
		super(cause);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -9052158006776713841L;

}
