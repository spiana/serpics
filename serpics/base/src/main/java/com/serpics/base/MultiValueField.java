package com.serpics.base;

import java.util.Date;

public interface MultiValueField {

	public AttributeType getAttributeType();
	public void setAttributeType(AttributeType type);
	
    public Date getDateValue() ;
   
    public Integer getIntegerValue() ;
   
    public Double getDoubleValue();
    
    public String getStringValue() ;
  
    public void setDateValue(Date value) ;
    
    public void setIntegerValue(Integer value) ;
   
    public void setDoubleValue(Double value);
    
    public void setStringValue(String value) ;

  
}
