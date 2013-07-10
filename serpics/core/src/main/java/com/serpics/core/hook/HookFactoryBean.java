package com.serpics.core.hook;

import com.serpics.membership.hooks.MembershipHookImpl;

public class HookFactoryBean<T> {

	public T getObject() throws Exception {
		// TODO Auto-generated method stub
		return (T) new MembershipHookImpl();
	}

}
