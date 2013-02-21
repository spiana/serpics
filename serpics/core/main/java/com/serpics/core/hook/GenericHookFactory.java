package com.serpics.core.hook;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.InitializingBean;

import com.serpics.core.AbstractAutowiringFactoryBean;
import com.serpics.core.CommerceEngine;

public class GenericHookFactory<T> extends AbstractAutowiringFactoryBean<T> implements InitializingBean{

	@Resource
	CommerceEngine commerceEngine;
	
	Map <String, Class<?>> hookImpls;
	
	@Resource
	HookScannerPostProcessor hookScannerPostProcessor;
	
	Class<?> objectType;
	
	public GenericHookFactory(Class<?> objectType){
		this.objectType = objectType;
	}

	@Override
	protected T doCreateInstance() {
		T hook = createHookInstance();
		((AbstractHook) hook).setSessionContext(commerceEngine.getCurrentContext());
		return hook;
	}

	public T createHookInstance(){
		T ref = null;
		try{
			ref = (T) objectType.newInstance();
		} catch (Exception e){};
		return ref;
	}
	
	@Override
	public Class<?> getObjectType() {				
		return objectType;
	}
	

}
