package com.serpics.core.service;

import com.serpics.core.Engine;
import com.serpics.core.session.SessionContext;

public interface SerpicsService<T extends SessionContext> {

	public Engine<T> getEngine(); 

	public void setEngine(Engine<T> engine) ;

	public  T getCurrentContext() ;
}
