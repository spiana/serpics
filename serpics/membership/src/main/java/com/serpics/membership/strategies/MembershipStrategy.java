package com.serpics.membership.strategies;

import com.serpics.core.SerpicsException;
import com.serpics.core.security.UserDetail;
import com.serpics.membership.data.model.UsersReg;

public interface MembershipStrategy {

	public UserDetail login(String userName, char[] password) throws SerpicsException;
	public UsersReg loadUserByUserName(String userName) throws SerpicsException;
}
