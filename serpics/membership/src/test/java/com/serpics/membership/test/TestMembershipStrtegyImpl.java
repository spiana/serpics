package com.serpics.membership.test;

import com.serpics.membership.strategies.MembershipStrategyImpl;
import com.serpics.stereotype.StoreStrategy;

@StoreStrategy(value="membershipStrategy", store = "test-store")
public class TestMembershipStrtegyImpl extends MembershipStrategyImpl {

}
