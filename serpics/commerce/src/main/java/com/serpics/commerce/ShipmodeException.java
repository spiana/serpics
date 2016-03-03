package com.serpics.commerce;

import com.serpics.core.SerpicsException;

public class ShipmodeException extends SerpicsException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2440382333101929251L;

	public ShipmodeException() {
		super();
	}

	public ShipmodeException(String message, Throwable cause) {
		super(message, cause);
		
	}

	public ShipmodeException(String message) {
		super(message);
	}

	public ShipmodeException(Throwable cause) {
		super(cause);
	}
}
