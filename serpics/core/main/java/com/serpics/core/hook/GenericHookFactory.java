package com.serpics.core.hook;

import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;

import com.serpics.core.AbstractAutowiringFactoryBean;
import com.serpics.core.CommerceEngine;

public class GenericHookFactory<T> extends AbstractAutowiringFactoryBean<T> implements InitializingBean{
	
	static final Logger logger = LoggerFactory.getLogger("GenericHookFactory");
	
	@Resource
	CommerceEngine commerceEngine;
	
	Map <String, Class<?>> hookImpls;
	
	@Resource
	HookScannerPostProcessor hookScannerPostProcessor;
	
	Class<?> objectType;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public GenericHookFactory(Class<?> objectType, Map hookImpls){
		this.objectType = objectType;
		this.hookImpls = hookImpls;
	}

	@Override
	protected T doCreateInstance() {
		T hook = createHookInstance();
		((AbstractHook) hook).setSessionContext(commerceEngine.getCurrentContext());
		return hook;
	}

	public T createHookInstance(){
		T ref = null;
		final String store = commerceEngine.getCurrentContext().getStoreRealm().getName();
		Class<?> impl = hookImpls.get(store);
		try{
			ref = (T) impl.newInstance();
		} catch (Exception e){
			logger.error("error", e);
		};
		return ref;
	}
	
	@Override
	public Class<?> getObjectType() {				
		return objectType;
	}
	

}
