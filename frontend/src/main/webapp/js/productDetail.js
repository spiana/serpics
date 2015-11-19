/**
 * @main  js for detail product template
 * @use   var 'rest' object from global scope for call rest service server side
 * @example rest.functionName(params) not instantiate new rest object such var rest = new RestClient()
 */

jQuery(document).ready(function() {
	
	rest.executeGetProduct('productService',"/",buildProductMenu,error)		

	/** function to handler ther image on modal**/
	handlerImageSlideOnModal()
		
	/** by delegate events **/
	jQuery(document).on("click", "i.fa-external-link",function() {		
		var productId = $(this).data('product-id')
		rest.executeGetProductById('productService/',productId,makeProductDetailOnModal,error)	
			
	});

	
})