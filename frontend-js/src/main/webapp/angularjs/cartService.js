 var app = angular.module("cart.service", ['serpics.config'])
/**
 * cart service to handler rest call to cart service
 */
app.service("cartService", function( $http, $q, serpicsServices, URL, $cookies,COOKIE_EXPIRES) {
	
	var endpoint = '/jax-rs/cartService/';
	 
	    /** Return public API. (like java interface) **/
	  	var service =   ({
	  		getCurrentCart		: getCurrentCart,
	  		cartAdd				: cartAdd,
	  		cartUpdate			: cartUpdate,
	  		deleteItem			: deleteItem,
	  		addBillingAddress	: addBillingAddress,
	  		addShippingAddress	: addShippingAddress,
	  		
	    });                
	    return service
	
	    /** public methods**/
	    
	    /**
	     * @return 
	     */
	    function getCurrentCart() {
	    	var serviceSSID = serpicsServices;
	    	return $q(function(resolve, reject) {
	    		
	    		serviceSSID.getSessionId().then(function(sessionId){
	    			console.log("cartService getCurrentCart() ssid nel promise "+sessionId) ;
	    			$http({
			             method: 'GET',
			             url: URL + endpoint , 
			             headers: {
			             	'ssid': sessionId
			            }
			          }).then(handleSuccess, handleError).then(resolve, reject);
	    		});
	    	});
	    }
	    
	    /**
	     * @param sku 					sku to send
  	     * @param quantity 		        quantity to send
	     * @return 
	     */
	    function cartAdd(sku ,quantity) {
	    	var serviceSSID = serpicsServices;
	    	return $q(function(resolve, reject) {
	    		
	    		serviceSSID.getSessionId().then(function(sessionId){
	    			console.log("cartService cartAdd(sku ,quantity) ssid nel promise "+sessionId) ;
	    			$http({
			             method: 'POST',
			             url: URL + endpoint + '?sku=' + sku + '&qty='+quantity, 
			             headers: {
			             	'ssid': sessionId,
			             	'Content-Type': 'application/x-www-form-urlencoded'
			            }
			          }).then(handleSuccess, handleError).then(resolve, reject);
	    		});
	    	});
	    }
	    
	   
	    /**
	     * @param cartItem
	     * @return 
	     */
	    function cartUpdate( cartItem ) {
	    	var serviceSSID = serpicsServices;
	    	return $q(function(resolve, reject) {
	    		
	    		serviceSSID.getSessionId().then(function(sessionId){
	    			console.log("cartService cartUpdate(cartItem) ssid nel promise "+sessionId) ;
	    			$http({
			             method: 'PUT',
			             url: URL + endpoint,
			             data: cartItem,
			             headers: {
			             	'ssid': sessionId,
			             	'Content-Type': 'application/json'
			            }
			          }).then(handleSuccess, handleError).then(resolve, reject);
	    		});
	    	});
	    }
	    
	    /**
	     * @param itemId
	     * @return 
	     */
	    function deleteItem(itemId) {
	    	var serviceSSID = serpicsServices;
	    	return $q(function(resolve, reject) {
	    		
	    		serviceSSID.getSessionId().then(function(sessionId){
	    			console.log("cartService deleteItem(itemId) ssid nel promise "+sessionId) ;
	    			$http({
			             method: 'DELETE',
			             url: URL + endpoint + "?itemId=" + itemId,
			             headers: {
			             	'ssid': sessionId,
			             	'Content-Type': 'application/x-www-form-urlencoded'
			            }
			          }).then(handleSuccess, handleError).then(resolve, reject);
	    		});
	    	});
	    }
	    
	   
	    /**
	     * @param billingAddress
	     * @return 
	     */     
	    function addBillingAddress(billingAddress) {
	    	var serviceSSID = serpicsServices;
	    	return $q(function(resolve, reject) {
	    		
	    		serviceSSID.getSessionId().then(function(sessionId){
	    			console.log("cartService addBillingAddress(billingAddress) ssid nel promise "+sessionId) ;
	    			$http({
			             method: 'POST',
			             url: URL + endpoint + "address/billing",
			             data: billingAddress, 
			             headers: {
			             	'ssid': sessionId,
			             	'Content-Type': 'application/json'
			            }
			          }).then(handleSuccess, handleError).then(resolve, reject);
	    		});
	    	});
	    }
	    
	    /**
	     * @param shippingAddress
	     * @return 
	     */     
	    function addShippingAddress(shippingAddress) {
	    	var serviceSSID = serpicsServices;
	    	return $q(function(resolve, reject) {
	    		
	    		serviceSSID.getSessionId().then(function(sessionId){
	    			console.log("cartService addShippingAddress(shippingAddress) ssid nel promise "+sessionId) ;
	    			$http({
			             method: 'POST',
			             url: URL + endpoint + "address/shipping",
			             data: shippingAddress, 
			             headers: {
			             	'ssid': sessionId,
			             	'Content-Type': 'application/json'
			            }
			          }).then(handleSuccess, handleError).then(resolve, reject);
	    		});
	    	});
	    }
	           
	    /**
	     * private method.
	     * I transform the error response, unwrapping the application dta from
	     * the API response payload.
	     */                
	                          
	    
	    function handleError( response ) {
	        /**
	         * The API response from the server should be returned in a
	         * nomralized format. However, if the request was not handled by the
	         * server (or what not handles properly - ex. server error), then we
	         * may have to normalize it on our end, as best we can.
	         */ 
	        if (! angular.isObject( response.data ) || ! response.data.message ) {
	            return( $q.reject( "An unknown error occurred." ) );
	        }
	        /** Otherwise, use expected error message.**/
	        return( $q.reject( response.data.message ) );
	    }
	    /** 
	     * I transform the successful response, unwrapping the application data
	     *from the API response payload.                
	     */
	    function handleSuccess( response ) {
        	var serviceSSID = serpicsServices;
        	serviceSSID.setCookie('ssid',$cookies.get('ssid'),COOKIE_EXPIRES)  /** expire 20 minut **/
	        return( response.data.responseObject);
	    }
});