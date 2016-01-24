package com.serpics.jaxrs.data;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonPropertyOrder;

import com.serpics.catalog.facade.data.CategoryData;

@JsonIgnoreProperties({ "updated","created","uuid","id" })
@JsonPropertyOrder({"code","description","published" })
public class CategoryDataRequest  extends CategoryData{

}
