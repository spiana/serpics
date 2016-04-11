package com.serpics.catalog.facade.data;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;



@JsonPropertyOrder({"sort","numberOfElements","first","last","size","number","content","medias","buyable","dowloadable","totalPages","totalElements" })
public class ProductVariantData  extends AbstractProductData{
	
	private double sequence;
	
	private List<VariantAttributeData> attributes;

	public double getSequence() {
		return sequence;
	}

	public void setSequence(double sequence) {
		this.sequence = sequence;
	}

	public List<VariantAttributeData> getAttributes() {
		return attributes;
	}

	public void setAttributes(List<VariantAttributeData> attributes) {
		this.attributes = attributes;
	}
		
}
