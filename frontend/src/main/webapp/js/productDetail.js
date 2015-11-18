/**
 * 
 */
jQuery(document).ready(function() {
	
	
	/** by delegate events **/
	jQuery(document).on("click", "[data-product-id]",function() {		
		  productId = $(this).data("product-id");			
				rest.executeGetProductById('productService/',productId,buildProductDetail,error)		
	});
	
	
	
})