package com.serpics.jaxrs;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.serpics.catalog.facade.CategoryFacade;
import com.serpics.catalog.facade.ProductFacade;
import com.serpics.catalog.facade.data.CategoryData;
import com.serpics.catalog.facade.data.PriceData;
import com.serpics.catalog.facade.data.ProductData;

@Path("/productService")
@Transactional(readOnly=true)
public class ProductRestServiceImpl implements ProductRestService{

	@Autowired
	ProductFacade productFacade;
	
	@Autowired
	CategoryFacade categoryFacade;
	
	@Override
	@Consumes(MediaType.APPLICATION_JSON)
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{category}/{brand}")
	public Response insert(ProductData product, @PathParam("category") Long categoryId, @PathParam("brand") Long brandId){
		Assert.notNull(product);
		ProductData productData = null;
		productData = productFacade.create(product, categoryId, brandId);
		return Response.ok(productData).build();
	}
	
	@Override
	@Consumes(MediaType.APPLICATION_JSON)
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/category/{category}")
	public Response insertCategory(ProductData product, @PathParam("category") Long categoryId){
		Assert.notNull(product);
		ProductData productData = null;
		productData = productFacade.createWithCategory(product, categoryId);
		return Response.ok(productData).build();
	}
	
	@Override
	@Consumes(MediaType.APPLICATION_JSON)
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/brand/{brand}")
	public Response insertBrand(ProductData product, @PathParam("brand") Long brandId){
		Assert.notNull(product);
		ProductData productData = null;
		productData = productFacade.createWithBrand(product, brandId);
		return Response.ok(productData).build();
	}
	
	@Override
	@Consumes(MediaType.APPLICATION_JSON)
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response insert(ProductData product){
		Assert.notNull(product);
		ProductData productData = null;
		productData = productFacade.create(product);
		return Response.ok(productData).build();
	}
	
	@Override
	@Consumes(MediaType.APPLICATION_JSON)
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	public Response update(ProductData product){
		Assert.notNull(product);
		ProductData productData = null;
		productData = productFacade.updateProduct(product);
		return Response.ok(productData).build();
	}
	
	@Override
	@Consumes(MediaType.APPLICATION_JSON)
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{product}")
	public ProductData getProduct(@PathParam("product") Long productId){
		Assert.notNull(productId);
		return productFacade.findById(productId);
	}
	
	@Override
	@Consumes(MediaType.APPLICATION_JSON)
	@DELETE
	@Path("/{product}")
	public Response delete(@PathParam("product") Long productId){
		Assert.notNull(productId);
		productFacade.deleteProduct(productId);
		return Response.ok().build();
	}
	
	@Override
	@Consumes(MediaType.APPLICATION_JSON)
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("getCategory/{product}")
	public List<CategoryData> getCategory(@PathParam("product") Long productId){
		Assert.notNull(productId);
		ProductData product = productFacade.findById(productId);
		if (product != null){
		return productFacade.getParentCategory(product);
		} else {
			return new ArrayList<CategoryData>();
		}
	}
	
	@Override
	@Consumes(MediaType.APPLICATION_JSON)
	@POST
	@Path("addBrand/{product}/{brand}")
	public Response addBrand(@PathParam("product") Long productId, @PathParam("brand") Long brandId){
		Assert.notNull(productId);
		Assert.notNull(brandId);
		productFacade.addBrand(productId, brandId);
		return Response.ok().build();
	}
	
	@Override
	@Consumes(MediaType.APPLICATION_JSON)
	@POST
	@Path("addCategory/{product}/{category}")
	public Response addCategory(@PathParam("product") Long productId, @PathParam("category") Long categoryId){
		Assert.notNull(productId);
		Assert.notNull(categoryId);
		productFacade.addEntryCategoryParent(productId, categoryId);
		return Response.ok().build();
	}
	
	@Override
	@Consumes(MediaType.APPLICATION_JSON)
	@POST
	@Path("addPrice/{product}")
	public Response addPrice(@PathParam("product") Long productId, PriceData price){
		Assert.notNull(productId);
		Assert.notNull(price);
		productFacade.addPrice(productId, price);
		return Response.ok().build();
	}
	
	@Override
	@Consumes(MediaType.APPLICATION_JSON)
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("byCode/{product}")
	public ProductData getProductByName(@PathParam("product") String name){
		Assert.notNull(name);
		return productFacade.findByName(name);
	}
	
	@Override
	@Consumes(MediaType.APPLICATION_JSON)
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Page<ProductData> findAll(@QueryParam("page") @DefaultValue("0") int page, @QueryParam("size" ) @DefaultValue("10") int size){
		Pageable pageable = new PageRequest(page, size);
		return productFacade.listProduct(pageable);
	}
	
	@Override
	@Consumes(MediaType.APPLICATION_JSON)
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("pageCategory/{category}")
	public Page<ProductData> findByCategory(@PathParam("category") Long categoryId, @QueryParam("page") @DefaultValue("0") int page, @QueryParam("size" ) @DefaultValue("10") int size){
		Pageable pageable = new PageRequest(page, size);
		return productFacade.listProductByCategory(categoryId, pageable);
	}
	
	@Override
	@Consumes(MediaType.APPLICATION_JSON)
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("pageBrand/{brand}")
	public Page<ProductData> findByBrand(@PathParam("brand") Long brandId, @QueryParam("page") @DefaultValue("0") int page, @QueryParam("size" ) @DefaultValue("10") int size){
		Pageable pageable = new PageRequest(page, size);
		return productFacade.listProductByBrand(brandId, pageable);
	}
	
}