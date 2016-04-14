package com.serpics.commerce;

import com.serpics.core.SerpicsException;

public class EmptyCartException extends SerpicsException{

	public EmptyCartException() {
		super();
	}

	public EmptyCartException(String message, Throwable cause) {
		super(message, cause);
		
	}

	public EmptyCartException(String message) {
		super(message);
	}

	public EmptyCartException(Throwable cause) {
		super(cause);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -9052158006776713841L;

}


