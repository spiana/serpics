package com.serpics.core.facade;

import java.util.List;

public interface PopulatorList<SOURCE , TARGET> {
	
	/**
	 * Get the list of populators.
	 * 
	 * @return the populators.
	 */
	List<Populator<SOURCE, TARGET>> getPopulators();

	/**
	 * Set the list of populators.
	 * 
	 * @param populators
	 *           the populators
	 */
	void setPopulators(List<Populator<SOURCE, TARGET>> populators);

}
