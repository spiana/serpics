package com.serpics.membership.hooks;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.serpics.core.hook.AbstractHookFactory;

@Component("membershipHook")
@Scope("store")
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
