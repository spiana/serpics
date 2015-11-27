package com.serpics.jaxrs;

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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.serpics.catalog.facade.CategoryFacade;
import com.serpics.catalog.facade.ProductFacade;
import com.serpics.catalog.facade.data.CategoryData;
import com.serpics.catalog.facade.data.PriceData;
import com.serpics.catalog.facade.data.ProductData;
import com.serpics.jaxrs.data.ApiRestResponse;
import com.serpics.jaxrs.data.ApiRestResponseStatus;

@Path("/productService")
@Transactional(readOnly = true)
public class ProductRestServiceImpl implements ProductRestService {

	@Autowired
	ProductFacade productFacade;

	@Autowired
	CategoryFacade categoryFacade;

	@Override
	@Consumes(MediaType.APPLICATION_JSON)
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{category}/{brand}")
	public Response insert(ProductData product, @PathParam("category") Long categoryId,
			@PathParam("brand") Long brandId) {
		
		Assert.notNull(product);
		ApiRestResponse<ProductData> apiRestResponse = new ApiRestResponse<ProductData>();
		ProductData productData = null;

		productData = productFacade.create(product, categoryId, brandId);

		apiRestResponse.setStatus(ApiRestResponseStatus.OK);
		apiRestResponse.setResponseObject(productData);
		return Response.ok(apiRestResponse).build();
	}

	@Override
	@Consumes(MediaType.APPLICATION_JSON)
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/category/{category}")
	public Response insertCategory(ProductData product, @PathParam("category") Long categoryId) {
		Assert.notNull(product);

		ApiRestResponse<ProductData> apiRestResponse = new ApiRestResponse<ProductData>();
		ProductData productData = null;

		productData = productFacade.createWithCategory(product, categoryId);

		apiRestResponse.setStatus(ApiRestResponseStatus.OK);
		apiRestResponse.setResponseObject(productData);
		return Response.ok(apiRestResponse).build();

	}

	@Override
	@Consumes(MediaType.APPLICATION_JSON)
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/brand/{brand}")
	public Response insertBrand(ProductData product, @PathParam("brand") Long brandId) {
		Assert.notNull(product);

		ProductData productData = null;
		ApiRestResponse<ProductData> apiRestResponse = new ApiRestResponse<ProductData>();

		productData = productFacade.createWithBrand(product, brandId);

		apiRestResponse.setStatus(ApiRestResponseStatus.OK);
		apiRestResponse.setResponseObject(productData);
		return Response.ok(apiRestResponse).build();
	}

	@Override
	@Consumes(MediaType.APPLICATION_JSON)
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response insert(ProductData product) {
		Assert.notNull(product);
		ProductData productData = null;
		ApiRestResponse<ProductData> apiRestResponse = new ApiRestResponse<ProductData>();
		
		productData = productFacade.create(product);
		
		apiRestResponse.setStatus(ApiRestResponseStatus.OK);
		apiRestResponse.setResponseObject(productData);
		return Response.ok(apiRestResponse).build();
	}

	@Override
	@Consumes(MediaType.APPLICATION_JSON)
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	public Response update(ProductData product) {
		Assert.notNull(product);
		ProductData productData = null;
		ApiRestResponse<ProductData> apiRestResponse = new ApiRestResponse<ProductData>();

		productData = productFacade.updateProduct(product);

		apiRestResponse.setStatus(ApiRestResponseStatus.OK);
		apiRestResponse.setResponseObject(productData);
		return Response.ok(apiRestResponse).build();

	}

	@Override
	@Consumes(MediaType.APPLICATION_JSON)
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{product}")
	public Response getProduct(@PathParam("product") Long productId) {

		// Assert.notNull(productId);

		ProductData productData = null;
		ApiRestResponse<ProductData> apiRestResponse = new ApiRestResponse<ProductData>();

		productData = productFacade.findById(productId);
		if (productData != null) {
			apiRestResponse.setStatus(ApiRestResponseStatus.OK);
			apiRestResponse.setResponseObject(productData);
			return Response.ok(apiRestResponse).build();
		} else {
			apiRestResponse.setStatus(ApiRestResponseStatus.ERROR);
			apiRestResponse.setMessage("ERROR, Product not found");
			return Response.status(404).entity(apiRestResponse).build();
		}

	}

	@Override
	@Consumes(MediaType.APPLICATION_JSON)
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{product}")
	public Response delete(@PathParam("product") Long productId) {
		Assert.notNull(productId);
		ApiRestResponse<ProductData> apiRestResponse = new ApiRestResponse<ProductData>();
		
		productFacade.deleteProduct(productId);
		
		apiRestResponse.setStatus(ApiRestResponseStatus.OK);
		return Response.ok(apiRestResponse).build();
	}

