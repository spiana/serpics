 var app = angular.module("order.controller", ['order.service'])
/** orderController **/
.controller("orderController",['$scope','serpicsServices','orderService', 
                                  
      function($scope,serpicsServices,orderService) {	
   	
  	    $scope.order 	= [];
  	    
  	    /** implemented order service **/ 
  	    
  	    /**
  	     * @param endpoint 		    web service rest endpoint
  	     * @param sessionId 		a sessionId
  	     * @param data				data to send
  	     * @return 					current cart from session
  	     * @use 					orderService,serpicsServices
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
  	     * @use 						orderService,serpicsServices
  	     */
  	    $scope.addPayment = function(endpoint, order, data) {		
  	    	orderService.addPayment(endpoint,$rootScope.sessionId,order,data).then( function( response ) {
  	       		/** do stuff with response **/
              })
  	    };  	   
  	             	    
}])