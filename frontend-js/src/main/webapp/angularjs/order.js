var app = angular.module("order", ['AuthManager','ngCookies'])

	.constant('api_endpoint', 	'http://localhost:8080/jax-rs/orderService/')

 app.service("orderService", function( $http, $q,api_endpoint) {
	 
        /** Return public API. (like java interface) **/
      	var service =   ({
      		getOrders: getOrders,
      		addPayment:addPayment
        	
        });                
        return service
        
        /** public methods**/
        
        /**
         * @param sessionId      
         * @return 
         */
        function getOrders(sessionId) {
        	
            var request = $http({
                method: 'GET',
                url: api_endpoint + 'getOrders',
                headers: {
                	'ssid': sessionId
                }
              });
        	
            return( request.then( handleSuccess, handleError ) );
        }                
    
        /**
         * @param sessionId    
         * @param order 
         * @param data   
         * @return 
         */
        function addPayment(sessionId,order,data) {
        	
            var request = $http({
                method: 'POST',
                url: api_endpoint + '/addPayment/'+ order,
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
    }
);

app.controller("orderController",['$scope','$cookies','authManagerService','orderService', 
                                  
      function($scope,$cookies,authManagerService,orderService) {	
   	
  	    var endpoint    = 'http://localhost:8080/jax-rs/auth/connect/default-store'
  	    	
  	    $scope.data = {
  	    		sessionIn	: '',
  	    		order		: []
  	    }	
  	   	  
  	  
  	    /** implemented order service **/ 
  	    
  	    /**
  	     * @param sessionId 		a sessionId
  	     * @param data				data to send
  	     * @return 					current cart from session
  	     * @use 					orderService,authManagerService
  	     */
  		$scope.getOrders = function(sessionId) {	
  	    	orderService.getOrders(sessionId).then( function( response ) {
 	       		/** do stuff with response **/
             })
  	    };
  	    
  	    /**
  	     * @param sessionId 			a sessionId
  	     * @param order 				add payment for @param order
  	     * @param data 					data to send
  	     * @return 						a new cart
  	     * @use 						orderService,authManagerService
  	     */
  	    $scope.addPayment = function(sessionId, order, data) {		
  	    	orderService.addPayment(sessionId,order,data).then( function( response ) {
  	       		/** do stuff with response **/
              })
  	    };  	   
  	             	    
}])
  
