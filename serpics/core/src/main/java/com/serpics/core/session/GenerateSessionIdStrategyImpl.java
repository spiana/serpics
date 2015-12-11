package com.serpics.core.session;

import java.util.UUID;

import org.apache.commons.codec.binary.Base64;

public class GenerateSessionIdStrategyImpl implements GenerateSessionIdStrategy {

	@Override
	public String generate(String realm) {
		String sessionId = UUID.randomUUID().toString();
		String token_prefix = Base64.encodeBase64String(realm.getBytes());
		sessionId = token_prefix + "-"+ sessionId;
		
		return sessionId;
	}

}
