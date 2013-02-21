package com.serpics.core;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import com.serpics.core.hook.Hook;

public class SerpicsPostProcessor implements BeanPostProcessor {

	@Override
	public Object postProcessAfterInitialization(Object arg0, String arg1) throws BeansException {
		// TODO Auto-generated method stub
		if (arg0.getClass().getAnnotation(Hook.class) != null)
			System.out.println(arg0.getClass().getAnnotation(Hook.class));

		return arg0;
	}

	@Override
	public Object postProcessBeforeInitialization(Object arg0, String arg1) throws BeansException {
		return arg0;

	}

}
