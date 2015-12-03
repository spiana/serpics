 var app = angular.module("order.service", ['serpics.config'])
/**
 * order service to handler rest call to order service
 */
app.service("orderService", function( $http, $q, authManagerService,URL ) {
	
	var endpoint = '/jax-rs/orderService/';
	 
	    /** Return public API. (like java interface) **/
	  	var service =   ({
	  		getOrders: getOrders,
	  		addPayment:addPayment
	    	
	    });                
	    return service
	    
	    /** public methods**/
	    
	    /**
	     * @param endpoint
	     * @param sessionId      
	     * @return 
	     */
	    function getOrders(endpoint,sessionId) {
	    	
	        var request = $http({
	            method: 'GET',
	            url: endpoint +  'getOrders',
	            headers: {
	            	'ssid': sessionId
	            }
	          });
	    	
	        return( request.then( handleSuccess, handleError ) );
	    }                
	
	    /**
	     * @param endpoint
	     * @param sessionId    
	     * @param order 
	     * @param data   
	     * @return 
	     */
	    function addPayment(endpoint,sessionId,order,data) {
	    	
	        var request = $http({
	            method: 'POST',
	            url: endpoint +  '/addPayment/'+ order,
	            headers: {
	            	'ssid': sessionId
	            },   
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