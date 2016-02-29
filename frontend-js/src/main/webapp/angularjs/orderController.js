 var app = angular.module("order.controller", ['order.service','serpics.router','cart.service'])
/** orderController **/
.controller("orderController",['$scope','orderService','$log','$stateParams','cartService',
                                  
      function($scope,orderService,$log,$stateParams,cartService) {	
   	
	    $scope.order = {};
	    placeOrder();

  	    

  	    
  	    function placeOrder() {
  	    	
  	    	$log.debug("$stateParams:   "+$stateParams);
//  	    {"paymentId":"PAY-99K50069UF479600WK3HP111","token":"EC-8EL93027C49180111","PayerID":"SHB26LER8P111"}
  	    	if ($stateParams.paymentId != null  && $stateParams.PayerID != null){
  	    		var paidData = {
  	    				paymentIdentifier: $stateParams.paymentId,
  	    				token: $stateParams.token,
  	    				payerId: $stateParams.PayerID
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