/**
 * 
 */
jQuery(document).ready(function() {
	
	
	/** call rest on startup **/
	rest.executeGetProductById('productService/',"8",buildProductDetail,error)			
	
})