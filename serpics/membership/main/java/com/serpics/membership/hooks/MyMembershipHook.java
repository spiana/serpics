package com.serpics.membership.hooks;

import org.springframework.context.annotation.Scope;

import com.serpics.core.hook.Hook;
import com.serpics.core.hook.HookImplementation;

@HookImplementation(value="membership", store = "my-store")
@Scope("store")
public class MyMembershipHook extends MembershipHookImpl {

}
