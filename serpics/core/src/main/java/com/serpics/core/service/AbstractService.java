package com.serpics.core.service;

import javax.annotation.Resource;

import org.springframework.transaction.annotation.Transactional;

import com.serpics.core.Engine;
import com.serpics.core.session.SessionContext;

@Transactional(readOnly = true)
public abstract class AbstractService<T extends SessionContext> implements SerpicsService<T>{

	//@Autowired(required = true)
	@Resource(name="commerceEngine")
	Engine<T> engine;

	@Override
	public Engine<T> getEngine() {
		return engine;
	}

	@Override
	public void setEngine(Engine<T> engine) {
		this.engine = engine;
	}

	@Override
	public T getCurrentContext() {
		return engine.getCurrentContext();
	}
	
}
