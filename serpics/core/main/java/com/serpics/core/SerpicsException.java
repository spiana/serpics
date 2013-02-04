package com.serpics.core;

public abstract class SerpicsException extends Exception {

	public SerpicsException() {
		super();
		
	}

	public SerpicsException(String message, Throwable cause) {
		super(message, cause);
		
	}

	public SerpicsException(String message) {
		super(message);
		
	}

	public SerpicsException(Throwable cause) {
		super(cause);
		
	}

}
