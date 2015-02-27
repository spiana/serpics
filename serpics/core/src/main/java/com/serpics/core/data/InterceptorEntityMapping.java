package com.serpics.core.data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import com.serpics.core.persistence.jpa.AbstractEntity;

public class InterceptorEntityMapping<T extends Interceptor> extends HashMap<String,  List<T>>  {
	
	private static final long serialVersionUID = 2773330666126105679L;

	private class Comparator implements java.util.Comparator<Interceptor>{
		@Override
		public int compare(Interceptor o1, Interceptor o2) {
			return o1.getOrder().compareTo(o2.getOrder());
		}
		
	}
	
	public void put(String entityName, T interceptor){
		if (super.get(entityName) == null){
			super.put(entityName, new ArrayList<T>());
		}	
			super.get(entityName).add(interceptor);
			Collections.sort(super.get(entityName), new Comparator());
	}
}
