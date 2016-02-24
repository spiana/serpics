package com.serpics.base.facade.data;

import com.serpics.base.MediaSupportType;
import com.serpics.core.facade.AbstractData;

public class MediaData extends AbstractData {
	
	protected String contentType;
	MediaSupportType type;
	protected String name;
	protected String source;
	protected int sequence;
	protected String description;
	
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getSequence() {
		return sequence;
	}
	public void setSequence(int sequence) {
		this.sequence = sequence;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public MediaSupportType getType() {
		return type;
	}
	public void setType(MediaSupportType type) {
		this.type = type;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
}
