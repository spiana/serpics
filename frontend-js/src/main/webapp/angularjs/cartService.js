 var app = angular.module("cart.service", ['serpics.config'])
/**
 * cart service to handler rest call to cart service
 */
app.service("cartService", function( $http, $q, authManagerService, URL) {
	
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
	     * @param endpoint
	     * @param sessionId               
	     * @param data
	     * @return 
	     */
	    function getCurrentCart(endpoint, sessionId, data ) {
	        var request = $http({
	            method: 'GET',
	            url: URL + endpoint +   'getCurrentCart/' , 
	            headers: {
	            	'ssid': sessionId
	            }
	          });
	        return( request.then( handleSuccess, handleError ) );
	    }
	    
	    /**
	     * @param endpoint
	     * @param sessionId               
	     * @param data
	     * @return 
	     */
	    function cartAdd( endpoint,sessionId, data ) {
	        var request = $http({
	            method: 'POST',
	            url: URL +  endpoint +    'cartAdd',
	            headers: {
	            	'ssid': sessionId
	            },   
	            data: data
	          });
	        return( request.then( handleSuccess, handleError ) );
	    }
	    
	   
	    /**
	     * @param endpoint
	     * @param sessionId               
	     * @param data
	     * @return 
	     */
	    function cartUpdate(endpoint,sessionId, data ) {
	        var request = $http({
	            method: 'PUT',
	            url: URL + endpoint +   'cartUpdate',
	            headers: {
	            	'ssid': sessionId
	            },   
	            data: data
	          });
	        return( request.then( handleSuccess, handleError ) );
	    }
	    
	    /**
	     * @param endpoint
	     * @param sessionId                     
	     * @param data
	     * @return 
	     */
	    function deleteItem(endpoint,sessionId,data) {
	        var request = $http({
	            method: 'DELETE',
	            url: URL + endpoint +   'deleteItem/',
	            headers: {
	            	'ssid': sessionId
	            },
	            data: data
	          });
	        return( request.then( handleSuccess, handleError ) );
	    }
	    
	   
	    /**
	     * @param endpoint
	     * @param sessionId                   
	     * @param data
	     * @return 
	     */     
	    function addBillingAddress(endpoint,sessionId,data) {
	    	 var request = $http({
	             method: 'POST',
	             url: 	URL + endpoint +  'address/billing',
	             headers: {
	             	'ssid': sessionId
	             } ,
	             data: data
	           });
	        return( request.then( handleSuccess, handleError ) );
	    }
	    
	    /**
	     * @param endpoint
	     * @param sessionId                   
	     * @param data
	     * @return 
	     */     
	    function addShippingAddress(endpoint,sessionId,data) {
	    	 var request = $http({
	             method: 'POST',
	             url: URL + endpoint +  'address/shipping',
	             headers: {
	             	'ssid': sessionId
	             } ,
	             data: data
	           });
	        return( request.then( handleSuccess, handleError ) );
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
	        return( response.data.responseObject);
	    }
});