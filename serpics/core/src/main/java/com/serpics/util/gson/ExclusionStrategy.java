package com.serpics.util.gson;

import com.google.gson.FieldAttributes;


public class ExclusionStrategy implements com.google.gson.ExclusionStrategy {

	@Override
	public boolean shouldSkipClass(Class<?> arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean shouldSkipField(FieldAttributes arg0) {
		return arg0.getAnnotation(GsonTransient.class) != null;
	}

}
