package com.serpics.jaxrs;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.qmino.miredot.annotations.ReturnType;
import com.serpics.catalog.facade.BrandFacade;
import com.serpics.catalog.facade.CategoryFacade;
import com.serpics.catalog.facade.ProductFacade;
import com.serpics.catalog.facade.data.BrandData;
import com.serpics.catalog.facade.data.CategoryData;
import com.serpics.catalog.facade.data.PriceData;
import com.serpics.catalog.facade.data.ProductData;
import com.serpics.jaxrs.data.ApiRestResponse;
import com.serpics.jaxrs.data.ApiRestResponseStatus;
import com.serpics.jaxrs.data.PriceDataRequest;
import com.serpics.jaxrs.data.ProductDataRequest;

@Path("/productService")
@Transactional(readOnly = true)
public class ProductRestServiceImpl implements ProductRestService {
	
	Logger LOG = LoggerFactory.getLogger(ProductRestServiceImpl.class);

	@Autowired
	ProductFacade productFacade;

	@Autowired
	CategoryFacade categoryFacade;
	
	@Autowired
	BrandFacade brandFacade;
	
    /**
     * This method inserts a product, with category and brand, into catalog.
     * @summary  Method: insert(ProductDataRequest productDataRequest,Long categoryId,Long brandId)
     * @param 	productDataRequest The product to insert
     * @param 	categoryId The category id of product
     * @param 	brandId The brand of product
     * @param ssid The sessionId for the store authentication
     * @return Response		object type: apiRestResponse
     */
	@Override
	@Consumes(MediaType.APPLICATION_JSON)
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("product/{categoryId}/{brandId}")
	@ReturnType("com.serpics.jaxrs.data.ApiRestResponse<com.serpics.catalog.facade.data.ProductData>")
	public Response insert(ProductDataRequest productDataRequest, @PathParam("categoryId") Long categoryId,
			@PathParam("brandId") Long brandId, @HeaderParam(value = "ssid") String ssid) {
		

		Assert.notNull(categoryId);
		Assert.notNull(brandId);
		
		ApiRestResponse<ProductData> apiRestResponse = new ApiRestResponse<ProductData>();
		ProductData productData = new ProductData();
		ResponseBuilder responseBuilder = null;
		
		try{
			BeanUtils.copyProperties(productDataRequest, productData,new String[]{"brandName"});
			Assert.notNull(productData);
			productData = productFacade.create(productData, categoryId, brandId);
			apiRestResponse.setStatus(ApiRestResponseStatus.OK);
			apiRestResponse.setResponseObject(productData);
			responseBuilder = Response.ok();
		}
		catch(BeansException e){
			LOG.error("Error converting bean",e);
			apiRestResponse.setStatus(ApiRestResponseStatus.ERROR);
			apiRestResponse.setMessage("Error Converting Request Bean");
			responseBuilder = Response.status(500);
		}

		return responseBuilder.entity(apiRestResponse).build();

	}

    /**
     * This method inserts a product, with category, into catalog.
     * @summary  Method: insertCategory(ProductDataRequest productDataRequest,Long categoryId)
     * @param 	productDataRequest The product to insert
     * @param 	categoryId The category id of product
     * @param ssid The sessionId for the store authentication
     * @return Response		object type: apiRestResponse
     */
	@Override
	@Consumes(MediaType.APPLICATION_JSON)
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/category/{categoryId}")
	@ReturnType("com.serpics.jaxrs.data.ApiRestResponse<com.serpics.catalog.facade.data.ProductData>")
	public Response insertCategory(ProductDataRequest productDataRequest, @PathParam("categoryId") Long categoryId, @HeaderParam(value = "ssid") String ssid) {
		
		ApiRestResponse<ProductData> apiRestResponse = new ApiRestResponse<ProductData>();
		ProductData productData = new ProductData();
		ResponseBuilder responseBuilder = null;
		
		try{
			BeanUtils.copyProperties(productDataRequest, productData,new String[]{"brandName"});
			if(!StringUtils.isEmpty(productDataRequest.getCode())){
				BrandData brandData = brandFacade.findBrandByCode(productDataRequest.getCode());
				productData.setBrand(brandData);
			}
			Assert.notNull(productData);
			productData = productFacade.createWithCategory(productData, categoryId);
			apiRestResponse.setStatus(ApiRestResponseStatus.OK);
			apiRestResponse.setResponseObject(productData);
			responseBuilder = Response.ok();
		}
		catch(BeansException e){
			LOG.error("Error converting bean",e);
			apiRestResponse.setStatus(ApiRestResponseStatus.ERROR);
			apiRestResponse.setMessage("Error Converting Request Bean");
			responseBuilder = Response.status(500);
		}

		return responseBuilder.entity(apiRestResponse).build();

	}

