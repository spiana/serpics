 var app = angular.module("order.controller", ['order.service'])
/** orderController **/
.controller("orderController",['$scope','orderService','$log',
                                  
      function($scope,orderService,$log) {	
   	
  	    $scope.order 	= [];
  	    
  	    placeOrder();
  	    
  	    function placeOrder() {
  	    	orderService.placeOrder().then( function( response) {
  	    		$log.debug("orderController: placeOrder()")
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
  	    $scope.addPayment = function(orderId, paymentData) {		
  	    	orderService.addPayment(orderId, paymentData).then( function( response ) {
  	    		$log.debug("OrderController: addPayment(orderId, paymentData): ramo then");
  	    		$scope.order = response;
  	    	})
  	    };  	   
}])