package com.serpics.membership.hooks;

import org.springframework.context.annotation.Scope;

import com.serpics.core.hook.Hook;
import com.serpics.core.hook.HookImplementation;

@HookImplementation(value="membership", store = "test-store")
public class TestMembershipHookImpl extends MembershipHookImpl {

}
