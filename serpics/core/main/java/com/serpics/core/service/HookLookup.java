package com.serpics.core.service;

import com.serpics.core.hook.AbstractHook;
import com.serpics.core.session.SessionContext;

public interface HookLookup {

	public AbstractHook fetchHook(SessionContext context, String hookName);

}
