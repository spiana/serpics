package com.serpics.commerce.hooks;

import com.serpics.membership.persistence.User;

public interface CommerceHook {

	public void mergeCart(User oldUser);
}
