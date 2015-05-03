package com.serpics.core.test.hook;

import com.serpics.stereotype.StoreStrategy;

@StoreStrategy(value="testHook"  , store="store1")
public class TestHook1Impl extends TestHookImpl {
	
	
	@Override
	public String sayGood() {
		return "is all good !";
	}

}
