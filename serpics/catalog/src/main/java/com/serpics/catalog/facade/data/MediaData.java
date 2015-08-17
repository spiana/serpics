package com.serpics.catalog.facade.data;

public class MediaData {
	protected String contentType;
	protected short mediaType;
	protected String name;
	protected String src;
	protected int sequence;
	protected String description;
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	public short getMediaType() {
		return mediaType;
	}
	public void setMediaType(short mediaType) {
		this.mediaType = mediaType;
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
	public String getSrc() {
		return src;
	}
	public void setSrc(String src) {
		this.src = src;
	}

}
