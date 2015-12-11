 var app = angular.module("order.controller", ['order.service'])
/** orderController **/
.controller("orderController",['$scope','authManagerService','orderService', 
                                  
      function($scope,authManagerService,orderService) {	
   	
  	    $scope.order 	= [];
  	    
  	    /** implemented order service **/ 
  	    
  	    /**
  	     * @param endpoint 		    web service rest endpoint
  	     * @param sessionId 		a sessionId
  	     * @param data				data to send
  	     * @return 					current cart from session
  	     * @use 					orderService,authManagerService
  	     */
  		$scope.getOrders = function(endpoint) {	
  	    	orderService.getOrders(endpoint,$rootScope.sessionId).then( function( response ) {
 	       		/** do stuff with response **/
             })
  	    };
  	    
  	    /**
  	     * @param endpoint 		    	web service rest endpoint
  	     * @param sessionId 			a sessionId
  	     * @param order 				add payment for @param order
  	     * @param data 					data to send
  	     * @return 						a new cart
  	     * @use 						orderService,authManagerService
  	     */
  	    $scope.addPayment = function(endpoint, order, data) {		
  	    	orderService.addPayment(endpoint,$rootScope.sessionId,order,data).then( function( response ) {
  	       		/** do stuff with response **/
              })
  	    };  	   
  	             	    
}])