package com.serpics.base.data.model;

import java.util.Date;

import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.serpics.base.AttributeType;
import com.serpics.base.MultiValueField;

@Embeddable
public class MultiValueAttribute implements MultiValueField{

	public MultiValueAttribute() {
		super();
		attributeType = AttributeType.STRING;
	}


	@Temporal(TemporalType.DATE)
    private Date dateValue;
    
    private Integer integerValue;
    private Double doubleValue;

    private String stringValue;

    private AttributeType attributeType;
	
    public Date getDateValue() {
		return dateValue;
	}

	public void setDateValue(Date dateValue) {
		this.dateValue = dateValue;
	}

	public Integer getIntegerValue() {
		return integerValue;
	}

	public void setIntegerValue(Integer integerValue) {
		this.integerValue = integerValue;
	}

	public Double getDoubleValue() {
		return doubleValue;
	}

	public void setDoubleValue(Double doubleValue) {
		this.doubleValue = doubleValue;
	}

	public String getStringValue() {
		return stringValue;
	}

	public void setStringValue(String stringValue) {
		this.stringValue = stringValue;
	}


	public AttributeType getAttributeType() {
		return attributeType;
	}


	public void setAttributeType(AttributeType attributeType) {
		this.attributeType = attributeType;
	}


}
