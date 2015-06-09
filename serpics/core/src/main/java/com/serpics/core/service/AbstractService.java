package com.serpics.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.serpics.core.Engine;
import com.serpics.core.session.SessionContext;

@Transactional(readOnly = true)
public abstract class AbstractService<T extends SessionContext> implements SerpicsService<T>{

	@Autowired(required = true)
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
		T context = engine.getCurrentContext();
		Assert.notNull(context, "Context not initialized !");
		return context;
	}
	
}