    /**
     * This method inserts a product, with brand, into catalog.
     * @summary  Method: insertBrand(ProductDataRequest productDataRequest,Long brandId)
     * @param 	productDataRequest The product to insert
     * @param 	brandId The brand Id of product
     * @param ssid The sessionId for the store authentication
     * @return Response		object type: apiRestResponse
     */
	@Override
	@Consumes(MediaType.APPLICATION_JSON)
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/brand/{brandId}")
	@ReturnType("com.serpics.jaxrs.data.ApiRestResponse<com.serpics.catalog.facade.data.ProductData>")
	public Response insertBrand(ProductDataRequest productDataRequest, @PathParam("brandId") Long brandId, @HeaderParam(value = "ssid") String ssid) {
		ApiRestResponse<ProductData> apiRestResponse = new ApiRestResponse<ProductData>();
		ProductData productData = new ProductData();
		ResponseBuilder responseBuilder = null;
		
		try{
			BeanUtils.copyProperties(productDataRequest, productData,new String[]{"brandName"});
			Assert.notNull(productData);
			productData = productFacade.createWithBrand(productData, brandId);
			apiRestResponse.setStatus(ApiRestResponseStatus.OK);
			apiRestResponse.setResponseObject(productData);
			responseBuilder = Response.ok();
		}
		catch(BeansException e){
			LOG.error("Error converting bean",e);
			apiRestResponse.setStatus(ApiRestResponseStatus.ERROR);
			apiRestResponse.setMessage("Error Converting Request Bean");
			responseBuilder = Response.status(500);
		}

		return responseBuilder.entity(apiRestResponse).build();

	}

    /**
     * This method inserts a product into catalog.
     * @summary  Method: insertBrand(ProductDataRequest productDataRequest)
     * @param 	productDataRequest The product to insert
     * @param ssid The sessionId for the store authentication
     * @return Response		object type: apiRestResponse
     */
	@Override
	@Consumes(MediaType.APPLICATION_JSON)
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@ReturnType("com.serpics.jaxrs.data.ApiRestResponse<com.serpics.catalog.facade.data.ProductData>")
	public Response insert(ProductDataRequest productDataRequest, @HeaderParam(value = "ssid") String ssid) {
		
		ApiRestResponse<ProductData> apiRestResponse = new ApiRestResponse<ProductData>();
		ProductData productData = new ProductData();
		ResponseBuilder responseBuilder = null;
		
		try{
			BeanUtils.copyProperties(productDataRequest, productData,new String[]{"brandName"});
			if(!StringUtils.isEmpty(productDataRequest.getCode())){
				BrandData brandData = brandFacade.findBrandByCode(productDataRequest.getCode());
				productData.setBrand(brandData);
			}
			Assert.notNull(productData);
			productData = productFacade.create(productData);
			apiRestResponse.setStatus(ApiRestResponseStatus.OK);
			apiRestResponse.setResponseObject(productData);
			responseBuilder = Response.ok();
		}
		catch(BeansException e){
			LOG.error("Error converting bean",e);
			apiRestResponse.setStatus(ApiRestResponseStatus.ERROR);
			apiRestResponse.setMessage("Error Converting Request Bean");
			responseBuilder = Response.status(500);
		}

		return responseBuilder.entity(apiRestResponse).build();

	}

