package com.serpics.system.test;

import org.junit.Test;

import com.serpics.core.EngineFactory;


public class ContextInitializingTest {

	
	@Test
	public void initContext(){
		EngineFactory.init();
	}
}
