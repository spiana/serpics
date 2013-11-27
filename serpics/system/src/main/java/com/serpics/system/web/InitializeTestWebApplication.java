package com.serpics.system.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import com.serpics.membership.services.BaseService;

public class InitializeTestWebApplication  implements ApplicationListener<ContextRefreshedEvent>{

	@Autowired
	BaseService baseService;
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		if (!baseService.isInitialized())
			baseService.initIstance();
	}

	
}
