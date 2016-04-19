 var app = angular.module("order.controller", ['order.service','cart.service'])
/** orderController **/
.controller("orderController",['$scope','orderService','$log','cartService',
                                  
      function($scope,orderService,$log,cartService) {	
   	    
	$scope.order = {};
	
  	    function placeOrder(paymentId,payerId,token) {
  	    	if (paymentId != ""  && payerId != ""){
  	    		var paidData = {
  	    				paymentIdentifier: paymentId,
  	    				token: token,
  	    				payerId: payerId
  	    		}
  	    		$log.debug("OrderController: paidData:"+JSON.stringify(paidData));
  	    		cartService.addPaymentInfo(paidData).then( function( response ) {
  	  	    		$log.debug("OrderController: addPaymentInfo(paidData): paidData:"+JSON.stringify(paidData));
  	  	    		orderService.placeOrder().then( function( response){
  	  	    			$log.debug("orderController after addPaymentInfo: placeOrder()")
  	  	    			$scope.order = response;
  	  	    			})
  	    		});
  	    	}else{
  	  	    	orderService.placeOrder().then( function( response) {
  	  	    		$log.debug("orderController: placeOrder() without stateParams")
  	  	    		$scope.order = response;
  	  	    	})
  	    	}
  	    }
  	    
  	    $scope.placeOrder = function (paymentId,payerId,token){
  	    	placeOrder(paymentId,payerId,token)
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