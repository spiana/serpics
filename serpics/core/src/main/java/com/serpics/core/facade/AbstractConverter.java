package com.serpics.core.facade;

import java.util.List;

import org.springframework.util.Assert;

public abstract class AbstractConverter<SOURCE , TARGET> implements Converter<SOURCE, TARGET> , Populator<SOURCE, TARGET> {

	Class<TARGET> targetClass;
	
	public TARGET Convert(SOURCE source){
		 TARGET target;
		 target = createTarget();
		 populate(source , target);
		 
		 return target;
	}
	
	@SuppressWarnings("unchecked")
	public TARGET createTarget() {
			Assert.notNull(targetClass);
			try
			{
				return targetClass.newInstance();
			}
			catch (InstantiationException | IllegalAccessException e)
			{
				throw new RuntimeException(e);
			}
	}
}