    /**
     * This method updates a product.
     * @summary  Method: update(ProductDataRequest productDataRequest)
     * @param 	productDataRequest The product to update
     * @param ssid The sessionId for the store authentication
     * @return Response		object type: apiRestResponse
     */
	@Override
	@Consumes(MediaType.APPLICATION_JSON)
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@ReturnType("com.serpics.jaxrs.data.ApiRestResponse<com.serpics.catalog.facade.data.ProductData>")
	public Response update(ProductDataRequest productDataRequest, @HeaderParam(value = "ssid") String ssid) {
		
		ApiRestResponse<ProductData> apiRestResponse = new ApiRestResponse<ProductData>();
		ProductData productData = new ProductData();
		ResponseBuilder responseBuilder = null;
		
		try{
			BeanUtils.copyProperties(productDataRequest, productData,new String[]{"brandName"});
			if(!StringUtils.isEmpty(productDataRequest.getCode())){
				BrandData brandData = brandFacade.findBrandByCode(productDataRequest.getCode());
				productData.setBrand(brandData);
			}
			Assert.notNull(productData);
			productData = productFacade.updateProduct(productData);
			apiRestResponse.setStatus(ApiRestResponseStatus.OK);
			apiRestResponse.setResponseObject(productData);
			responseBuilder = Response.ok();
		}
		catch(BeansException e){
			LOG.error("Error converting bean",e);
			apiRestResponse.setStatus(ApiRestResponseStatus.ERROR);
			apiRestResponse.setMessage("Error Converting Request Bean");
			responseBuilder = Response.status(500);
		}

		return responseBuilder.entity(apiRestResponse).build();

	}

