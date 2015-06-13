package com.serpics.membership.test;

import org.springframework.context.annotation.Scope;

import com.serpics.membership.strategies.MembershipStrategyImpl;
import com.serpics.stereotype.StoreStrategy;

@StoreStrategy(value="membership", store = "test-store")
public class TestMembershipStrtegyImpl extends MembershipStrategyImpl {

}
