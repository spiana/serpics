var app = angular.module("cart",  ['AuthManager','ngCookies'])

	.constant('api_endpoint', 	'http://localhost:8080/jax-rs/cartService/')

 app.service("cartService", function( $http, $q ,api_endpoint) {
	 
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
         * @param sessionId               
         * @param data
         * @return 
         */
        function getCurrentCart( sessionId, data ) {
            var request = $http({
                method: 'GET',
                url: api_endpoint + 'getCurrentCart/' , 
                headers: {
                	'ssid': sessionId
                }
              });
            return( request.then( handleSuccess, handleError ) );
        }
        
        /**
         * @param sessionId               
         * @param data
         * @return 
         */
        function cartAdd( sessionId, data ) {
            var request = $http({
                method: 'POST',
                url: api_endpoint +  'cartAdd',
                headers: {
                	'ssid': sessionId
                },   
                data: data
              });
            return( request.then( handleSuccess, handleError ) );
        }
        
       
        /**
         * @param sessionId               
         * @param data
         * @return 
         */
        function cartUpdate(sessionId, data ) {
            var request = $http({
                method: 'PUT',
                url: api_endpoint + 'cartUpdate',
                headers: {
                	'ssid': sessionId
                },   
                data: data
              });
            return( request.then( handleSuccess, handleError ) );
        }
        
        /**
         * @param sessionId                     
         * @param data
         * @return 
         */
        function deleteItem(sessionId,data) {
            var request = $http({
                method: 'DELETE',
                url: api_endpoint + 'deleteItem/',
                headers: {
                	'ssid': sessionId
                },
                data: data
              });
            return( request.then( handleSuccess, handleError ) );
        }
        
       
        /**
         * @param sessionId                   
         * @param data
         * @return 
         */     
        function addBillingAddress(sessionId,data) {
        	 var request = $http({
                 method: 'POST',
                 url	: 	api_endpoint + 'address/billing',
                 headers: {
                 	'ssid': sessionId
                 } ,
                 data: data
               });
            return( request.then( handleSuccess, handleError ) );
        }
        
        /**
         * @param sessionId                   
         * @param data
         * @return 
         */     
        function addShippingAddress(sessionId,data) {
        	 var request = $http({
                 method: 'POST',
                 url	: 	api_endpoint + 'address/shipping',
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
    }
);
		
		
app.controller("brandController",['$scope','$cookies','authManagerService','cartService', 
                                  
      function($scope,$cookies,authManagerService,cartService) {	
   	
  	    var endpoint    = 'http://localhost:8080/jax-rs/auth/connect/default-store'
  	    	
  	    $scope.data = {
  	    		sessionIn:'',
  	    		cart:[]
  	    }	
  	   	  
  	  
  	    /** implemented cart service **/ 
  	    
  	    /**
  	     * @param sessionId 		a sessionId
  	     * @param data				data to send
  	     * @return 					current cart from session
  	     * @use 					cartService,authManagerService
  	     */
  		$scope.getCurrentCart = function(sessionId,data ) {	
  	    	cartService.getCurrentCart(sessionId,data).then( function( response ) {
 	       		/** do stuff with response **/
             })
  	    };
  	    
  	    /**
  	     * @param sessionId 			a sessionId
  	     * @param data 					data to send
  	     * @return 						a new cart
  	     * @use 						cartService,authManagerService
  	     */
  	    $scope.cartAdd = function(sessionId, data) {		
  	    	cartService.cartAdd(sessionId, data).then( function( response ) {
  	       		/** do stuff with response **/
              })
  	    };
  	    
  	   /**
  	     * @param sessionId 			a sessionId
  	     * @param data    				data to send
  	     * @return 						a cart update with @param data
  	     * @use 						cartService,authManagerService
  	     */
  	    $scope.cartUpdate = function(sessionId, data ) {		
  	    	cartService.cartUpdate(sessionId,data ).then( function( response ) {
  	       		/** do stuff with response **/
              })
  	    };
  	    
  	    /**
  	     * @param sessionId 		a sessionId
  	     * @param code 	    
  	     * @return 					all brand by @param brandId
  	     * @use 					cartService,authManagerService
  	     */
  	    $scope.deleteItem = function(sessionId,data) {		
  	    	cartService.deleteItem(sessionId,data).then( function( response ) {
  	       		/** do stuff with response **/
              })
  	    };
  	    
  	    /**
  	     * @param sessionId 		a sessionId
  	     * @param data    			data to send
  	     * @return 					
  	     * @use 					cartService,authManagerService
  	     */
  	    $scope.addBillingAddress = function(sessionId,data) {		
  	    	cartService.addBillingAddress(sessionId,data).then( function( response ) {
  	       		/** do stuff with response **/
              })
  	    };
  	    
  	  /**
  	     * @param sessionId 		a sessionId
  	     * @param data    			data to send
  	     * @return 					
  	     * @use 					cartService,authManagerService
  	     */
  	    $scope.addShippingAddress = function(sessionId,data) {		
  	    	cartService.addShippingAddress(sessionId,data).then( function( response ) {
  	       		/** do stuff with response **/
              })
  	    };
  	             	    
}])
  