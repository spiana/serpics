package com.serpics.catalog.facade.data;

public class CtentryAttributesData {
	protected Long attributeId;
	protected Long baseAttributeId;
	protected Long ctentryId;
	protected int isLogical;
	protected int sequence;
	public Long getAttributeId() {
		return attributeId;
	}
	public void setAttributeId(Long attributeId) {
		this.attributeId = attributeId;
	}
	public Long getBaseAttributeId() {
		return baseAttributeId;
	}
	public void setBaseAttributeId(Long baseAttributeId) {
		this.baseAttributeId = baseAttributeId;
	}
	public Long getCtentryId() {
		return ctentryId;
	}
	public void setCtentryId(Long ctentryId) {
		this.ctentryId = ctentryId;
	}
	public int getIsLogical() {
		return isLogical;
	}
	public void setIsLogical(int isLogical) {
		this.isLogical = isLogical;
	}
	public int getSequence() {
		return sequence;
	}
	public void setSequence(int sequence) {
		this.sequence = sequence;
	}
	
}
