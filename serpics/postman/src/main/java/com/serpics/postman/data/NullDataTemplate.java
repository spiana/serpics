package com.serpics.postman.data;

import org.hsqldb.lib.HashMap;

public class NullDataTemplate implements DataTemplate {

	@Override
	public Object getDataForTemplate() {
		return new HashMap();
	}

}
