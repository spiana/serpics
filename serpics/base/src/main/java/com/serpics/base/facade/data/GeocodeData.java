package com.serpics.base.facade.data;

import com.serpics.core.facade.AbstractData;

public class GeocodeData extends AbstractData{ 
	protected String name;
	protected String description;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
