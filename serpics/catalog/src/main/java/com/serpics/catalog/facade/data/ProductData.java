package com.serpics.catalog.facade.data;

import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;



@JsonPropertyOrder({"sort","numberOfElements","first","last","size","number","content","medias","buyable","dowloadable","totalPages","totalElements" })
public class ProductData  extends AbstractProductData{
	
	protected BrandData brand;
	protected boolean published;
	
	protected Set<CategoryData> categories;
	
	protected List<ProductVariantData> variants;

	protected String productType;
	
	public BrandData getBrand() {
		return brand;
	}

	public void setBrand(BrandData brand) {
		this.brand = brand;
	}

	public boolean isPublished() {
		return published;
	}

	public void setPublished(boolean published) {
		this.published = published;
	}

	public Set<CategoryData> getCategories() {
		return categories;
	}

	public void setCategories(Set<CategoryData> categories) {
		this.categories = categories;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public List<ProductVariantData> getVariants() {
		return variants;
	}

	public void setVariants(List<ProductVariantData> variants) {
		this.variants = variants;
	}
	
}
