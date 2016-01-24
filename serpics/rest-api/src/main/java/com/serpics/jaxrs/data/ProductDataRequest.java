package com.serpics.jaxrs.data;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonPropertyOrder;

import com.serpics.catalog.facade.data.ProductData;

@JsonIgnoreProperties({ "updated","created","uuid","id"})
@JsonPropertyOrder({"content","buyable","dowloadable" })
public class ProductDataRequest  extends ProductData{
	{


	}
}
