package com.serpics.core.test.hooks;

import com.serpics.core.hook.AbstractHook;
import com.serpics.stereotype.HookImplementation;

@HookImplementation("test")
public class TestHookImpl extends AbstractHook implements TestHook {

}