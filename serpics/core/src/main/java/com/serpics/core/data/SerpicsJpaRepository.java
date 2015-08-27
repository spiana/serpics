package com.serpics.core.data;

import com.serpics.core.Engine;
import com.serpics.core.session.SessionContext;

public interface SerpicsJpaRepository{

	public void setRepositoryIniziatializer(RepositoryInitializer inizializer);
	public void  setEngine(Engine<SessionContext> engine);
}
