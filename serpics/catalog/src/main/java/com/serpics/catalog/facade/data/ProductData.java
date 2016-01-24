package com.serpics.catalog.facade.data;

import org.codehaus.jackson.annotate.JsonPropertyOrder;

@JsonPropertyOrder({"sort","numberOfElements","first","last","size","number","content","medias","buyable","dowloadable","totalPages","totalElements" })
public class ProductData  extends AbstractProductData{
	
}
