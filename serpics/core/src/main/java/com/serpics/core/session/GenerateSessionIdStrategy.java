package com.serpics.core.session;

public interface GenerateSessionIdStrategy {
	
	public String generate(String realm);

}
