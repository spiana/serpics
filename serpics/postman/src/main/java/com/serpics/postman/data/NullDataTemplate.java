package com.serpics.postman.data;

import java.util.HashMap;

public class NullDataTemplate implements DataTemplate {

	@Override
	public Object getDataForTemplate() {
		return new HashMap<String,String>();
	}

}
