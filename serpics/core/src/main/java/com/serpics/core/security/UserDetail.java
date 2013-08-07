package com.serpics.core.security;

import java.security.Principal;

public interface UserDetail  extends Principal {
	public Long getUserId();
	public String getName();
	public Long getStoreId();

	
}
