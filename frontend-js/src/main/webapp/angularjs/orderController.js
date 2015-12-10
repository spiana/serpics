 var app = angular.module("order.controller", ['order.service'])
/** orderController **/
.controller("orderController",['$scope','orderService', 
                                  
      function($scope,serpicsServices,orderService) {	
   	
  	    $scope.order 	= [];
  	    
  	    /** implemented order service **/ 
  	    
  	    /**
  	     * @return 					list of orders
  	     * @use 					orderService,serpicsServices
  	     */
  		$scope.getOrders = function() {	
  	    	orderService.getOrders().then( function( response ) {
  	    		console.log("OrderController: getOrders(): ramo then");
  	    		$scope.order = response;
  	    	})
  	    };
  	    
  	    /**
  	     * @param order 				add payment for @param order
  	     * @param data 					data to send
  	     * @return 						a new cart
  	     * @use 						orderService,serpicsServices
  	     */
  	    $scope.addPayment = function( order, data) {		
  	    	orderService.addPayment(order, data).then( function( response ) {
  	    		console.log("OrderController: addPayment(): ramo then");
  	    		$scope.order = response;
  	    	})
  	    };  	   
  	             	    
}])