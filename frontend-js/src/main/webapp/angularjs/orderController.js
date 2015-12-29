 var app = angular.module("order.controller", ['order.service'])
/** orderController **/
.controller("orderController",['$scope','orderService', 
                                  
      function($scope,orderService) {	
   	
  	    $scope.order 	= [];
  	    
  	    placeOrder();
  	    
  	    function placeOrder() {
  	    	orderService.placeOrder().then( function( response) {
  	    		console.log("orderController: placeOrder()")
  	    		$scope.order = response;
  	    	})
  	    }
  	    
  	    /** implemented order service **/ 
  	    
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