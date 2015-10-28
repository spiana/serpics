package com.serpics.core;

public abstract class SerpicsException extends Exception {

	private static final long serialVersionUID = -1075271340522172790L;

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
