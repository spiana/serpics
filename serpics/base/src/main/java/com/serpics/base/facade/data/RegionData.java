package com.serpics.base.facade.data;

import com.serpics.core.facade.AbstractData;

public class RegionData  extends AbstractData{ 

	protected String  description;
	protected String name;

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
