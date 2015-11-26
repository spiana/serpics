var app = angular.module("order",  [])

	.constant('api_endpoint', 	'http://localhost:8080/jax-rs/orderService/')

 app.service("orderService", function( $http, $q ,$cookies,$log,api_endpoint) {
	 
        /** Return public API. (like java interface) **/
      	var service =   ({
      		getOrders: getOrders,
      		addPayment:addPayment
        	
        });                
        return service
        
        /** public methods**/
        /** read **/
        function getOrders() {
        	
            var request = $http({
                method: 'GET',
                url: api_endpoint + 'getOrders'
              });
        	
            return( request.then( handleSuccess, handleError ) );
        }                
    
        /** create **/
        function getOrders(order,data) {
        	
            var request = $http({
                method: 'POST',
                url: api_endpoint + '/addPayment/'+ order,
                headers: {
                	'ssid': authManager.getsessionId
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
        	console.log("send ssid to controller: " + response.data)
        	return( response.data );
        }
    }
);