	@Override
	@Consumes(MediaType.APPLICATION_JSON)
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("getCategory/{product}")
	public Response getCategory(@PathParam("product") Long productId) {
		Assert.notNull(productId);

		ApiRestResponse<List<CategoryData>> apiRestResponse = new ApiRestResponse<List<CategoryData>>();

		ProductData product = productFacade.findById(productId);
		if (product != null) {
			apiRestResponse.setStatus(ApiRestResponseStatus.OK);
			apiRestResponse.setResponseObject(productFacade.getParentCategory(product));
			return Response.ok(apiRestResponse).build();
		} else {
			apiRestResponse.setStatus(ApiRestResponseStatus.ERROR);
			apiRestResponse.setMessage("ERROR, Product not found");
			return Response.status(404).entity(apiRestResponse).build();
		}
	}

	@Override
	@Consumes(MediaType.APPLICATION_JSON)
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("addBrand/{product}/{brand}")
	public Response addBrand(@PathParam("product") Long productId, @PathParam("brand") Long brandId) {

		Assert.notNull(productId);
		Assert.notNull(brandId);
		ApiRestResponse<ProductData> apiRestResponse = new ApiRestResponse<ProductData>();

		productFacade.addBrand(productId, brandId);

		apiRestResponse.setStatus(ApiRestResponseStatus.OK);
		return Response.ok(apiRestResponse).build();
	}

	@Override
	@Consumes(MediaType.APPLICATION_JSON)
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("addCategory/{product}/{category}")
	public Response addCategory(@PathParam("product") Long productId, @PathParam("category") Long categoryId) {

		Assert.notNull(productId);
		Assert.notNull(categoryId);
		ApiRestResponse<ProductData> apiRestResponse = new ApiRestResponse<ProductData>();

		productFacade.addEntryCategoryParent(productId, categoryId);

		apiRestResponse.setStatus(ApiRestResponseStatus.OK);
		return Response.ok(apiRestResponse).build();
	}

	@Override
	@Consumes(MediaType.APPLICATION_JSON)
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("addPrice/{product}")
	public Response addPrice(@PathParam("product") Long productId, PriceData price) {

		Assert.notNull(productId);
		Assert.notNull(price);

		ApiRestResponse<ProductData> apiRestResponse = new ApiRestResponse<ProductData>();

		productFacade.addPrice(productId, price);

		apiRestResponse.setStatus(ApiRestResponseStatus.OK);
		return Response.ok(apiRestResponse).build();
	}

	@Override
	@Consumes(MediaType.APPLICATION_JSON)
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("byCode/{product}")
	public Response getProductByName(@PathParam("product") String name) {
		Assert.notNull(name);
		ProductData productData = null;
		ApiRestResponse<ProductData> apiRestResponse = new ApiRestResponse<ProductData>();

		productData = productFacade.findByName(name);

		if (productData != null) {
			apiRestResponse.setStatus(ApiRestResponseStatus.OK);
			apiRestResponse.setResponseObject(productData);
			return Response.ok(apiRestResponse).build();
		} else {
			apiRestResponse.setStatus(ApiRestResponseStatus.ERROR);
			apiRestResponse.setMessage("ERROR, Product not found");
			return Response.status(404).entity(apiRestResponse).build();
		}
	}

	@Override
	@Consumes(MediaType.APPLICATION_JSON)
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response findAll(@QueryParam("page") @DefaultValue("0") int page,
			@QueryParam("size") @DefaultValue("10") int size) {

		ApiRestResponse<Page<ProductData>> apiRestResponse = new ApiRestResponse<Page<ProductData>>();

		apiRestResponse.setStatus(ApiRestResponseStatus.OK);
		apiRestResponse.setResponseObject(productFacade.listProduct(new PageRequest(page, size)));
		return Response.ok(apiRestResponse).build();
	}

	@Override
	@Consumes(MediaType.APPLICATION_JSON)
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{category}")
	public Response findByCategory(@PathParam("category") Long categoryId,
			@QueryParam("page") @DefaultValue("0") int page, @QueryParam("size") @DefaultValue("10") int size) {

		ApiRestResponse<Page<ProductData>> apiRestResponse = new ApiRestResponse<Page<ProductData>>();

		apiRestResponse.setStatus(ApiRestResponseStatus.OK);
		apiRestResponse.setResponseObject(productFacade.listProductByCategory(categoryId, new PageRequest(page, size)));
		return Response.ok(apiRestResponse).build();
	}

	@Override
	@Consumes(MediaType.APPLICATION_JSON)
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("pageBrand/{brand}")
	public Response findByBrand(@PathParam("brand") Long brandId, @QueryParam("page") @DefaultValue("0") int page,
			@QueryParam("size") @DefaultValue("10") int size) {

		ApiRestResponse<Page<ProductData>> apiRestResponse = new ApiRestResponse<Page<ProductData>>();

		apiRestResponse.setStatus(ApiRestResponseStatus.OK);
		apiRestResponse.setResponseObject(productFacade.listProductByBrand(brandId, new PageRequest(page, size)));
		return Response.ok(apiRestResponse).build();
	}

}