 var app = angular.module("order.service", ['serpics.config'])
/**
 * order service to handler rest call to order service
 */
app.service("orderService", function( $http, $q, serpicsServices,URL,COOKIE_EXPIRES,$log,$cookies ) {
	
	var endpoint = '/jax-rs/orderService/';
	var localSessionId = '';
	
	    /** Return public API. (like java interface) **/
	  	var service =   ({
	  		getOrders:		getOrders,
	  		addPayment:		addPayment,
	    	placeOrder:		placeOrder
	    });                
	    return service
	    
	    /** public methods**/
	    
	    /**
	     * @return 
	     */
	    function getOrders() {
	    	var serviceSSID = serpicsServices;
	    	return $q(function(resolve, reject) {
	    		
	    		serviceSSID.getSessionId().then(function(sessionId){
	    			$log.debug("OrderService getOrders() ssid nel promise"+sessionId) ;
	    			$http({
			             method: 'GET',
			             url: 	URL + endpoint,
			             headers: {
			             	'ssid': sessionId
			            }
			          }).then(handleSuccess, handleError).then(resolve, reject);
	    		});
	    	});
	    }                
	
	    /**
	     * @param order 
	     * @param data   
	     * @return 
	     */
	    function addPayment(order,data) {
	    	var serviceSSID = serpicsServices;
	    	return $q(function(resolve, reject) {
	    		
	    		serviceSSID.getSessionId().then(function(sessionId){
	    			$log.debug("OrderService addPayment(order,data) ssid nel promise"+sessionId) ;
	    			$http({
			             method: 'POST',
			             url: 	URL + endpoint +  'addPayment/'+ order,
			             data: data,
			             headers: {
			             	'ssid': sessionId
			            }
			          }).then(handleSuccess, handleError).then(resolve, reject);
	    		});
	    	});
	    }
	    
	    /**
	     * @return order from currentCart
	     */
	    function placeOrder() {
	    	var serviceSSID = serpicsServices;
	    	return $q(function(resolve, reject) {
	    		
	    		serviceSSID.getSessionId().then(function(sessionId){
	    			$log.debug("OrderService placeOrder() ssid nel promise"+sessionId) ;
	    			$http({
			             method: 'GET',
			             url: 	URL + endpoint +  'placeOrder',
			             headers: {
			             	'ssid': sessionId
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