package com.serpics.core.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class InterceptorEntityMapping extends HashMap<String,  List<InterceptorMapping>>  {
	
	private static final long serialVersionUID = 2773330666126105679L;

	private class Comparator implements java.util.Comparator<InterceptorMapping>{
		@Override
		public int compare(InterceptorMapping o1, InterceptorMapping o2) {
			return o2.getOrder() - o1.getOrder();
		}
		
	}
	
	public void put(String entityName, InterceptorMapping mapping){
		if (super.get(entityName) == null){
			super.put(entityName, new ArrayList<InterceptorMapping>());
		}	
			super.get(entityName).add(mapping);
			
			Collections.sort(super.get(entityName), new Comparator());
	}
}
