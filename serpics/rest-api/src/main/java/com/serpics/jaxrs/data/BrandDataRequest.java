package com.serpics.jaxrs.data;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonPropertyOrder;

import com.serpics.catalog.facade.data.BrandData;

@JsonIgnoreProperties({ "updated","created","uuid","id","brandProductNumber" })
@JsonPropertyOrder({"name","logo","published" })
public class BrandDataRequest  extends BrandData{

}
