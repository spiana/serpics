package com.serpics.core.test.hooks;

import com.serpics.core.hook.AbstractHook;
import com.serpics.stereotype.HookImplementation;

@HookImplementation(value="test",store="test-store")
public class AlternativeTestHookImpl extends AbstractHook implements TestHook {

}
