package com.serpics.membership.strategies;

import org.springframework.context.annotation.Scope;

import com.serpics.stereotype.StoreStrategy;

@StoreStrategy(value="membership", store = "test-store")
public class TestMembershipStrtegyImpl extends MembershipStrategyImpl {

}
