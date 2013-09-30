package com.serpics.membership.hooks;

import org.springframework.context.annotation.Scope;

import com.serpics.stereotype.Hook;
import com.serpics.stereotype.StoreHook;

@StoreHook(value="membership", store = "test-store")
public class TestMembershipHookImpl extends MembershipHookImpl {

}
