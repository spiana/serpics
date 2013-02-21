package com.serpics.core.hook;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.FatalBeanException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.RootBeanDefinition;

import com.serpics.membership.hooks.MembershipHookImpl;

public class HookScannerPostProcessor implements BeanDefinitionRegistryPostProcessor {
	
	private static Logger logger = LoggerFactory.getLogger(HookScannerPostProcessor.class);
	
	@SuppressWarnings("rawtypes")
	Map<String, Map> storeMap = new HashMap<String, Map>();
	
	Map<String, Map> hooksMap;
	
	private void doHookScan(){
		Map<String, Class<?>> storeHooks = new HashMap<String, Class<?>>();
		storeHooks.put("membershipHook", MembershipHookImpl.class);
		storeMap.put("default-store", storeHooks);
	}
	
	public Class<?> getHookImplementation(String storeName, String hookName){
		return (Class<?>) storeMap.get(storeName).get(hookName);
	}

	@Override
	public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
		Class<?> type = MembershipHookImpl.class;
		String interfaceName = getImplementedHookInterface(type);
		if (interfaceName == null)
			throw new FatalBeanException("No Hook interface found for class " + type.getName());
			
		
			
		
		GenericHookFactory f = new GenericHookFactory(type); 
		BeanDefinition definition = new RootBeanDefinition(GenericHookFactory.class);
		definition.getConstructorArgumentValues().addGenericArgumentValue(type);
//		definition.setScope(scope)
		registry.registerBeanDefinition(interfaceName, definition);
		logger.info("Registered factory {} for Class {}", interfaceName, type.getName());
		
	}

	private String getImplementedHookInterface(Class<?> clazz){
		for (Class<?> c : clazz.getInterfaces()){
			if (c.getName().endsWith("Hook"))
				return c.getSimpleName();
		}
		return null;
		
	}
	
	
	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		// TODO Auto-generated method stub
		
	}

}
