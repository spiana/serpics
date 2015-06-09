package com.serpics.core.facade;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.util.Assert;

public abstract class AbstractConverter<SOURCE , TARGET> implements Converter<SOURCE, TARGET> , Populator<SOURCE, TARGET> {

	Class<TARGET> targetClass;
	
	@Override
	public TARGET convert(SOURCE source){
		 TARGET target;
		 target = createTarget();
		 populate(source , target);
		 
		 return target;
	}
	


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



	@Required
	public void setTargetClass(Class<TARGET> targetClass) {
		this.targetClass = targetClass;
	}
}
