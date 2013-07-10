package com.serpics.membership.hooks;

import com.serpics.core.hook.AbstractHookFactory;

public class MembershipHookFactory extends AbstractHookFactory<MembershipHook> {

	@Override
	public Class<?> getObjectType() {
		return MembershipHook.class;
	}

	@Override
	public MembershipHook createHookInstance() {
		return new MembershipHookImpl();
	}

}
