package com.serpics.jaxrs;

import java.util.List;

import javax.ws.rs.core.Response;

import org.springframework.data.domain.Page;

import com.serpics.catalog.facade.data.CategoryData;
import com.serpics.catalog.facade.data.ProductData;

public interface CategoryRestService {
	
	public Response createParent(CategoryData category, Long parentId);
	public Response create(CategoryData category);
	public Response addParent(Long childId, Long parentId);
	public List<CategoryData> getChild(Long parentId);
	public CategoryData getCategoryById(Long categoryId);
	public CategoryData getCategoryByCode(String categoryId);
	public Response update(CategoryData category);
	public Response delete(Long categoryId);
	public Page<CategoryData> findAll(int page, int size);
	public List<CategoryData> getTop();
	
}
