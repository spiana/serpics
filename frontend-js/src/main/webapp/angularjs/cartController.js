 var app = angular.module("cart.controller", ['cart.service'])
 /** cartController **/
.controller("cartController",['$scope','cartService',
                                  
      function($scope,cartService) {	
   	
  	    $rootScope.cart 	= [];
  	  
  	    /** implemented cart service **/ 
  	    
  	    /**
  	     * @param endpoint 		    	web service rest endpoint
  	     * @param sessionId 		a sessionId
  	     * @param data				data to send
  	     * @return 					current cart from session
  	     * @use 					cartService,
  	     */
  		$scope.getCurrentCart = function(endpoint,data ) {	
  	    	cartService.getCurrentCart(endpoint,$rootScope.sessionId,data).then( function( response ) {
 	       		/** do stuff with response **/
             })
  	    };
  	    
  	    /**
  	     * @param endpoint 		    	web service rest endpoint
  	     * @param sessionId 			a sessionId
  	     * @param data 					data to send
  	     * @return 						a new cart
  	     * @use 						cartService,
  	     */
  	    $scope.cartAdd = function(endpoint, data) {		
  	    	cartService.cartAdd(endpoint,$rootScope.sessionId, data).then( function( response ) {
  	       		/** do stuff with response **/
              })
  	    };
  	    
  	   /**
  	    * @param endpoint 		    	web service rest endpoint
  	     * @param sessionId 			a sessionId
  	     * @param data    				data to send
  	     * @return 						a cart update with @param data
  	     * @use 						cartService,
  	     */
  	    $scope.cartUpdate = function(endpoint, data ) {		
  	    	cartService.cartUpdate(endpoint,$rootScope.sessionId,data ).then( function( response ) {
  	       		/** do stuff with response **/
              })
  	    };
  	    
  	    /**
  	     * @param endpoint 		    web service rest endpoint
  	     * @param sessionId 		a sessionId
  	     * @param code 	    
  	     * @return 					all brand by @param brandId
  	     * @use 					cartService,
  	     */
  	    $scope.deleteItem = function(endpoint,data) {		
  	    	cartService.deleteItem(endpoint,$rootScope.sessionId,data).then( function( response ) {
  	       		/** do stuff with response **/
              })
  	    };
  	    
  	    /**
  	     * @param endpoint 		    web service rest endpoint
  	     * @param sessionId 		a sessionId
  	     * @param data    			data to send
  	     * @return 					
  	     * @use 					cartService,
  	     */
  	    $scope.addBillingAddress = function(endpoint,data) {		
  	    	cartService.addBillingAddress(endpoint,$rootScope.sessionId,data).then( function( response ) {
  	       		/** do stuff with response **/
              })
  	    };
  	    
  	  /**
  	     * @param sessionId 		a sessionId
  	     * @param data    			data to send
  	     * @return 					
  	     * @use 					cartService,
  	     */
  	    $scope.addShippingAddress = function(endpoint,data) {		
  	    	cartService.addShippingAddress(endpoint,sessionId,data).then( function( response ) {
  	       		/** do stuff with response **/
              })
  	    };
  	             	    
}])
