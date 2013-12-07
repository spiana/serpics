package com.serpics.core.test.hook;

import com.serpics.stereotype.StoreHook;

@StoreHook(value="testHook" )
public class TestHookImpl implements TestHook {

	@Override
	public String sayGood() {
		return "Good";
	}

}