    /**
     * This method gets a product by productId.
     * @summary  Method: getProduct(Long productId)
     * @param 	productId The product Id to get
     * @param ssid The sessionId for the store authentication
     * @return Response		object type: apiRestResponse
     * @statuscode 200 Product found
     * @statuscode 404 Product not found
     */
	@Override
	@Consumes(MediaType.APPLICATION_JSON)
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/product/{productId}")
	@ReturnType("com.serpics.jaxrs.data.ApiRestResponse<com.serpics.catalog.facade.data.ProductData>")
	public Response getProduct(@PathParam("productId") Long productId, @HeaderParam(value = "ssid") String ssid) {

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

    /**
     * This method deletes a product by productId.
     * @summary  Method: delete(Long productId)
     * @param 	productId The product Id to delete.
     * @param ssid The sessionId for the store authentication
     * @return Response		object type: apiRestResponse
     */
	@Override
	@Consumes(MediaType.APPLICATION_JSON)
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/product/{productId}")
	@ReturnType("com.serpics.jaxrs.data.ApiRestResponse<com.serpics.catalog.facade.data.ProductData>")
	public Response delete(@PathParam("productId") Long productId, @HeaderParam(value = "ssid") String ssid) {
		Assert.notNull(productId);
		ApiRestResponse<ProductData> apiRestResponse = new ApiRestResponse<ProductData>();
		
		productFacade.deleteProduct(productId);
		
		apiRestResponse.setStatus(ApiRestResponseStatus.OK);
		return Response.ok(apiRestResponse).build();
	}

    /**
     * This method gets a list of category of a product by productId.
     * @summary  Method: getCategory(Long productId)
     * @param 	productId The product Id to search
     * @param ssid The sessionId for the store authentication
     * @return Response		object type: apiRestResponse
     * @statuscode 200 Product found
     * @statuscode 404 Product not found
     */
	@Override
	@Consumes(MediaType.APPLICATION_JSON)
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("category/{productId}")
	@ReturnType("com.serpics.jaxrs.data.ApiRestResponse<java.util.List<com.serpics.catalog.facade.data.CategoryData>>")
	public Response getCategory(@PathParam("productId") Long productId, @HeaderParam(value = "ssid") String ssid) {
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

    /**
     * This method adds a brand to a product.
     * @summary  Method: addBrand(Long productId,Long brandId)
     * @param 	productId The product Id to add brand
     * @param 	brandId The brand Id to add
     * @param ssid The sessionId for the store authentication
     * @return Response		object type: apiRestResponse
     */
	@Override
	@Consumes(MediaType.APPLICATION_JSON)
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("brand/{productId}/{brandId}")
	@ReturnType("com.serpics.jaxrs.data.ApiRestResponse<com.serpics.catalog.facade.data.ProductData>")
	public Response addBrand(@PathParam("productId") Long productId, @PathParam("brandId") Long brandId, @HeaderParam(value = "ssid") String ssid) {

		Assert.notNull(productId);
		Assert.notNull(brandId);
		ApiRestResponse<ProductData> apiRestResponse = new ApiRestResponse<ProductData>();

		productFacade.addBrand(productId, brandId);

		apiRestResponse.setStatus(ApiRestResponseStatus.OK);
		return Response.ok(apiRestResponse).build();
	}

    /**
     * This method adds a category to a product.
     * @summary  Method: addCategory(Long productId,Long categoryId)
     * @param 	productId The product Id to add category
     * @param 	categoryId The category Id to add
     * @param ssid The sessionId for the store authentication
     * @return Response		object type: apiRestResponse
     */
	@Override
	@Consumes(MediaType.APPLICATION_JSON)
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("category/{productId}/{categoryId}")
	@ReturnType("com.serpics.jaxrs.data.ApiRestResponse<com.serpics.catalog.facade.data.ProductData>")
	public Response addCategory(@PathParam("productId") Long productId, @PathParam("categoryId") Long categoryId, @HeaderParam(value = "ssid") String ssid) {

		Assert.notNull(productId);
		Assert.notNull(categoryId);
		ApiRestResponse<ProductData> apiRestResponse = new ApiRestResponse<ProductData>();

		productFacade.addEntryCategoryParent(productId, categoryId);

		apiRestResponse.setStatus(ApiRestResponseStatus.OK);
		return Response.ok(apiRestResponse).build();
	}

    /**
     * This method adds a price to a product.
     * @summary  Method: addPrice(Long productId, PriceDataRequest priceDataRequest)
     * @param 	productId The product Id to add price
     * @param 	priceDataRequest The price Id to add
     * @param ssid The sessionId for the store authentication
     * @return Response		object type: apiRestResponse
     */
	@Override
	@Consumes(MediaType.APPLICATION_JSON)
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("addPrice/{productId}")
	@ReturnType("com.serpics.jaxrs.data.ApiRestResponse<com.serpics.catalog.facade.data.ProductData>")
	public Response addPrice(@PathParam("productId") Long productId, PriceDataRequest priceDataRequest, @HeaderParam(value = "ssid") String ssid) {

		Assert.notNull(productId);
		
		ApiRestResponse<ProductData> apiRestResponse = new ApiRestResponse<ProductData>();
		PriceData priceData = new PriceData();
		ResponseBuilder responseBuilder = null;
		
		try{
			BeanUtils.copyProperties(priceDataRequest, priceData);
			Assert.notNull(priceData);
			productFacade.addPrice(productId, priceData);
			apiRestResponse.setStatus(ApiRestResponseStatus.OK);
			responseBuilder = Response.ok();
		}
		catch(BeansException e){
			LOG.error("Error converting bean",e);
			apiRestResponse.setStatus(ApiRestResponseStatus.ERROR);
			apiRestResponse.setMessage("Error Converting Request Bean");
			responseBuilder = Response.status(500);
		}

		return responseBuilder.entity(apiRestResponse).build();

	}

	
    /**
     * This method gets a product by name.
     * @summary  Method: getProductByName(String name)
     * @param 	productName The name of product to search
     * @return Response		object type: apiRestResponse
     * @param ssid The sessionId for the store authentication
     * @statuscode 200 Product found
     * @statuscode 404 Product not found
     */
	@Override
	@Consumes(MediaType.APPLICATION_JSON)
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("name/{productName}")
	@ReturnType("com.serpics.jaxrs.data.ApiRestResponse<com.serpics.catalog.facade.data.ProductData>")
	public Response getProductByName(@PathParam("productName") String productName, @HeaderParam(value = "ssid") String ssid) {
		Assert.notNull(productName);
		ProductData productData = null;
		ApiRestResponse<ProductData> apiRestResponse = new ApiRestResponse<ProductData>();

		productData = productFacade.findByName(productName);

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

    /**
     * This method gets all products.
     * @summary  Method: findAll(int page, int size)
     * @param 	page The number of page requested 
     * @param 	size The size of product to display in a page
     * @param ssid The sessionId for the store authentication
     * @return Response		object type: apiRestResponse
     */
	@Override
	@Consumes(MediaType.APPLICATION_JSON)
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@ReturnType("com.serpics.jaxrs.data.ApiRestResponse<org.springframework.data.domain.Page<com.serpics.catalog.facade.data.ProductData>>")
	public Response findAll(@QueryParam("page") @DefaultValue("0") int page,
			@QueryParam("size") @DefaultValue("10") int size, @HeaderParam(value = "ssid") String ssid) {

		ApiRestResponse<Page<ProductData>> apiRestResponse = new ApiRestResponse<Page<ProductData>>();

		apiRestResponse.setStatus(ApiRestResponseStatus.OK);
		apiRestResponse.setResponseObject(productFacade.listProduct(new PageRequest(page, size)));
		return Response.ok(apiRestResponse).build();
	}

    /**
     * This method gets all products of a given category.
     * @summary  Method: findByCategory(Long categoryId, int page, int size)
     * @param 	categoryId The category Id to search
     * @param 	page The number of page requested
     * @param 	size The size of product to display in a page
     * @param ssid The sessionId for the store authentication
     * @return Response		object type: apiRestResponse
     */
	@Override
	@Consumes(MediaType.APPLICATION_JSON)
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("pageCategory/{categoryId}")
	@ReturnType("com.serpics.jaxrs.data.ApiRestResponse<org.springframework.data.domain.Page<com.serpics.catalog.facade.data.ProductData>>")
	public Response findByCategory(@PathParam("categoryId") Long categoryId,
			@QueryParam("page") @DefaultValue("0") int page, @QueryParam("size") @DefaultValue("10") int size, @HeaderParam(value = "ssid") String ssid) {

		ApiRestResponse<Page<ProductData>> apiRestResponse = new ApiRestResponse<Page<ProductData>>();

		apiRestResponse.setStatus(ApiRestResponseStatus.OK);
		apiRestResponse.setResponseObject(productFacade.listProductByCategory(categoryId, new PageRequest(page, size)));
		return Response.ok(apiRestResponse).build();
	}

    /**
     * This method gets all products of a given brand.
     * @summary  Method: findByBrand(Long brandId,int page,int size)
     * @param 	brandId The brand Id to search
     * @param 	page The number of page requested
     * @param 	size The size of product to display in a page
     * @param ssid The sessionId for the store authentication
     * @return Response		object type: apiRestResponse
     */
	@Override
	@Consumes(MediaType.APPLICATION_JSON)
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("pageBrand/{brand}")
	@ReturnType("com.serpics.jaxrs.data.ApiRestResponse<org.springframework.data.domain.Page<com.serpics.catalog.facade.data.ProductData>>")
	public Response findByBrand(@PathParam("brand") Long brandId, @QueryParam("page") @DefaultValue("0") int page,
			@QueryParam("size") @DefaultValue("10") int size, @HeaderParam(value = "ssid") String ssid) {

		ApiRestResponse<Page<ProductData>> apiRestResponse = new ApiRestResponse<Page<ProductData>>();

		apiRestResponse.setStatus(ApiRestResponseStatus.OK);
		apiRestResponse.setResponseObject(productFacade.listProductByBrand(brandId, new PageRequest(page, size)));
		return Response.ok(apiRestResponse).build();
	}
	
    /**
     * This method search products by code, by name and description.
     * @summary  Method: findBySearch(String searchText, int page,int size)
     * @param 	searchText The text to search into products
     * @param 	page The number of page requested
     * @param 	size The size of product to display in a page
     * @param ssid The sessionId for the store authentication
     * @return Response		object type: apiRestResponse
     */
	@Override
	@Consumes(MediaType.APPLICATION_JSON)
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/search")
	@ReturnType("com.serpics.jaxrs.data.ApiRestResponse<org.springframework.data.domain.Page<com.serpics.catalog.facade.data.ProductData>>")
	public Response findBySearch(@QueryParam("searchText") @DefaultValue("") String searchText, @QueryParam("page") @DefaultValue("0") int page,
			@QueryParam("size") @DefaultValue("10") int size, @HeaderParam(value = "ssid") String ssid) {

		ApiRestResponse<Page<ProductData>> apiRestResponse = new ApiRestResponse<Page<ProductData>>();

		apiRestResponse.setStatus(ApiRestResponseStatus.OK);
		apiRestResponse.setResponseObject(productFacade.searchProducts(new PageRequest(page, size), searchText));
		return Response.ok(apiRestResponse).build();
	}

}