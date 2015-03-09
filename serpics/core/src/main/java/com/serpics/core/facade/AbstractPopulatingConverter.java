package com.serpics.core.facade;

import java.util.List;

public abstract class AbstractPopulatingConverter<SOURCE , TARGET> extends AbstractConverter<SOURCE, TARGET> 
	implements PopulatorList<SOURCE, TARGET>{

	List<Populator<SOURCE, TARGET>> populators;
	
	public List<Populator<SOURCE, TARGET>> getPopulators() {
		return populators;
	}

	public void setPopulators(List<Populator<SOURCE, TARGET>> populators) {
		this.populators = populators;
	}

	@Override
	public void populate(final SOURCE source, final TARGET target)
	{
		final List<Populator<SOURCE, TARGET>> list = getPopulators();
		if (list != null)
		{
			for (final Populator<SOURCE, TARGET> populator : list)
			{
				if (populator != null)
				{
					populator.populate(source, target);
				}
			}
		}
	}
	
}
