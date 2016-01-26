package com.serpics.scheduler;

import org.springframework.context.ApplicationContext;

import com.serpics.core.EngineFactory;

public class Application {
	
	public static void main(String[] args) {
		System.out.println("Inizio...");
		EngineFactory.init();
		ApplicationContext ctx =  EngineFactory.getCurrentApplicationContext();
		System.out.println("Fine");
		
	}
}
